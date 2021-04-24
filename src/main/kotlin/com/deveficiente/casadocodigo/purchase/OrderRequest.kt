package com.deveficiente.casadocodigo.purchase

import java.math.BigDecimal
import javax.persistence.EntityManager
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

class OrderRequest(
        @field:Positive
        @field:NotNull
        val amount: BigDecimal,

        @field:Size(min = 1)
        @Valid
        val items: List<LineItemRequest> = arrayListOf()
) {
    fun toModel(entityManager: EntityManager): Set<LineItem> {
        return this.items.map { it.toModel(entityManager) }.toSet()
    }
}
