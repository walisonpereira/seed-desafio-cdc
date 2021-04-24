package com.deveficiente.casadocodigo.author

import com.deveficiente.casadocodigo.shared.validation.Unique
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class AuthorRequest(
        @field:NotBlank
        val name: String,

        @field:NotBlank
        @field:Email
        @field:Unique(entityClass = Author::class, entityField = "email")
        val email: String,

        @field:NotBlank
        @field:Size(max = 400)
        val description: String
) {
    fun toModel(): Author = Author(name = name, email = email, description = description)
}
