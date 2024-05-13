package com.ritense.valtimoprocesstestrunner.testrun.definition

import com.ritense.form.service.FormSubmissionService
import com.ritense.processlink.service.ProcessLinkActivityService
import com.ritense.valtimo.camunda.service.CamundaRepositoryService
import java.util.UUID

class TestrunDefinitionServiceImpl(
    private val definitionRepository: TestrunDefinitionRepository
) : TestrunDefinitionService {
    override fun createTestrunDefinition(testrunDefinitionDto: TestrunDefinitionDto): TestrunDefinition {
        val testrunDefinition = TestrunDefinitionDto.toEntity(testrunDefinitionDto)
        return definitionRepository.save(testrunDefinition);
    }

    override fun getAllDefinitions(): List<TestrunDefinition> {
        return definitionRepository.findAll()
    }

    override fun getDefinition(id: UUID): TestrunDefinition {
        return definitionRepository.findById(id).orElseThrow()
    }

}