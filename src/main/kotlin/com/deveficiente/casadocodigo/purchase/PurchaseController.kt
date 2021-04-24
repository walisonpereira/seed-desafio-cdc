package com.deveficiente.casadocodigo.purchase

import com.deveficiente.casadocodigo.shared.validation.Exists
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@RestController
class PurchaseController(val entityManager: EntityManager) {

    @InitBinder
    fun initBinder(binder: WebDataBinder) {
        binder.addValidators(CouponValidator(entityManager))
    }

    @Transactional
    @PostMapping("/purchases")
    fun create(
            @Valid @RequestBody request: PurchaseRequest,
            uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<Unit> {
        val purchase = request.toModel(entityManager)
        entityManager.persist(purchase)

        val uri = uriComponentsBuilder
                .path("/purchases/${purchase.id}")
                .build().toUri()
        return ResponseEntity.created(uri).build()
    }

    @GetMapping("/purchases/{id}")
    fun retrieve(
            @Exists(entityClass = Purchase::class) @PathVariable id: Long
    ): ResponseEntity<PurchaseResponse> {
        val purchase = entityManager.find(Purchase::class.java, id)
        return ResponseEntity.ok(PurchaseResponse(purchase))
    }

}
