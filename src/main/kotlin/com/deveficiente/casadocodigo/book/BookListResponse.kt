package com.deveficiente.casadocodigo.book

class BookListResponse private constructor(val id: Long?, val title: String) {
    companion object {
        fun of(books: MutableList<Book>): List<BookListResponse> =
                books.map { BookListResponse(id = it.id, title = it.title) }
    }
}
