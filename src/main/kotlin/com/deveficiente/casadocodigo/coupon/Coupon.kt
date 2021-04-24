package com.deveficiente.casadocodigo.coupon

import com.deveficiente.casadocodigo.coupon.Coupon.Companion.FIND_BY_CODE
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Future
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@Entity
@Table(name = "coupons", uniqueConstraints = [
    UniqueConstraint(columnNames = ["code"], name = "uk_coupon_code")
])
@NamedQueries(NamedQuery(name = FIND_BY_CODE, query = "select c from Coupon c where c.code = :code"))
data class Coupon(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @field:NotBlank
        val code: String,

        @field:Positive
        @field:NotNull
        val discount: BigDecimal,

        @field:Future
        @field:NotNull
        val validUntil: LocalDate
) {
    fun isValid() = LocalDate.now() <= validUntil;

    companion object {
        const val FIND_BY_CODE: String = "Coupon.findByCode"
    }
}
