package com.deveficiente.casadocodigo.category

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "categories", uniqueConstraints = [
    UniqueConstraint(columnNames = ["name"], name = "uk_category_name")
])
data class Category(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @field:NotBlank
        val name: String
)
