package com.ritense.valtimoprocesstestrunner.testrun.instance

import com.ritense.valtimoprocesstestrunner.testrun.definition.TestrunDefinition
import com.ritense.valtimoprocesstestrunner.testrun.definition.TestrunDefinitionDto
import java.util.*

interface TestrunInstanceService {
    fun startTestrun(definition: TestrunDefinition): TestrunInstance

    fun getByDocumentId(documentId: UUID): Optional<TestrunInstance>
}