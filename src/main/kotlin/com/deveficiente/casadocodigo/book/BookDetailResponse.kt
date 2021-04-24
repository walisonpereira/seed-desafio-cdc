package com.deveficiente.casadocodigo.book

import com.deveficiente.casadocodigo.author.Author
import java.math.BigDecimal
import java.time.LocalDate

class BookDetailResponse(
        val title: String,
        val abstract: String,
        val summary: String,
        val price: BigDecimal,
        val pages: Int,
        val isbn: String,
        val publishedAt: LocalDate,
        val author: BookAuthorResponse
) {
    constructor(book: Book) : this(
            book.title,
            book.abstract,
            book.summary,
            book.price,
            book.pages,
            book.isbn,
            book.publishedAt,
            BookAuthorResponse(book.author)
    )

    class BookAuthorResponse(val name: String, val description: String) {
        constructor(author: Author) : this(author.name, author.description)
    }
}


