package com.ritense.valtimoprocesstestrunner.testrun.definition

import com.ritense.valtimoprocesstestrunner.interaction.definition.TestrunInteractionDefinitionService
import com.ritense.valtimoprocesstestrunner.testrun.instance.TestrunInstanceService
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.Order
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@AutoConfiguration
@EnableJpaRepositories(basePackages = ["com.ritense.valtimoprocesstestrunner.testrun.definition"])
@EntityScan(basePackages = ["com.ritense.valtimoprocesstestrunner.testrun.definition"])
class TestrunDefinitionAutoConfiguration {
    @Bean
    fun testrunDefinitionService(
        testrunDefinitionRepository: TestrunDefinitionRepository,
        testrunInteractionDefinitionService: TestrunInteractionDefinitionService
    ): TestrunDefinitionService {
        return TestrunDefinitionServiceImpl(
          testrunDefinitionRepository,
          testrunInteractionDefinitionService
        )
    }

    @Bean
    fun testrunDefinitionResource(
        testrunDefinitionService: TestrunDefinitionService,
        testrunInstanceService: TestrunInstanceService
    ): TestrunDefinitionResource {
        return TestrunDefinitionResource(
            testrunDefinitionService,
            testrunInstanceService
        )
    }

    @Bean
    @Order(300)
    fun testrunDefinitionSecurityConfigurer(): TestrunDefinitionSecurityConfigurer {
        return TestrunDefinitionSecurityConfigurer()
    }
}
