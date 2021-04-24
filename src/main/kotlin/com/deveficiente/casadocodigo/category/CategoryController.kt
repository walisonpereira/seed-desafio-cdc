package com.deveficiente.casadocodigo.category

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
class CategoryController(val entityManager: EntityManager) {

    @Transactional
    @PostMapping("/categories")
    fun create(
            @Valid @RequestBody request: CategoryRequest
    ): ResponseEntity<CategoryResponse> {
        val category = request.toModel()
        entityManager.persist(category)
        return ResponseEntity.ok(CategoryResponse(category))
    }

}
