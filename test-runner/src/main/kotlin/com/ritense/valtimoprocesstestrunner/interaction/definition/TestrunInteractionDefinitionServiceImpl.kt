package com.ritense.valtimoprocesstestrunner.interaction.definition

import com.fasterxml.jackson.databind.ObjectMapper
import com.ritense.valtimoprocesstestrunner.command.TestrunCommandDefinition
import com.ritense.valtimoprocesstestrunner.event.TestrunEventDefinition
import com.ritense.valtimoprocesstestrunner.testrun.definition.TestrunDefinitionService
import java.util.*

class TestrunInteractionDefinitionServiceImpl(
    private val testrunDefinitionService: TestrunDefinitionService,
    private val repository: TestrunInteractionDefinitionRepository,
    private val objectMapper: ObjectMapper
): TestrunInteractionDefinitionService {
    override fun getInteractionDefinitionsByTestrunDefinition(testrunDefinitionId: UUID): List<TestrunInteractionDefinition> {
        val definition = testrunDefinitionService.getDefinition(testrunDefinitionId)
        return repository.getAllByTestrunDefinition(definition)
    }

    override fun createInteractionDefinition(
        definitionId: UUID,
        testrunInteractionDefinitionDto: TestrunInteractionDefinitionDto
    ): TestrunInteractionDefinition {
        val definition = testrunDefinitionService.getDefinition(definitionId)

        val testrunInteractionDefinition = TestrunInteractionDefinition(
            testrunDefinition = definition,
            commandDefinition = objectMapper.treeToValue(testrunInteractionDefinitionDto.commandDefinition, TestrunCommandDefinition::class.java),
            eventDefinition = objectMapper.treeToValue(testrunInteractionDefinitionDto.eventDefinition, TestrunEventDefinition::class.java)
        )

        return repository.save(testrunInteractionDefinition)
    }
}