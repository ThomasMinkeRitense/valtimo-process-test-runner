package com.ritense.valtimoprocesstestrunner.completetask

import com.fasterxml.jackson.databind.ObjectMapper
import com.ritense.form.service.FormSubmissionService
import com.ritense.processlink.service.ProcessLinkActivityService
import com.ritense.valtimoprocesstestrunner.command.TestrunCommand
import com.ritense.valtimoprocesstestrunner.command.TestrunCommandHandler
import com.ritense.valtimoprocesstestrunner.completetask.scheduledtaskcompletion.ScheduledTaskCompletionService
import java.util.UUID

class CompleteTaskCommandHandler(
    private val scheduledTaskCompletionService: ScheduledTaskCompletionService
): TestrunCommandHandler {
    override fun handle(command: TestrunCommand): Any {
        command as CompleteTask
        return scheduledTaskCompletionService.scheduleTaskCompletion(
            command.taskInstanceId,
            command.payload
        );
    }

    override fun canHandle(command: TestrunCommand): Boolean {
        return command is CompleteTask
    }


}