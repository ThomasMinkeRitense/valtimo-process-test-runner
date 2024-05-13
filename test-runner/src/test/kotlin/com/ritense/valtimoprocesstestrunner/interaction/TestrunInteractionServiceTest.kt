package com.ritense.valtimoprocesstestrunner.interaction

import com.fasterxml.jackson.databind.ObjectMapper
import com.ritense.valtimoprocesstestrunner.completetask.CompleteTask
import com.ritense.valtimoprocesstestrunner.completetask.CompleteTaskCommandDefinition
import com.ritense.valtimoprocesstestrunner.command.TestrunCommandService
import com.ritense.valtimoprocesstestrunner.event.TaskCreated
import com.ritense.valtimoprocesstestrunner.event.TaskCreatedEventDefinition
import com.ritense.valtimoprocesstestrunner.interaction.instance.TestrunInteractionInstance
import com.ritense.valtimoprocesstestrunner.interaction.instance.TestrunInteractionInstanceRepository
import com.ritense.valtimoprocesstestrunner.interaction.instance.TestrunInteractionInstanceServiceImpl
import com.ritense.valtimoprocesstestrunner.mapper.TestrunMapperService
import com.ritense.valtimoprocesstestrunner.testrun.definition.TestrunDefinition
import com.ritense.valtimoprocesstestrunner.testrun.instance.TestrunInstance
import com.ritense.valtimoprocesstestrunner.testrun.instance.TestrunInstanceRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import java.util.*

class TestrunInteractionServiceTest {
    lateinit var service: TestrunInteractionInstanceServiceImpl

    @Mock
    lateinit var testrunInteractionInstanceRepository: TestrunInteractionInstanceRepository

    @Mock
    lateinit var testrunInstanceRepository: TestrunInstanceRepository

    @Mock
    lateinit var mapperService: TestrunMapperService

    @Mock
    lateinit var commandService: TestrunCommandService

    var objectMapper: ObjectMapper = ObjectMapper();

    @BeforeEach
    fun init() {
        MockitoAnnotations.openMocks(this)
        service = TestrunInteractionInstanceServiceImpl(
            commandService,
            testrunInteractionInstanceRepository,
            testrunInstanceRepository,
            mapperService
        )
    }

    @Test
    fun `Should dispatch the correct command to the commandService`() {
        val payload = objectMapper.createObjectNode();
        payload.put("var", "val")

        val documentId = UUID.randomUUID()
        val eventDefinition = TaskCreatedEventDefinition(
            taskDefinitionKey = "taskDefinitionKey"
        )
        val commandDefinition = CompleteTaskCommandDefinition(
            taskDefinitionKey = "taskDefinitionKey",
            payload
        )

        val testrunDefinition = TestrunDefinition(
            title = "Testrun 1",
            processDefinitionKey = "testrun-process",
            documentDefinitionName = "testrun-document",
            payload = objectMapper.createObjectNode()
        )

        val testrunInstance = TestrunInstance(
            definition = testrunDefinition,
            documentId = documentId
        )

        val interaction = TestrunInteractionInstance(
            testrunInstance = testrunInstance,
            eventDefinition = eventDefinition,
            commandDefinition = commandDefinition
        )

        val event = TaskCreated("taskDefinitionKey", "taskId", documentId)
        val command = CompleteTask("taskId", payload)

        `when`(testrunInstanceRepository.findByDocumentId(documentId))
            .thenReturn(Optional.of(testrunInstance))
        `when`(testrunInteractionInstanceRepository.findAllByTestrunInstance(testrunInstance))
            .thenReturn(listOf(interaction))
        `when`(mapperService.eventToCommand(event, CompleteTask::class.java, interaction))
            .thenReturn(command)

        service.handleEvent(event)

        verify(commandService).handleCommand(command)
    }
}