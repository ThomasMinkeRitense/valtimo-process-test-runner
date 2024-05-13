package com.ritense.valtimoprocesstestrunner.interaction.instance

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.ritense.valtimoprocesstestrunner.command.TestrunCommandDefinition
import com.ritense.valtimoprocesstestrunner.event.TestrunEventDefinition
import com.ritense.valtimoprocesstestrunner.testrun.instance.TestrunInstance
import io.hypersistence.utils.hibernate.type.json.JsonType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.Type
import java.util.*

@Entity
@Table(name = "testrun_interaction_instance")
open class TestrunInteractionInstance(
    @Id
    @Column(name = "id")
    val id: UUID = UUID.randomUUID(),

    @ManyToOne
    @JoinColumn(name = "testrun_instance_id", referencedColumnName = "id")
    val testrunInstance: TestrunInstance,

    @Type(value = JsonType::class)
    @JsonTypeInfo(use= JsonTypeInfo.Id.NAME, property = "type")
    @Column(name = "event_definition", columnDefinition = "JSON", nullable = false)
    val eventDefinition: TestrunEventDefinition,

    @Type(value = JsonType::class)
    @JsonTypeInfo(use= JsonTypeInfo.Id.NAME, property = "type")
    @Column(name = "command_definition", columnDefinition = "JSON", nullable = false)
    val commandDefinition: TestrunCommandDefinition
)