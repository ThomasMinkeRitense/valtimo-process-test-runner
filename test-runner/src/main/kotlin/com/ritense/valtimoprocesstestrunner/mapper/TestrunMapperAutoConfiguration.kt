package com.ritense.valtimoprocesstestrunner.mapper

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.Order

@AutoConfiguration
class TestrunMapperAutoConfiguration {

    @Bean
    fun taskCreatedCompleteTaskMapper(): TaskCreatedCompleteTaskMapper {
        return TaskCreatedCompleteTaskMapper()
    }
    @Bean
    fun testrunMapperService(
        commandMappers: List<EventCommandMapper<*,*>>
    ): TestrunMapperService {
        return TestrunMapperService(
            commandMappers
        )
    }

  @Bean
  fun testrunMapperResource(
    testrunMapperService: TestrunMapperService
  ): TestrunMapperResource {
    return TestrunMapperResource(
      testrunMapperService
    )
  }

  @Bean
  @Order(300)
  fun testrunMapperSecurityConfigurer(): TestrunMapperSecurityConfigurer {
    return TestrunMapperSecurityConfigurer()
  }
}
