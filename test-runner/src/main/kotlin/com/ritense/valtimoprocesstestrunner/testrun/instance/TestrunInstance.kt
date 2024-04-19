package com.ritense.valtimoprocesstestrunner.testrun.instance

import com.ritense.valtimoprocesstestrunner.testrun.definition.TestrunDefinition
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "testrun_instance")
open class TestrunInstance (
    @Id
    @Column(name = "id")
    val id: UUID = UUID.randomUUID(),

    @ManyToOne
    @JoinColumn(name = "testrun_definition_id", referencedColumnName = "id")
    val definition: TestrunDefinition,

    @Column(name = "document_id", nullable = false)
    var documentId: UUID?
) {
    fun assignDocument(documentId: UUID) {
        this.documentId = documentId
    }
}