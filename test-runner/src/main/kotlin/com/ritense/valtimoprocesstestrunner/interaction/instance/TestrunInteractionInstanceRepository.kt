package com.ritense.valtimoprocesstestrunner.interaction.instance

import com.ritense.valtimoprocesstestrunner.event.TestrunEventDefinition
import com.ritense.valtimoprocesstestrunner.testrun.instance.TestrunInstance
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface TestrunInteractionInstanceRepository: JpaRepository<TestrunInteractionInstance, UUID> {
    fun findFirstByEventDefinitionAndTestrunInstanceDocumentId(eventDefinition: TestrunEventDefinition, documentId: UUID): TestrunInteractionInstance?

    fun findByEventDefinitionAndTestrunInstanceId(eventDefinition: TestrunEventDefinition, testrunInstanceId: UUID): TestrunInteractionInstance?

    fun findAllByEventDefinition(eventDefinition: TestrunEventDefinition): List<TestrunInteractionInstance>
    fun findAllByTestrunInstance(testrunInstance: TestrunInstance): List<TestrunInteractionInstance>


}