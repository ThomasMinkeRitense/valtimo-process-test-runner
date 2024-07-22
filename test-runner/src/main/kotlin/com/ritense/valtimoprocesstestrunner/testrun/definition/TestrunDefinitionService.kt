package com.ritense.valtimoprocesstestrunner.testrun.definition

import java.util.UUID

interface TestrunDefinitionService {
    fun createTestrunDefinition(testrunDefinitionDto: TestrunDefinitionDto): TestrunDefinition

    fun updateTestrunDefinition(testrunDefinitionDto: TestrunDefinitionDto): TestrunDefinition
    fun getAllDefinitions(): List<TestrunDefinition>

    fun getDefinition(id: UUID): TestrunDefinition
}
