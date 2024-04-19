package com.ritense.valtimoprocesstestrunner.testrun.definition

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import java.util.UUID

data class TestrunDefinitionDto(
    val id: UUID?,
    val title: String,

    @JsonProperty("process_definition_key")
    val processDefinitionKey: String,

    @JsonProperty("document_definition_name")
    val documentDefinitionName: String,
    val payload: JsonNode
) {
    companion object {
        fun toEntity(testrunDefinitionDto: TestrunDefinitionDto): TestrunDefinition {
            return TestrunDefinition(
                title = testrunDefinitionDto.title,
                documentDefinitionName = testrunDefinitionDto.documentDefinitionName,
                processDefinitionKey = testrunDefinitionDto.processDefinitionKey,
                payload = testrunDefinitionDto.payload
            )
        }
    }
}