package com.ritense.valtimoprocesstestrunner.testrun.definition

import com.ritense.authorization.annotation.RunWithoutAuthorization
import com.ritense.valtimo.contract.annotation.SkipComponentScan
import com.ritense.valtimoprocesstestrunner.testrun.instance.TestrunInstance
import com.ritense.valtimoprocesstestrunner.testrun.instance.TestrunInstanceService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@SkipComponentScan
@RequestMapping("/api/management/v1/test-run/definition")
class TestrunDefinitionResource(
    private val service: TestrunDefinitionService,
    private val instanceService: TestrunInstanceService
) {
    @GetMapping
    fun getAllDefinitions(): List<TestrunDefinitionDto> {
        return service
            .getAllDefinitions()
            .stream()
            .map { it.toDto() }
            .toList()
    }

    @GetMapping("/{definitionId}")
    fun getDefinitionWithInteractions(@PathVariable definitionId: UUID): TestrunDefinitionDto {
      return service.getDefinition(definitionId).toDto()
    }

    @PostMapping
    fun createDefinition(@RequestBody testrunDefinitionDto: TestrunDefinitionDto): TestrunDefinitionDto {
        return service.createTestrunDefinition(testrunDefinitionDto).toDto()
    }

    @PutMapping
    fun updateDefinition(@RequestBody testrunDefinitionDto: TestrunDefinitionDto): TestrunDefinitionDto {
        return service.updateTestrunDefinition(testrunDefinitionDto).toDto()
    }

    @RunWithoutAuthorization
    @PostMapping("/{definitionId}/start")
    fun startTestrun(@PathVariable definitionId: UUID): TestrunInstance {
        val testrunDefinition = service.getDefinition(definitionId)
        return instanceService.startTestrun(testrunDefinition)
    }

    @GetMapping("/{definitionId}/instance")
    fun getInstancesForDefinition(@PathVariable definitionId: UUID): List<TestrunInstance> {
      val testrunDefinition = service.getDefinition(definitionId)
      return instanceService.getByDefinition(testrunDefinition)
    }
}
