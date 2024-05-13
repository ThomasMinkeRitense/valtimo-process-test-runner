package com.ritense.valtimoprocesstestrunner.mapper

import com.ritense.valtimoprocesstestrunner.interaction.instance.TestrunInteractionInstance

interface EventCommandMapper<FROM, TO> {
    fun supports(fromClass: Class<*>, toClass: Class<*>): Boolean

    fun map(from: FROM, testrunInteractionInstance: TestrunInteractionInstance): TO
}