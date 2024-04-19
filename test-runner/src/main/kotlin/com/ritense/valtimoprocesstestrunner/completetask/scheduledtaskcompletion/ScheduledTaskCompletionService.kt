package com.ritense.valtimoprocesstestrunner.completetask.scheduledtaskcompletion

import com.fasterxml.jackson.databind.JsonNode
import com.ritense.authorization.AuthorizationContext.Companion.runWithoutAuthorization
import com.ritense.form.service.FormSubmissionService
import com.ritense.processlink.service.ProcessLinkActivityService
import com.ritense.valtimo.service.CamundaProcessService
import com.ritense.valtimo.service.CamundaTaskService
import com.ritense.valtimoprocesstestrunner.completetask.CompleteTask
import mu.KotlinLogging
import org.camunda.bpm.engine.TaskService
import org.springframework.scheduling.annotation.Scheduled
import java.util.*

class ScheduledTaskCompletionService(
    private val repository: ScheduledTaskCompletionRepository,
    private val formSubmissionService: FormSubmissionService,
    private val processLinkActivityService: ProcessLinkActivityService,
    private val camundaTaskService: CamundaTaskService,
    private val camundaProcessService: CamundaProcessService
) {
    @Scheduled(fixedDelay = 2000L)
    fun completeFirstTask() {
        repository.findFirstByOrderById().ifPresentOrElse(
            { task ->
                runWithoutAuthorization {
                    completeTask(task)
                }
            },
            { logger.info { "No tasks scheduled for completion" }}
        )
    }

    fun scheduleTaskCompletion(
        taskInstanceId: String,
        payload: JsonNode
    ) {
        logger.info { "Scheduling task completion for task ${taskInstanceId}" }
        val scheduledTaskCompletion = ScheduledTaskCompletion(
            taskInstanceId = taskInstanceId,
            payload = payload
        )

        repository.save(scheduledTaskCompletion)
    }

    private fun getProcessLinkId(taskInstanceId: String): UUID {
        return processLinkActivityService.openTask(UUID.fromString(taskInstanceId)).processLinkId
    }


    private fun completeTask(scheduledTaskCompletion: ScheduledTaskCompletion) {
        logger.info { "Completing task ${scheduledTaskCompletion.taskInstanceId}" }

        formSubmissionService.handleSubmission(
            processLinkId = getProcessLinkId(scheduledTaskCompletion.taskInstanceId),
            documentDefinitionName = null,
            documentId = getDocumentId(scheduledTaskCompletion.taskInstanceId),
            taskInstanceId = scheduledTaskCompletion.taskInstanceId,
            formData = scheduledTaskCompletion.payload
        );
        repository.delete(scheduledTaskCompletion)
    }

    private fun getDocumentId(taskInstanceId: String): String {
        val processInstanceId = getProcessInstanceId(taskInstanceId)
        return camundaProcessService
            .findProcessInstanceById(processInstanceId)
            .orElseThrow()
            .businessKey
    }

    private fun getProcessInstanceId(taskInstanceId: String): String {
        return camundaTaskService
            .findTaskById(taskInstanceId)
            .getProcessInstanceId()
    }

    companion object {
        val logger = KotlinLogging.logger {}
    }
}