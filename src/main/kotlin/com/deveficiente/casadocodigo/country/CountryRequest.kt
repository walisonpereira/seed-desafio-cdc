package com.deveficiente.casadocodigo.country

import com.deveficiente.casadocodigo.shared.validation.Unique
import javax.validation.constraints.NotBlank

class CountryRequest(
        @field:NotBlank
        @field:Unique(entityClass = Country::class, entityField = "name")
        val name: String
) {
    fun toModel() = Country(name = name)
}
