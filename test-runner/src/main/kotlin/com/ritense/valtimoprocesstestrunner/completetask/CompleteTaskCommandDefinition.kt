package com.ritense.valtimoprocesstestrunner.completetask

import com.fasterxml.jackson.databind.JsonNode
import com.ritense.valtimoprocesstestrunner.command.TestrunCommandDefinition

data class CompleteTaskCommandDefinition(
    val taskDefinitionKey: String,
    val payload: JsonNode
): TestrunCommandDefinition {
    override fun <TO> getCommandType(): Class<TO> {
        return CompleteTask::class.java as Class<TO>
    }
}