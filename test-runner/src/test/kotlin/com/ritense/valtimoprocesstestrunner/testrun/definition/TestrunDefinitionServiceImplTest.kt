package com.ritense.valtimoprocesstestrunner.testrun.definition

import com.fasterxml.jackson.databind.ObjectMapper
import com.ritense.valtimoprocesstestrunner.testrun.instance.TestrunInstanceServiceImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify

class TestrunDefinitionServiceImplTest {
    lateinit var service: TestrunDefinitionService

    @Mock
    lateinit var testrunDefinitionRepository: TestrunDefinitionRepository

    var objectMapper: ObjectMapper = ObjectMapper();

    var processDefinitionKey = "testrun-process"
    var documentDefinitionName = "testrun-document"
    var title = "Testrun 1"

    @BeforeEach
    fun init() {
        MockitoAnnotations.openMocks(this)
        service = TestrunDefinitionServiceImpl(
            definitionRepository = testrunDefinitionRepository
        )
    }
    @Test
    fun `Should create testrun definition`() {
        val testrunDefinitionDto = TestrunDefinitionDto(
            id = null,
            title = title,
            processDefinitionKey = processDefinitionKey,
            documentDefinitionName = documentDefinitionName,
            payload = ObjectMapper().createObjectNode()
        )

        Mockito.`when`(testrunDefinitionRepository.save(Mockito.any())).thenAnswer { i -> i.arguments[0] }
        val entity = service.createTestrunDefinition(testrunDefinitionDto)

        verify(testrunDefinitionRepository).save(entity)
    }
}