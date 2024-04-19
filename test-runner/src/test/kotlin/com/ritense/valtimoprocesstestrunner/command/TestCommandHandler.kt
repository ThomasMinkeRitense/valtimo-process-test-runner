package com.ritense.valtimoprocesstestrunner.command

import com.ritense.valtimoprocesstestrunner.command.TestrunCommand
import com.ritense.valtimoprocesstestrunner.command.TestrunCommandHandler

class TestCommandHandler: TestrunCommandHandler {
    override fun handle(command: TestrunCommand): Any {
        return true;
    }

    override fun canHandle(command: TestrunCommand): Boolean {
        return command is TestCommand
    }
}