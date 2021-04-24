package com.deveficiente.casadocodigo.purchase.validation

import com.deveficiente.casadocodigo.country.Country
import com.deveficiente.casadocodigo.purchase.PurchaseRequest
import com.deveficiente.casadocodigo.state.State
import javax.persistence.EntityManager
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class StateBelongsToCountryValidator(
        val entityManager: EntityManager
) : ConstraintValidator<StateBelongsToCountry, PurchaseRequest> {

    override fun initialize(constraint: StateBelongsToCountry) {
        // nothing to initialize
    }

    override fun isValid(value: PurchaseRequest, context: ConstraintValidatorContext): Boolean {
        if (value.stateId == null) {
            return true
        }

        val country = entityManager.find(Country::class.java, value.countryId)
        val state = entityManager.find(State::class.java, value.stateId)

        return state?.country == country
    }

}
