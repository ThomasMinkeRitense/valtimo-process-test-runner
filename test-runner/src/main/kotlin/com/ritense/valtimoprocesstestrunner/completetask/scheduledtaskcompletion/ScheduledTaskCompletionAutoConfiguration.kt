package com.ritense.valtimoprocesstestrunner.completetask.scheduledtaskcompletion

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.NamedType
import com.ritense.form.service.FormSubmissionService
import com.ritense.processlink.service.ProcessLinkActivityService
import com.ritense.valtimo.service.CamundaProcessService
import com.ritense.valtimo.service.CamundaTaskService
import com.ritense.valtimoprocesstestrunner.completetask.CompleteTaskCommandDefinition
import com.ritense.valtimoprocesstestrunner.completetask.CompleteTaskCommandHandler
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@AutoConfiguration
@EnableScheduling
@EnableJpaRepositories(basePackages = ["com.ritense.valtimoprocesstestrunner.completetask.scheduledtaskcompletion"])
@EntityScan(basePackages = ["com.ritense.valtimoprocesstestrunner.completetask.scheduledtaskcompletion"])
class ScheduledTaskCompletionAutoConfiguration {
    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE)
    fun scheduledTaskCompletionService(
        scheduledTaskCompletionRepository: ScheduledTaskCompletionRepository,
        formSubmissionService: FormSubmissionService,
        processLinkActivityService: ProcessLinkActivityService,
        camundaTaskService: CamundaTaskService,
        camundaProcessService: CamundaProcessService
    ): ScheduledTaskCompletionService {
        return ScheduledTaskCompletionService(
            scheduledTaskCompletionRepository,
            formSubmissionService,
            processLinkActivityService,
            camundaTaskService,
            camundaProcessService
        );
    }
}