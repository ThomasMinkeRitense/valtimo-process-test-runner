package com.ritense.valtimoprocesstestrunner.mapper

import com.ritense.valtimoprocesstestrunner.completetask.CompleteTask
import com.ritense.valtimoprocesstestrunner.completetask.CompleteTaskCommandDefinition
import com.ritense.valtimoprocesstestrunner.event.TaskCreated
import com.ritense.valtimoprocesstestrunner.interaction.instance.TestrunInteractionInstance

class TaskCreatedCompleteTaskMapper: EventCommandMapper<TaskCreated, CompleteTask> {
  override fun supports(fromClass: Class<*>, toClass: Class<*>): Boolean {
      return fromClass == TaskCreated::class.java && toClass == CompleteTask::class.java
  }

  override fun from(): Class<*> {
    return TaskCreated::class.java
  }

  override fun to(): Class<*> {
    return CompleteTask::class.java
  }

  override fun eventName(): String {
    return TaskCreated.readableName()
  }

  override fun mapperName(): String {
    return "When a task is created, complete the task"
  }

  override fun map(
      from: TaskCreated,
      testrunInteractionInstance: TestrunInteractionInstance
  ): CompleteTask {
      val commandDefinition = testrunInteractionInstance.commandDefinition as CompleteTaskCommandDefinition
      return CompleteTask(
          from.taskInstanceId,
          commandDefinition.payload
      )
  }
}
