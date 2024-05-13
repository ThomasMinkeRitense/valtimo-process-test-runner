package com.ritense.valtimoprocesstestrunner.command

import org.springframework.stereotype.Component

class TestrunCommandService(
    private val commandHandlers: List<TestrunCommandHandler>
) {
    fun handleCommand(command: TestrunCommand): Any {
        return getCommandHandler(command)!!.handle(command);
    }

    private fun getCommandHandler(command: TestrunCommand): TestrunCommandHandler? {
        return commandHandlers.firstOrNull {
            it.canHandle(command)
        }
    }
}