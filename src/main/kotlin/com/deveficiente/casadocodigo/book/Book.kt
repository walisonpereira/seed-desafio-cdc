package com.deveficiente.casadocodigo.book

import com.deveficiente.casadocodigo.author.Author
import com.deveficiente.casadocodigo.book.Book.Companion.FIND_ALL
import com.deveficiente.casadocodigo.category.Category
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.*

@Entity
@Table(name = "books", uniqueConstraints = [
    UniqueConstraint(columnNames = ["title"], name = "uk_book_title"),
    UniqueConstraint(columnNames = ["isbn"], name = "uk_book_isbn")
])
@NamedQueries(NamedQuery(name = FIND_ALL, query = "select b from Book b"))
data class Book(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @field:NotBlank
        val title: String,

        @field:NotBlank
        @field:Size(max = 500)
        val abstract: String,

        @field:NotBlank
        val summary: String,

        @field:NotNull
        @field:Min(20)
        val price: BigDecimal,

        @field:Min(100)
        val pages: Int,

        @field:NotBlank
        val isbn: String,

        @field:Future
        @field:NotNull
        val publishedAt: LocalDate,

        @field:NotNull
        @Valid
        @ManyToOne
        val category: Category,

        @field:NotNull
        @Valid
        @ManyToOne
        val author: Author
) {
    companion object {
        const val FIND_ALL: String = "Book.findAll"
    }
}
