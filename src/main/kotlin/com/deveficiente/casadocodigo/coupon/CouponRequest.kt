package com.deveficiente.casadocodigo.coupon

import com.deveficiente.casadocodigo.shared.validation.Unique
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO.DATE
import java.math.BigDecimal
import java.time.LocalDate
import javax.validation.constraints.Future
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class CouponRequest(
        @field:NotBlank
        @field:Unique(entityClass = Coupon::class, entityField = "code")
        val code: String,

        @field:Positive
        @field:NotNull
        val discount: BigDecimal,

        @field:Future
        @field:NotNull
        @field:DateTimeFormat(iso = DATE)
        val validUntil: LocalDate

) {
    fun toModel() = Coupon(code = code, discount = discount, validUntil = validUntil)
}
