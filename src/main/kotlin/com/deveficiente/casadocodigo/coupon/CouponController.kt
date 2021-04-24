package com.deveficiente.casadocodigo.coupon

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
class CouponController(val entityManager: EntityManager) {

    @Transactional
    @PostMapping("/coupons")
    fun create(
            @Valid @RequestBody request: CouponRequest
    ): ResponseEntity<CouponResponse> {
        val coupon = request.toModel()
        entityManager.persist(coupon)
        return ResponseEntity.ok(CouponResponse(coupon));
    }

}
