package com.ritense.valtimoprocesstestrunner.event

import com.ritense.valtimoprocesstestrunner.interaction.instance.TestrunInteractionInstanceService
import mu.KotlinLogging
import org.camunda.bpm.engine.ActivityTypes
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.delegate.DelegateTask
import org.camunda.bpm.engine.delegate.TaskListener
import org.camunda.bpm.extension.reactor.bus.CamundaSelector
import org.camunda.bpm.extension.reactor.spring.listener.ReactorTaskListener
import java.util.*

@CamundaSelector(type = ActivityTypes.TASK_USER_TASK, event = TaskListener.EVENTNAME_CREATE)
class TaskCreatedEventListener(
    private val interactionInstanceService: TestrunInteractionInstanceService,
    private val runtimeService: RuntimeService
): ReactorTaskListener() {
    override fun notify(delegateTask: DelegateTask) {
        logger.info { "Handling 'user task created' event" }
        val taskCreated = TaskCreated(
            delegateTask.taskDefinitionKey,
            delegateTask.id,
            getBusinessKey(delegateTask)
        )
        interactionInstanceService.handleEvent(taskCreated)
    }

    fun getBusinessKey(delegateTask: DelegateTask): UUID {
        return UUID.fromString(runtimeService
            .createProcessInstanceQuery()
            .processInstanceId(delegateTask.processInstanceId)
            .singleResult()!!.businessKey)
    }

    companion object {
        val logger = KotlinLogging.logger {}
    }
}