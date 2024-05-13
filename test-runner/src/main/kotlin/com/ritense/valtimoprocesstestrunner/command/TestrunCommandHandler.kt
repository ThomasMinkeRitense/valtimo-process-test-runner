package com.ritense.valtimoprocesstestrunner.command

interface TestrunCommandHandler {
    fun handle(command: TestrunCommand): Any

    fun canHandle(command: TestrunCommand): Boolean
}