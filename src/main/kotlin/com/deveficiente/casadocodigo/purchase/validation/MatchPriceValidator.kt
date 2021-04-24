package com.deveficiente.casadocodigo.purchase.validation

import com.deveficiente.casadocodigo.purchase.LineItem
import com.deveficiente.casadocodigo.purchase.OrderRequest
import java.math.BigDecimal
import javax.persistence.EntityManager
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class MatchPriceValidator(
        val entityManager: EntityManager
) : ConstraintValidator<MatchPrice, OrderRequest?> {

    override fun initialize(constraint: MatchPrice) {
        // nothing to initialize
    }

    override fun isValid(value: OrderRequest?, context: ConstraintValidatorContext): Boolean {
        if (value == null) {
            return true
        }

        val total = value.toModel(entityManager)
                .map(LineItem::amount)
                .fold(BigDecimal.ZERO, BigDecimal::add)

        return value.amount.compareTo(total) == 0
    }

}
