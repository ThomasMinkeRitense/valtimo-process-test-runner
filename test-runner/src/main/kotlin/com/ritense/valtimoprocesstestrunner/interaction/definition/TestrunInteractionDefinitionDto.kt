package com.ritense.valtimoprocesstestrunner.interaction.definition

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import jakarta.persistence.Column
import java.util.UUID

data class TestrunInteractionDefinitionDto(
    @JsonProperty
    val title: String,

    @JsonProperty("event_definition")
    val eventDefinition: JsonNode,

    @JsonProperty("command_definition")
    val commandDefinition: JsonNode
)
