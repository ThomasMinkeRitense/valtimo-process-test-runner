package com.ritense.valtimoprocesstestrunner.interaction.definition

import com.ritense.valtimoprocesstestrunner.testrun.definition.TestrunDefinition

interface TestrunInteractionDefinitionService {
    fun getInteractionDefinitionsByTestrunDefinition(definition: TestrunDefinition): List<TestrunInteractionDefinition>

    fun createOrUpdateInteractionDefinition(definition: TestrunDefinition, testrunInteractionDefinitionDto: TestrunInteractionDefinitionDto): TestrunInteractionDefinition
}
