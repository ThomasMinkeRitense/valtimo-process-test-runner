package com.ritense.valtimoprocesstestrunner.testrun.definition

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface TestrunDefinitionRepository: JpaRepository<TestrunDefinition, UUID> {
}