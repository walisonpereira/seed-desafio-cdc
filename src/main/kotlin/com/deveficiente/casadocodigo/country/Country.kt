package com.deveficiente.casadocodigo.country

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "countries", uniqueConstraints = [
    UniqueConstraint(columnNames = ["name"], name = "uk_country_name")
])
data class Country(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @field:NotBlank
        val name: String
)
