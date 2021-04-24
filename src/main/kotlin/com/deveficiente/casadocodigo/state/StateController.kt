package com.deveficiente.casadocodigo.state

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
class StateController(val entityManager: EntityManager) {

    @Transactional
    @PostMapping("/states")
    fun create(
            @Valid @RequestBody request: StateRequest
    ): ResponseEntity<StateResponse> {
        val state = request.toModel(entityManager)
        entityManager.persist(state)
        return ResponseEntity.ok(StateResponse(state))
    }

}
