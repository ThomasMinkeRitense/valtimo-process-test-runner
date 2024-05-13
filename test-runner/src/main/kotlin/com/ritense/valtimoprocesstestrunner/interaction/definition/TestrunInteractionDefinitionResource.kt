package com.ritense.valtimoprocesstestrunner.interaction.definition

import com.ritense.authorization.annotation.RunWithoutAuthorization
import com.ritense.valtimo.contract.annotation.SkipComponentScan
import com.ritense.valtimoprocesstestrunner.testrun.instance.TestrunInstance
import com.ritense.valtimoprocesstestrunner.testrun.instance.TestrunInstanceService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.stream.Collectors

@RestController
@SkipComponentScan
@RequestMapping("/api/management/v1/test-run/definition/{definitionId}/interaction-definition")
class TestrunInteractionDefinitionResource(
    private val service: TestrunInteractionDefinitionService,
) {
    @PostMapping
    fun createDefinition(@PathVariable definitionId: UUID, @RequestBody testrunInteractionDefinitionDto: TestrunInteractionDefinitionDto): TestrunInteractionDefinitionDto {
        return service.createInteractionDefinition(definitionId, testrunInteractionDefinitionDto).toDto()
    }

    @GetMapping
    fun getDefinitions(@PathVariable definitionId: UUID): List<TestrunInteractionDefinitionDto> {
        return service
            .getInteractionDefinitionsByTestrunDefinition(definitionId)
            .map { definition -> definition.toDto()}
    }
}