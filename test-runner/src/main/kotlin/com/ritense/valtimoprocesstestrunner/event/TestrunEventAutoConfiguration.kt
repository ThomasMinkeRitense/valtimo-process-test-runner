package com.ritense.valtimoprocesstestrunner.event

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.NamedType
import com.ritense.valtimoprocesstestrunner.interaction.instance.TestrunInteractionInstanceService
import org.camunda.bpm.engine.RuntimeService
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Bean

@AutoConfiguration
class TestrunEventAutoConfiguration {
    @Bean
    fun taskCreatedEventDefinitionType(): NamedType {
        return NamedType(TaskCreatedEventDefinition::class.java, "taskCreated")
    }

    @Bean
    fun taskCreatedEventListener(
        interactionInstanceService: TestrunInteractionInstanceService,
        runtimeService: RuntimeService
    ): TaskCreatedEventListener {
        return TaskCreatedEventListener(
            interactionInstanceService,
            runtimeService
        )
    }

    @Bean
    fun testrunEventObjectMapperConfigurer(
        objectMapper: ObjectMapper,
        testrunCommandTypes: Collection<NamedType>
    ): TestrunEventObjectMapperConfigurer {
        return TestrunEventObjectMapperConfigurer(
            objectMapper, testrunCommandTypes
        )
    }
}