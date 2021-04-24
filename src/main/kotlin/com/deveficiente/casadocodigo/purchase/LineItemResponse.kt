package com.deveficiente.casadocodigo.purchase

import java.math.BigDecimal

class LineItemResponse private constructor(
        val bookTitle: String,
        val quantity: Int,
        val price: BigDecimal,
        val amount: BigDecimal,
) {
    companion object {
        fun of(items: Set<LineItem>): Set<LineItemResponse> =
                items.map {
                    LineItemResponse(
                            bookTitle = it.book.title,
                            quantity = it.quantity,
                            price = it.price,
                            amount = it.amount())
                }.toSet()
    }
}
