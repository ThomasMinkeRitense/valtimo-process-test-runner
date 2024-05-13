package com.ritense.valtimoprocesstestrunner.interaction.instance

import com.ritense.valtimoprocesstestrunner.event.TestrunEvent
import java.util.UUID

interface TestrunInteractionInstanceService {
    fun handleEvent(event: TestrunEvent)

    fun createInteractionInstance(interactionInstance: TestrunInteractionInstance)
}