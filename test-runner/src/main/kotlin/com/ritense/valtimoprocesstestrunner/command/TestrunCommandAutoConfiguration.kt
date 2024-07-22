package com.ritense.valtimoprocesstestrunner.command

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.NamedType
import com.ritense.form.service.FormSubmissionService
import com.ritense.processlink.service.ProcessLinkActivityService
import com.ritense.valtimoprocesstestrunner.completetask.CompleteTaskCommandDefinition
import com.ritense.valtimoprocesstestrunner.completetask.CompleteTaskCommandHandler
import com.ritense.valtimoprocesstestrunner.completetask.scheduledtaskcompletion.ScheduledTaskCompletionService
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Bean

@AutoConfiguration
class TestrunCommandAutoConfiguration {
    @Bean
    fun completeTaskCommandHandler(
        scheduledTaskCompletionService: ScheduledTaskCompletionService
    ): CompleteTaskCommandHandler {
        return CompleteTaskCommandHandler(
            scheduledTaskCompletionService = scheduledTaskCompletionService
        )
    }

    @Bean
    fun testrunCommandService(
        commandHandlers: List<TestrunCommandHandler>
    ): TestrunCommandService {
        return TestrunCommandService(commandHandlers)
    }

    @Bean
    fun completeTaskCommandDefinitionType(): NamedType {
        return NamedType(CompleteTaskCommandDefinition::class.java, "CompleteTaskCommandDefinition")
    }

    @Bean
    fun testrunCommandObjectMapperConfigurer(
        objectMapper: ObjectMapper,
        testrunCommandTypes: Collection<NamedType>
    ): TestrunCommandObjectMapperConfigurer {
        return TestrunCommandObjectMapperConfigurer(
            objectMapper, testrunCommandTypes
        )
    }
}
