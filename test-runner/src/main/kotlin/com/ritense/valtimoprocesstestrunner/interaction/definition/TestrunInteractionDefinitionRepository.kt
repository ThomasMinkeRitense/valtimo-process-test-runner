package com.ritense.valtimoprocesstestrunner.interaction.definition

import com.ritense.valtimoprocesstestrunner.testrun.definition.TestrunDefinition
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface TestrunInteractionDefinitionRepository: JpaRepository<TestrunInteractionDefinition, UUID> {
    fun getAllByTestrunDefinition(testrunDefinition: TestrunDefinition): List<TestrunInteractionDefinition>
}