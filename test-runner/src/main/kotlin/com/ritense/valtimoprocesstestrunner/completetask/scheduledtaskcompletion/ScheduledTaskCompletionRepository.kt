package com.ritense.valtimoprocesstestrunner.completetask.scheduledtaskcompletion

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ScheduledTaskCompletionRepository: JpaRepository<ScheduledTaskCompletion, UUID> {
    fun findFirstByOrderById(): Optional<ScheduledTaskCompletion>
}