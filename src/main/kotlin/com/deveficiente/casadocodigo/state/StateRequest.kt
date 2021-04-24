package com.deveficiente.casadocodigo.state

import com.deveficiente.casadocodigo.country.Country
import com.deveficiente.casadocodigo.shared.validation.Exists
import com.deveficiente.casadocodigo.shared.validation.Unique
import javax.persistence.EntityManager
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class StateRequest(
        @field:NotBlank
        @field:Unique(entityClass = State::class, entityField = "name")
        val name: String,

        @field:NotNull
        @field:Exists(entityClass = Country::class)
        val countryId: Long
) {
    fun toModel(entityManager: EntityManager): State {
        val country = entityManager.find(Country::class.java, countryId)
        return State(name = name, country = country)
    }
}
