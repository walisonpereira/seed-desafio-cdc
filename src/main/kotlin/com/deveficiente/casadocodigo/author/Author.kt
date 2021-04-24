package com.deveficiente.casadocodigo.author

import java.time.OffsetDateTime
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "authors", uniqueConstraints = [
    UniqueConstraint(columnNames = ["email"], name = "uk_author_email")
])
data class Author(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @field:NotBlank
        val name: String,

        @field:NotBlank
        @field:Email
        val email: String,

        @field:NotBlank
        @field:Size(max = 400)
        val description: String,

        val createdAt: OffsetDateTime = OffsetDateTime.now()
)
