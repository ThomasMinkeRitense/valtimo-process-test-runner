package com.ritense.valtimoprocesstestrunner.mapper

import com.ritense.valtimoprocesstestrunner.command.TestrunCommandService
import com.ritense.valtimoprocesstestrunner.mapper.TestrunMapperService
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.Order
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

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
}