package com.ritense.valtimoprocesstestrunner.mapper

import com.ritense.valtimo.contract.annotation.SkipComponentScan
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@SkipComponentScan
@RequestMapping("/api/management/v1/test-run/mapper")
class TestrunMapperResource(
  private val service: TestrunMapperService
) {
  @GetMapping("/eventtypes")
  fun getEventTypes(): Map<String, String> {
    return service.getEventtypes()
  }

  @GetMapping("/eventtype/{eventtype}")
  fun getMappersForEvent(@PathVariable eventtype: String): Map<String, String> {
    return service.getMappersForEvent(eventtype)
  }
}
