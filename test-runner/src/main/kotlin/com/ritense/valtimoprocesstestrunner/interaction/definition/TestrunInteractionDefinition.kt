package com.ritense.valtimoprocesstestrunner.interaction.definition

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.ObjectMapper
import com.ritense.valtimoprocesstestrunner.command.TestrunCommandDefinition
import com.ritense.valtimoprocesstestrunner.event.TestrunEventDefinition
import com.ritense.valtimoprocesstestrunner.testrun.definition.TestrunDefinition
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
@Table(name = "testrun_interaction_definition")
open class TestrunInteractionDefinition(
    @Id
    @Column(name = "id")
    val id: UUID = UUID.randomUUID(),

    @Column(name = "title", nullable = false)
    val title: String,

    @ManyToOne
    @JoinColumn(name = "testrun_definition_id", referencedColumnName = "id")
    val testrunDefinition: TestrunDefinition,

    @Type(value = JsonType::class)
    @JsonTypeInfo(use= JsonTypeInfo.Id.NAME, property = "type")
    @Column(name = "event_definition", columnDefinition = "JSON", nullable = false)
    val eventDefinition: TestrunEventDefinition,

    @Type(value = JsonType::class)
    @JsonTypeInfo(use= JsonTypeInfo.Id.NAME, property = "type")
    @Column(name = "command_definition", columnDefinition = "JSON", nullable = false)
    val commandDefinition: TestrunCommandDefinition
) {
    fun toDto(): TestrunInteractionDefinitionDto {
        val objectMapper = ObjectMapper()
        return TestrunInteractionDefinitionDto(
            id = this.id,
            title = this.title,
            eventDefinition = objectMapper.valueToTree(this.eventDefinition),
            commandDefinition = objectMapper.valueToTree(this.commandDefinition)
        )
    }
}
