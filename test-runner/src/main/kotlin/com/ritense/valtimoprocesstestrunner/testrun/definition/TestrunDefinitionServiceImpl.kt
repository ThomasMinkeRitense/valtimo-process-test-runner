package com.ritense.valtimoprocesstestrunner.testrun.definition

import com.fasterxml.jackson.databind.ObjectMapper
import com.ritense.form.service.FormSubmissionService
import com.ritense.processlink.service.ProcessLinkActivityService
import com.ritense.valtimo.camunda.service.CamundaRepositoryService
import com.ritense.valtimoprocesstestrunner.command.TestrunCommandDefinition
import com.ritense.valtimoprocesstestrunner.event.TestrunEventDefinition
import com.ritense.valtimoprocesstestrunner.interaction.definition.TestrunInteractionDefinition
import com.ritense.valtimoprocesstestrunner.interaction.definition.TestrunInteractionDefinitionRepository
import com.ritense.valtimoprocesstestrunner.interaction.definition.TestrunInteractionDefinitionService
import java.util.UUID

class TestrunDefinitionServiceImpl(
    private val definitionRepository: TestrunDefinitionRepository,
    private val testrunInteractionDefinitionService: TestrunInteractionDefinitionService
) : TestrunDefinitionService {
    override fun createTestrunDefinition(testrunDefinitionDto: TestrunDefinitionDto): TestrunDefinition {
        if(testrunDefinitionDto.id != null) {
          throw IllegalArgumentException("Definition ID can not be set for the create operation")
        }

        val definition = TestrunDefinition(
          title = testrunDefinitionDto.title,
          processDefinitionKey = testrunDefinitionDto.processDefinitionKey,
          documentDefinitionName = testrunDefinitionDto.documentDefinitionName,
          payload = testrunDefinitionDto.payload
        )

        definitionRepository.saveAndFlush(definition)

        testrunDefinitionDto.interactions.forEach {
          val interactionDefinition = testrunInteractionDefinitionService
            .createOrUpdateInteractionDefinition(definition, it)
          definition.interactions.add(interactionDefinition)
        }

        return definitionRepository.save(definition);
    }

  override fun updateTestrunDefinition(testrunDefinitionDto: TestrunDefinitionDto): TestrunDefinition {
    definitionRepository.findById(testrunDefinitionDto.id!!).orElseThrow()

    val definition = TestrunDefinition(
      id = testrunDefinitionDto.id,
      title = testrunDefinitionDto.title,
      processDefinitionKey = testrunDefinitionDto.processDefinitionKey,
      documentDefinitionName = testrunDefinitionDto.documentDefinitionName,
      payload = testrunDefinitionDto.payload
    )

    definitionRepository.saveAndFlush(definition)

    testrunDefinitionDto.interactions.forEach {
      val interactionDefinition = testrunInteractionDefinitionService
        .createOrUpdateInteractionDefinition(definition, it)
      definition.interactions.add(interactionDefinition)
    }

    return definitionRepository.save(definition);
  }

  override fun getAllDefinitions(): List<TestrunDefinition> {
        return definitionRepository.findAll()
    }

    override fun getDefinition(id: UUID): TestrunDefinition {
        return definitionRepository.findById(id).orElseThrow()
    }

}
