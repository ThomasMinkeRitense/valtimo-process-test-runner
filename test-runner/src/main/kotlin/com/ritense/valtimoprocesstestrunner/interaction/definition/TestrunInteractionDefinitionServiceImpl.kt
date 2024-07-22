package com.ritense.valtimoprocesstestrunner.interaction.definition

import com.fasterxml.jackson.databind.ObjectMapper
import com.ritense.valtimoprocesstestrunner.command.TestrunCommandDefinition
import com.ritense.valtimoprocesstestrunner.event.TestrunEventDefinition
import com.ritense.valtimoprocesstestrunner.testrun.definition.TestrunDefinition
import com.ritense.valtimoprocesstestrunner.testrun.definition.TestrunDefinitionService
import java.util.*

class TestrunInteractionDefinitionServiceImpl(
    private val repository: TestrunInteractionDefinitionRepository,
    private val objectMapper: ObjectMapper
): TestrunInteractionDefinitionService {
    override fun getInteractionDefinitionsByTestrunDefinition(definition: TestrunDefinition): List<TestrunInteractionDefinition> {
        return repository.getAllByTestrunDefinition(definition)
    }

    override fun createOrUpdateInteractionDefinition(
      definition: TestrunDefinition,
      testrunInteractionDefinitionDto: TestrunInteractionDefinitionDto
    ): TestrunInteractionDefinition {
        if(testrunInteractionDefinitionDto.id == null) {
          return createInteractionDefinition(definition, testrunInteractionDefinitionDto)
        } else {
          return updateInteractionDefinition(definition, testrunInteractionDefinitionDto)
        }
    }

    private fun createInteractionDefinition(
      definition: TestrunDefinition,
      testrunInteractionDefinitionDto: TestrunInteractionDefinitionDto
    ): TestrunInteractionDefinition {
      val testrunInteractionDefinition = TestrunInteractionDefinition(
        title = testrunInteractionDefinitionDto.title,
        testrunDefinition = definition,
        commandDefinition = objectMapper.treeToValue(testrunInteractionDefinitionDto.commandDefinition, TestrunCommandDefinition::class.java),
        eventDefinition = objectMapper.treeToValue(testrunInteractionDefinitionDto.eventDefinition, TestrunEventDefinition::class.java)
      )

      return repository.saveAndFlush(testrunInteractionDefinition)
    }

    private fun updateInteractionDefinition(
      definition: TestrunDefinition,
      testrunInteractionDefinitionDto: TestrunInteractionDefinitionDto
    ): TestrunInteractionDefinition {
      val testrunInteractionDefinition = TestrunInteractionDefinition(
        id = testrunInteractionDefinitionDto.id!!,
        title = testrunInteractionDefinitionDto.title,
        testrunDefinition = definition,
        commandDefinition = objectMapper.treeToValue(testrunInteractionDefinitionDto.commandDefinition, TestrunCommandDefinition::class.java),
        eventDefinition = objectMapper.treeToValue(testrunInteractionDefinitionDto.eventDefinition, TestrunEventDefinition::class.java)
      )

      return repository.saveAndFlush(testrunInteractionDefinition)
    }
}
