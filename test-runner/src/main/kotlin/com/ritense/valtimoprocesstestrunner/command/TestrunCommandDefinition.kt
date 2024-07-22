package com.ritense.valtimoprocesstestrunner.command

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "type", visible = true)
interface TestrunCommandDefinition {
    fun <TO> getCommandType(): Class<TO>
}
