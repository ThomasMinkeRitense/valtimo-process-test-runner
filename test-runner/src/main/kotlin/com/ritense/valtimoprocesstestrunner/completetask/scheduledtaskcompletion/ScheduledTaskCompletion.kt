package com.ritense.valtimoprocesstestrunner.completetask.scheduledtaskcompletion

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.JsonNode
import io.hypersistence.utils.hibernate.type.json.JsonType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Type
import java.util.*

@Entity
@Table(name = "testrun_scheduled_task_completion")
open class ScheduledTaskCompletion(
    @Column(name = "task_instance_id")
    val taskInstanceId: String,

    @Type(value = JsonType::class)
    @Column(name = "payload", columnDefinition = "json")
    val payload: JsonNode,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
)