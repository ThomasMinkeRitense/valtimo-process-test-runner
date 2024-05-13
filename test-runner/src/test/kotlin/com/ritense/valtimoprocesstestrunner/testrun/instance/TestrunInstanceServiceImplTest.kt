package com.ritense.valtimoprocesstestrunner.testrun.instance

import com.fasterxml.jackson.databind.ObjectMapper
import com.ritense.form.service.FormSubmissionService
import com.ritense.form.web.rest.dto.FormSubmissionResult
import com.ritense.form.web.rest.dto.FormSubmissionResultSucceeded
import com.ritense.processlink.service.ProcessLinkActivityService
import com.ritense.processlink.web.rest.dto.ProcessLinkActivityResult
import com.ritense.valtimo.camunda.domain.CamundaProcessDefinition
import com.ritense.valtimo.camunda.service.CamundaRepositoryService
import com.ritense.valtimoprocesstestrunner.interaction.definition.TestrunInteractionDefinitionService
import com.ritense.valtimoprocesstestrunner.interaction.instance.TestrunInteractionInstanceService
import com.ritense.valtimoprocesstestrunner.testrun.definition.TestrunDefinition
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import java.util.*

class TestrunInstanceServiceImplTest {
    lateinit var service: TestrunInstanceService

    @Mock lateinit var testrunInstanceRepository: TestrunInstanceRepository
    @Mock lateinit var formSubmissionService: FormSubmissionService
    @Mock lateinit var camundaRepositoryService: CamundaRepositoryService
    @Mock lateinit var processLinkActivityService: ProcessLinkActivityService
    @Mock lateinit var testrunInteractionDefinitionService: TestrunInteractionDefinitionService
    @Mock lateinit var testrunInteractionInstanceService: TestrunInteractionInstanceService

    var objectMapper: ObjectMapper = ObjectMapper();

    var processDefinitionKey = "testrun-process"
    var documentDefinitionName = "testrun-document"
    var title = "Testrun 1"

    @BeforeEach
    fun init() {
        MockitoAnnotations.openMocks(this)
        service = TestrunInstanceServiceImpl(
            instanceRepository = testrunInstanceRepository,
            formSubmissionService = formSubmissionService,
            camundaRepositoryService = camundaRepositoryService,
            processLinkActivityService = processLinkActivityService,
            testrunInteractionDefinitionService = testrunInteractionDefinitionService,
            testrunInteractionInstanceService = testrunInteractionInstanceService
        )
    }

    @Test
    fun `Should start test run`() {
        val documentId = UUID.randomUUID()
        val testrunDefinition = TestrunDefinition(
            title = title,
            processDefinitionKey = processDefinitionKey,
            documentDefinitionName = documentDefinitionName,
            payload = objectMapper.createObjectNode()
        )

        val camundaProcessDefinition: CamundaProcessDefinition = mock()
        val processLink: ProcessLinkActivityResult<*> = mock()
        val formSubmissionResult: FormSubmissionResult = FormSubmissionResultSucceeded(documentId.toString())

        `when`(
            camundaRepositoryService
                .findLatestProcessDefinition(processDefinitionKey)
        ).thenReturn(camundaProcessDefinition)

        `when`(
            processLinkActivityService
                .getStartEventObject(
                    camundaProcessDefinition.id,
                    documentId = null,
                    documentDefinitionName = documentDefinitionName
                )
        ).thenReturn(processLink)

        `when`(formSubmissionService
            .handleSubmission(
                processLinkId = processLink.processLinkId,
                formData = ObjectMapper().createObjectNode(),
                documentDefinitionName = documentDefinitionName,
                documentId = null,
                taskInstanceId = null
            )
        ).thenReturn(formSubmissionResult)

        val instance = service.startTestrun(testrunDefinition)

        assertEquals(instance.documentId, documentId)
        assertEquals(instance.definition, testrunDefinition)
        verify(formSubmissionService)
            .handleSubmission(
                processLinkId = processLink.processLinkId,
                formData = ObjectMapper().createObjectNode(),
                documentDefinitionName = documentDefinitionName,
                documentId = null,
                taskInstanceId = null
            )
    }
}