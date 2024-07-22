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

    fun getEventtypes(): Map<String, String> {
      return this.mappers
        .map { it.from().canonicalName to it.eventName()}
        .toMap()
    }

    fun getMappersForEvent(eventtype: String): Map<String, String> {
      return this.mappers
        .filter { it.from().canonicalName.equals(eventtype) }
        .map { it.javaClass.canonicalName to it.mapperName() }
        .toMap()
    }

    private fun <FROM, TO> getMapper(
        from: Class<FROM>,
        to: Class<TO>): EventCommandMapper<FROM, TO> {
      @Suppress("UNCHECKED_CAST")
      return mappers.firstOrNull {
          it.supports(from, to)
      } as EventCommandMapper<FROM, TO>
    }
}
