package com.ritense.valtimoprocesstestrunner.event

data class TaskCreatedEventDefinition(
    val taskDefinitionKey: String
): TestrunEventDefinition