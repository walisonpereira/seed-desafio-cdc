package com.deveficiente.casadocodigo.purchase

import com.deveficiente.casadocodigo.book.Book
import java.math.BigDecimal
import javax.persistence.Embeddable
import javax.persistence.ManyToOne
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@Embeddable
class LineItem private constructor(
        @field:NotNull
        @Valid
        @ManyToOne
        val book: Book,

        @field:Positive
        val quantity: Int,

        @field:Positive
        val price: BigDecimal
) {
    constructor(book: Book, quantity: Int) : this(book, quantity, book.price)

    fun amount(): BigDecimal = price.multiply(BigDecimal(quantity))
}
