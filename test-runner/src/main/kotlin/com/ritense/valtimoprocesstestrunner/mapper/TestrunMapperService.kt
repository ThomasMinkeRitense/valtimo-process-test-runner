package com.ritense.valtimoprocesstestrunner.mapper

import com.ritense.valtimoprocesstestrunner.event.TestrunEvent
import com.ritense.valtimoprocesstestrunner.interaction.instance.TestrunInteractionInstance
import com.ritense.valtimoprocesstestrunner.testrun.instance.TestrunInstance

class TestrunMapperService(
    private val mappers: List<EventCommandMapper<*, *>>
) {
    fun <TO> eventToCommand(
        event: TestrunEvent,
        to: Class<TO>,
        interactionInstance: TestrunInteractionInstance): TO {
        val mapper = getMapper(event.javaClass, to)
        return mapper.map(event, interactionInstance);
    }
    private fun <FROM, TO> getMapper(
        from: Class<FROM>,
        to: Class<TO>): EventCommandMapper<FROM, TO> {
        return mappers.firstOrNull {
            it.supports(from, to)
        } as EventCommandMapper<FROM, TO>
    }
}