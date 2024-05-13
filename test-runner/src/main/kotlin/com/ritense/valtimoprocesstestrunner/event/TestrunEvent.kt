package com.ritense.valtimoprocesstestrunner.event

import java.util.UUID

interface TestrunEvent{
    val documentId: UUID
    fun eventDefinition(): TestrunEventDefinition
}