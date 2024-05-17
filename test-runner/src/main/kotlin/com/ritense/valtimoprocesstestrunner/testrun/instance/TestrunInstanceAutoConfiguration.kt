package com.ritense.valtimoprocesstestrunner.testrun.instance

import com.ritense.form.service.FormSubmissionService
import com.ritense.processlink.service.ProcessLinkActivityService
import com.ritense.valtimo.camunda.service.CamundaRepositoryService
import com.ritense.valtimoprocesstestrunner.interaction.definition.TestrunInteractionDefinitionService
import com.ritense.valtimoprocesstestrunner.interaction.instance.TestrunInteractionInstanceService
import com.ritense.valtimoprocesstestrunner.testrun.definition.TestrunDefinitionResource
import com.ritense.valtimoprocesstestrunner.testrun.definition.TestrunDefinitionSecurityConfigurer
import com.ritense.valtimoprocesstestrunner.testrun.definition.TestrunDefinitionService
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.Order
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@AutoConfiguration
@EnableJpaRepositories(basePackages = ["com.ritense.valtimoprocesstestrunner.testrun.instance"])
@EntityScan(basePackages = ["com.ritense.valtimoprocesstestrunner.testrun.instance"])
class TestrunInstanceAutoConfiguration {
    @Bean
    fun testrunInstanceService(
        testrunInstanceRepository: TestrunInstanceRepository,
        camundaRepositoryService: CamundaRepositoryService,
        formSubmissionService: FormSubmissionService,
        processLinkActivityService: ProcessLinkActivityService,
        testrunInteractionDefinitionService: TestrunInteractionDefinitionService,
        testrunInteractionInstanceService: TestrunInteractionInstanceService
    ): TestrunInstanceService {
        return TestrunInstanceServiceImpl(
            instanceRepository = testrunInstanceRepository,
            camundaRepositoryService = camundaRepositoryService,
            formSubmissionService = formSubmissionService,
            processLinkActivityService = processLinkActivityService,
            testrunInteractionDefinitionService = testrunInteractionDefinitionService,
            testrunInteractionInstanceService = testrunInteractionInstanceService
        )
    }

    @Bean
    @Order(300)
    fun testrunInstanceSecurityConfigurer(): TestrunInstanceSecurityConfigurer {
        return TestrunInstanceSecurityConfigurer()
    }
}
