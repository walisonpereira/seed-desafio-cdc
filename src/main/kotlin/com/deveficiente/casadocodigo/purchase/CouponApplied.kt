package com.deveficiente.casadocodigo.purchase

import com.deveficiente.casadocodigo.coupon.Coupon
import org.springframework.lang.NonNull
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.Embeddable
import javax.persistence.ManyToOne
import javax.validation.Valid
import javax.validation.constraints.Future
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@Embeddable
class CouponApplied private constructor(
        @Valid
        @ManyToOne
        val coupon: Coupon,

        @field:Positive
        @field:NonNull
        val discount: BigDecimal,

        @field:NotNull
        @field:Future
        val validUntil: LocalDate
) {
    constructor(coupon: Coupon) : this(coupon, coupon.discount, coupon.validUntil)
}
