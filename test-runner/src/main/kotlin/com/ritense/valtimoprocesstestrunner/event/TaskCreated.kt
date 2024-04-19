package com.ritense.valtimoprocesstestrunner.event

import java.util.*

data class TaskCreated(
    val taskDefinitionKey: String,
    val taskInstanceId: String,
    override val documentId: UUID
) : TestrunEvent {

    override fun eventDefinition(): TestrunEventDefinition {
        return TaskCreatedEventDefinition(taskDefinitionKey)
    }
}