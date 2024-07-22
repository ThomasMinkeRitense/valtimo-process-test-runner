package com.ritense.valtimoprocesstestrunner.mapper

import com.ritense.valtimoprocesstestrunner.interaction.instance.TestrunInteractionInstance

interface EventCommandMapper<FROM, TO> {
    fun supports(fromClass: Class<*>, toClass: Class<*>): Boolean

    fun from(): Class<*>

    fun to(): Class<*>

    fun eventName(): String

    fun mapperName(): String

    fun map(from: FROM, testrunInteractionInstance: TestrunInteractionInstance): TO
}
