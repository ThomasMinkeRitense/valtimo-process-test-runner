package com.ritense.valtimoprocesstestrunner.testrun.instance

import com.ritense.valtimoprocesstestrunner.testrun.definition.TestrunDefinition
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface TestrunInstanceRepository: JpaRepository<TestrunInstance, UUID> {
    fun findByDocumentId(documentId: UUID): Optional<TestrunInstance>

    fun findByDefinition(definition: TestrunDefinition): List<TestrunInstance>
}
