package com.deveficiente.casadocodigo.book

import com.deveficiente.casadocodigo.author.Author
import com.deveficiente.casadocodigo.category.Category
import com.deveficiente.casadocodigo.shared.validation.Exists
import com.deveficiente.casadocodigo.shared.validation.Unique
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO.DATE
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.EntityManager
import javax.validation.constraints.*

class BookRequest(
        @field:NotBlank
        @field:Unique(entityClass = Book::class, entityField = "title")
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
        @field:Unique(entityClass = Book::class, entityField = "isbn")
        val isbn: String,

        @field:Future
        @field:NotNull
        @DateTimeFormat(iso = DATE)
        val publishedAt: LocalDate,

        @field:NotNull
        @field:Exists(entityClass = Category::class)
        val categoryId: Long,

        @field:NotNull
        @field:Exists(entityClass = Author::class)
        val authorId: Long,
) {
    fun toModel(entityManager: EntityManager): Book {
        val category = entityManager.find(Category::class.java, categoryId)
        val author = entityManager.find(Author::class.java, authorId)

        return Book(
                title = title,
                abstract = abstract,
                summary = summary,
                price = price,
                pages = pages,
                isbn = isbn,
                publishedAt = publishedAt,
                category = category,
                author = author
        )
    }
}
