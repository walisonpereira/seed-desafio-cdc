package com.deveficiente.casadocodigo.purchase

import com.deveficiente.casadocodigo.country.Country
import com.deveficiente.casadocodigo.coupon.Coupon
import com.deveficiente.casadocodigo.coupon.Coupon.Companion.FIND_BY_CODE
import com.deveficiente.casadocodigo.purchase.validation.MatchPrice
import com.deveficiente.casadocodigo.purchase.validation.StateBelongsToCountry
import com.deveficiente.casadocodigo.shared.validation.CEP
import com.deveficiente.casadocodigo.shared.validation.Document
import com.deveficiente.casadocodigo.shared.validation.Exists
import com.deveficiente.casadocodigo.state.State
import javax.persistence.EntityManager
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@StateBelongsToCountry
class PurchaseRequest(
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
        @field:Exists(entityClass = Country::class)
        val countryId: Long,

        @field:Exists(entityClass = State::class)
        val stateId: Long?,

        @field:NotBlank
        val phone: String,

        @field:CEP
        @field:NotBlank
        val cep: String,

        @field:NotNull
        @field:MatchPrice
        @Valid
        val order: OrderRequest,

        @field:Exists(entityClass = Coupon::class, entityField = "code")
        val couponCode: String?,
) {
    fun toModel(entityManager: EntityManager): Purchase {
        val country = entityManager.find(Country::class.java, countryId)
        val state = stateId?.let { entityManager.find(State::class.java, stateId) }
        val items = order.toModel(entityManager)
        val coupon = couponCode?.let {
            entityManager.createNamedQuery(FIND_BY_CODE, Coupon::class.java)
                    .setParameter("code", it)
                    .resultList
                    .firstOrNull()
        }

        return Purchase(
                email = email,
                name = name,
                surname = surname,
                document = document,
                address = address,
                complement = complement,
                city = city,
                country = country,
                state = state,
                phone = phone,
                cep = cep,
                items = items,
                couponApplied = coupon?.let { CouponApplied(it) }
        )
    }
}
