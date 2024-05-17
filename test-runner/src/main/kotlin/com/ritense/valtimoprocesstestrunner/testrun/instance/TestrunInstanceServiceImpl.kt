package com.ritense.valtimoprocesstestrunner.testrun.instance

import com.ritense.form.service.FormSubmissionService
import com.ritense.processlink.service.ProcessLinkActivityService
import com.ritense.valtimo.camunda.service.CamundaRepositoryService
import com.ritense.valtimoprocesstestrunner.interaction.definition.TestrunInteractionDefinition
import com.ritense.valtimoprocesstestrunner.interaction.definition.TestrunInteractionDefinitionService
import com.ritense.valtimoprocesstestrunner.interaction.instance.TestrunInteractionInstance
import com.ritense.valtimoprocesstestrunner.interaction.instance.TestrunInteractionInstanceService
import com.ritense.valtimoprocesstestrunner.testrun.definition.TestrunDefinition
import com.ritense.valtimoprocesstestrunner.testrun.definition.TestrunDefinitionDto
import com.ritense.valtimoprocesstestrunner.testrun.definition.TestrunDefinitionRepository
import mu.KotlinLogging
import org.springframework.context.ApplicationEventPublisher
import java.util.*

class TestrunInstanceServiceImpl(
    private val instanceRepository: TestrunInstanceRepository,
    private val formSubmissionService: FormSubmissionService,
    private val camundaRepositoryService: CamundaRepositoryService,
    private val processLinkActivityService: ProcessLinkActivityService,
    private val testrunInteractionDefinitionService: TestrunInteractionDefinitionService,
    private val testrunInteractionInstanceService: TestrunInteractionInstanceService
) : TestrunInstanceService {
    override fun startTestrun(definition: TestrunDefinition): TestrunInstance {
        logger.info { "Starting test-run for definition ${definition.id}" }

        val instance = TestrunInstance(
            definition = definition,
            documentId = null
        )

        instanceRepository.save(instance);
        copyInteractionsToInstance(instance)
        val processLinkId = getProcessLinkId(definition)
        val documentId = createDocumentAndStartProcess(processLinkId, definition)

        instance.assignDocument(UUID.fromString(documentId));
        instanceRepository.save(instance);

        return instance
    }

    override fun getByDocumentId(documentId: UUID): Optional<TestrunInstance> {
        return instanceRepository.findByDocumentId(documentId)
    }

  override fun getByDefinition(definition: TestrunDefinition): List<TestrunInstance> {
        return instanceRepository.findByDefinition(definition)
  }

  private fun copyInteractionsToInstance(testrunInstance: TestrunInstance) {
        val interactionDefinitions: List<TestrunInteractionDefinition> = testrunInteractionDefinitionService.getInteractionDefinitionsByTestrunDefinition(testrunInstance.definition.id)
        interactionDefinitions
            .forEach({
                val testrunInteractionInstance = TestrunInteractionInstance(
                    testrunInstance = testrunInstance,
                    eventDefinition = it.eventDefinition,
                    commandDefinition = it.commandDefinition
                )
                testrunInteractionInstanceService.createInteractionInstance(testrunInteractionInstance)
            })
    }

    private fun getProcessLinkId(definition: TestrunDefinition): UUID {
        val camundaProcessDefinitionId = getLatestProcessDefinitionId(definition);
        return processLinkActivityService.getStartEventObject(
            processDefinitionId = camundaProcessDefinitionId,
            documentDefinitionName = definition.documentDefinitionName,
            documentId = null
        )!!.processLinkId
    }

    private fun getLatestProcessDefinitionId(definition: TestrunDefinition): String {
        return camundaRepositoryService.findLatestProcessDefinition(definition.processDefinitionKey)!!.id;
    }

    private fun createDocumentAndStartProcess(processLinkId: UUID, definition: TestrunDefinition): String? {
        return formSubmissionService.handleSubmission(
            processLinkId = processLinkId,
            formData = definition.payload,
            documentDefinitionName = definition.documentDefinitionName,
            documentId = null,
            taskInstanceId = null
        ).documentId()
    }

    companion object {
        val logger = KotlinLogging.logger {}
    }
}
