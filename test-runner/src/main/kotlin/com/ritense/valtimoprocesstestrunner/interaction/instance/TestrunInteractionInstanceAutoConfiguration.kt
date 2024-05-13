package com.ritense.valtimoprocesstestrunner.interaction.instance

import com.ritense.valtimoprocesstestrunner.command.TestrunCommandService
import com.ritense.valtimoprocesstestrunner.mapper.TestrunMapperService
import com.ritense.valtimoprocesstestrunner.testrun.instance.TestrunInstanceRepository
import com.ritense.valtimoprocesstestrunner.testrun.instance.TestrunInstanceService
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@AutoConfiguration
@EnableJpaRepositories(basePackages = ["com.ritense.valtimoprocesstestrunner.interaction.instance"])
@EntityScan(basePackages = ["com.ritense.valtimoprocesstestrunner.interaction.instance"])
class TestrunInteractionInstanceAutoConfiguration {
    @Bean
    fun testrunInteractionInstanceService(
        testrunCommandService: TestrunCommandService,
        testrunInteractionInstanceRepository: TestrunInteractionInstanceRepository,
        testrunMapperService: TestrunMapperService,
        testrunInstanceRepository: TestrunInstanceRepository
    ): TestrunInteractionInstanceServiceImpl {
        return TestrunInteractionInstanceServiceImpl(
            commandService = testrunCommandService,
            testrunInteractionInstanceRepository = testrunInteractionInstanceRepository,
            mapperService = testrunMapperService,
            testrunInstanceRepository = testrunInstanceRepository
        )
    }
}