package com.ritense.valtimoprocesstestrunner.testrun.definition

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import com.ritense.valtimoprocesstestrunner.interaction.definition.TestrunInteractionDefinition
import io.hypersistence.utils.hibernate.type.json.JsonType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.Type
import java.util.*

@Entity
@Table(name = "testrun_definition")
open class TestrunDefinition(
    @Id
    @Column(name = "id")
    val id: UUID = UUID.randomUUID(),

    @Column(name = "title", nullable = false)
    val title: String,

    @Column(name = "process_definition_key")
    @JsonProperty("process_definition_key")
    val processDefinitionKey: String,

    @Column(name = "document_definition_name")
    @JsonProperty("document_definition_name")
    val documentDefinitionName: String,

    @Type(value = JsonType::class)
    @Column(name = "payload", columnDefinition = "json")
    val payload: JsonNode,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "testrunDefinition")
    var interactions: MutableList<TestrunInteractionDefinition> = mutableListOf()
) {
    fun toDto(): TestrunDefinitionDto {
        return TestrunDefinitionDto(
            id = this.id,
            title = this.title,
            documentDefinitionName = this.documentDefinitionName,
            processDefinitionKey = this.processDefinitionKey,
            payload = this.payload,
            interactions = this.interactions.map { it.toDto() }
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TestrunDefinition) return false

        if (id != other.id) return false
        if (title != other.title) return false
        if (processDefinitionKey != other.processDefinitionKey) return false
        if (documentDefinitionName != other.documentDefinitionName) return false
        if (payload != other.payload) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + processDefinitionKey.hashCode()
        result = 31 * result + documentDefinitionName.hashCode()
        result = 31 * result + payload.hashCode()
        return result
    }
}
