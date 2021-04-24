package com.deveficiente.casadocodigo.category

import com.deveficiente.casadocodigo.shared.validation.Unique
import javax.validation.constraints.NotBlank

class CategoryRequest(
        @field:NotBlank
        @field:Unique(entityClass = Category::class, entityField = "name")
        val name: String
) {
    fun toModel() = Category(name = name)
}
