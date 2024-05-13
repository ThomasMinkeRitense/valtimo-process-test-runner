package com.ritense.valtimoprocesstestrunner.interaction.definition

import java.util.UUID

interface TestrunInteractionDefinitionService {
    fun getInteractionDefinitionsByTestrunDefinition(testrunDefinitionId: UUID): List<TestrunInteractionDefinition>

    fun createInteractionDefinition(definitionId: UUID, testrunInteractionDefinitionDto: TestrunInteractionDefinitionDto): TestrunInteractionDefinition
}