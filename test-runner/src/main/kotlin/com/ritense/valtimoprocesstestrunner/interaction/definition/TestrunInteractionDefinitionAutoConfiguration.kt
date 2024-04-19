package com.ritense.valtimoprocesstestrunner.interaction.definition

import com.fasterxml.jackson.databind.ObjectMapper
import com.ritense.valtimoprocesstestrunner.command.TestrunCommandService
import com.ritense.valtimoprocesstestrunner.mapper.TestrunMapperService
import com.ritense.valtimoprocesstestrunner.testrun.definition.TestrunDefinitionSecurityConfigurer
import com.ritense.valtimoprocesstestrunner.testrun.definition.TestrunDefinitionService
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.Order
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@AutoConfiguration
@EnableJpaRepositories(basePackages = ["com.ritense.valtimoprocesstestrunner.interaction.definition"])
@EntityScan(basePackages = ["com.ritense.valtimoprocesstestrunner.interaction.definition"])
class TestrunInteractionDefinitionAutoConfiguration {
    @Bean
    fun testrunInteractionDefinitionService(
        testrunDefinitionService: TestrunDefinitionService,
        testrunInteractionDefinitionRepository: TestrunInteractionDefinitionRepository,
        objectMapper: ObjectMapper
    ): TestrunInteractionDefinitionService {
        return TestrunInteractionDefinitionServiceImpl(
            testrunDefinitionService = testrunDefinitionService,
            repository = testrunInteractionDefinitionRepository,
            objectMapper = objectMapper
        )
    }

    @Bean
    fun testrunInteractionDefinitionResource(
        testrunInteractionDefinitionService: TestrunInteractionDefinitionService
    ): TestrunInteractionDefinitionResource {
        return TestrunInteractionDefinitionResource(testrunInteractionDefinitionService)
    }

    @Bean
    @Order(300)
    fun testrunInteractionDefinitionSecurityConfigurer(): TestrunInteractionDefinitionSecurityConfigurer {
        return TestrunInteractionDefinitionSecurityConfigurer()
    }
}