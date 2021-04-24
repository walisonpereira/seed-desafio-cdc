package com.deveficiente.casadocodigo.author

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
class AuthorController(val entityManager: EntityManager) {

    @Transactional
    @PostMapping("/authors")
    fun create(
            @Valid @RequestBody request: AuthorRequest
    ): ResponseEntity<AuthorResponse> {
        val author = request.toModel()
        entityManager.persist(author)
        return ResponseEntity.ok(AuthorResponse(author))
    }

}
