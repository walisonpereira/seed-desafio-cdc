package com.deveficiente.casadocodigo.purchase

import com.deveficiente.casadocodigo.book.Book
import com.deveficiente.casadocodigo.shared.validation.Exists
import javax.persistence.EntityManager
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class LineItemRequest(
        @field:NotNull
        @field:Exists(entityClass = Book::class)
        val bookId: Long,

        @field:Positive
        @field:Min(1)
        val quantity: Int
) {
    fun toModel(entityManager: EntityManager): LineItem {
        val book = entityManager.find(Book::class.java, bookId)
        return LineItem(book = book, quantity = quantity)
    }
}
