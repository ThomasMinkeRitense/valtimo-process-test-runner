package com.ritense.valtimoprocesstestrunner.command

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.kotlin.verify

class TestrunCommandServiceTest{
    lateinit var service: TestrunCommandService

    @Spy
    lateinit var handler: TestCommandHandler

    @Spy
    lateinit var handlers: ArrayList<TestrunCommandHandler>

    @BeforeEach
    fun beforeAll() {
        MockitoAnnotations.openMocks(this)
        handler = mock(TestCommandHandler::class.java);
        handlers.add(handler);
        service = TestrunCommandService(handlers)
    }
    @Test
    fun `Should be handled by the correct handler`() {
        val testCommand = TestCommand()
        `when`(handler.canHandle(testCommand)).thenCallRealMethod()

        service.handleCommand(testCommand);

        verify(handler).handle(testCommand);
    }
}