package com.deveficiente.casadocodigo.country

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
class CountryController(val entityManager: EntityManager) {

    @Transactional
    @PostMapping("/countries")
    fun create(
            @Valid @RequestBody request: CountryRequest
    ): ResponseEntity<CountryResponse> {
        val country = request.toModel()
        entityManager.persist(country)
        return ResponseEntity.ok(CountryResponse(country))
    }

}
