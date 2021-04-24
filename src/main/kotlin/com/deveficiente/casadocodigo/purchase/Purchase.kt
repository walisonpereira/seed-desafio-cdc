package com.deveficiente.casadocodigo.purchase

import com.deveficiente.casadocodigo.country.Country
import com.deveficiente.casadocodigo.shared.validation.CEP
import com.deveficiente.casadocodigo.shared.validation.Document
import com.deveficiente.casadocodigo.state.State
import java.math.BigDecimal
import java.math.RoundingMode.CEILING
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "purchases")
class Purchase(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @field:Email
        @field:NotBlank
        val email: String,

        @field:NotBlank
        val name: String,

        @field:NotBlank
        val surname: String,

        @field:Document
        @field:NotBlank
        val document: String,

        @field:NotBlank
        val address: String,

        @field:NotBlank
        val complement: String,

        @field:NotBlank
        val city: String,

        @field:NotNull
        @Valid
        @ManyToOne
        val country: Country,

        @Valid
        @ManyToOne
        val state: State?,

        @field:NotBlank
        val phone: String,

        @field:CEP
        @field:NotBlank
        val cep: String,

        @field:Size(min = 1)
        @Valid
        @ElementCollection
        val items: Set<LineItem> = hashSetOf(),

        @Embedded
        val couponApplied: CouponApplied? = null,

        @Enumerated(EnumType.STRING)
        val status: Status = Status.DEFERRED
) {
    private fun grossAmount(): BigDecimal = items.map(LineItem::amount).fold(BigDecimal.ZERO, BigDecimal::add)

    fun discount(): BigDecimal? = couponApplied?.discount?.multiply(grossAmount())?.setScale(2, CEILING) ?: BigDecimal.ZERO

    fun netAmount(): BigDecimal = grossAmount().subtract(discount()).setScale(2, CEILING)
}
