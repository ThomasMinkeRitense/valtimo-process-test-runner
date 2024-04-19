package com.ritense.valtimoprocesstestrunner.completetask

import com.fasterxml.jackson.databind.JsonNode
import com.ritense.valtimoprocesstestrunner.command.TestrunCommand

class CompleteTask(
    val taskInstanceId: String,
    val payload: JsonNode
) : TestrunCommand {
}