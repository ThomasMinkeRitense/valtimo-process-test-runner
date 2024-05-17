package com.ritense.valtimoprocesstestrunner.testrun.instance

import com.ritense.authorization.annotation.RunWithoutAuthorization
import com.ritense.valtimo.contract.annotation.SkipComponentScan
import com.ritense.valtimoprocesstestrunner.testrun.definition.TestrunDefinitionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@SkipComponentScan
@RequestMapping("/api/management/v1/test-run/definition")
class TestrunInstanceResource(
    private val definitionService: TestrunDefinitionService,
    private val instanceService: TestrunInstanceService
) {
    @RunWithoutAuthorization
    @PostMapping("/{definitionId}/start")
    fun startTestrun(@PathVariable definitionId: UUID): TestrunInstance {
        val testrunDefinition = definitionService.getDefinition(definitionId)
        return instanceService.startTestrun(testrunDefinition)
    }
}
