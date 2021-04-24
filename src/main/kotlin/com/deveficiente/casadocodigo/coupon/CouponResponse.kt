package com.deveficiente.casadocodigo.coupon

import java.math.BigDecimal
import java.time.LocalDate

class CouponResponse(
        val id: Long?,
        val code: String,
        val discount: BigDecimal,
        val validUntil: LocalDate
) {
    constructor(coupon: Coupon) : this(coupon.id, coupon.code, coupon.discount, coupon.validUntil)
}
