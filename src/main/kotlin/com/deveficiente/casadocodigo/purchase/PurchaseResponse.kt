package com.deveficiente.casadocodigo.purchase

import java.math.BigDecimal

class PurchaseResponse private constructor(
        val id: Long?,
        val email: String,
        val name: String,
        val surname: String,
        val document: String,
        val address: String,
        val complement: String,
        val city: String,
        val country: String,
        val state: String?,
        val phone: String,
        val cep: String,
        val items: Set<LineItemResponse>,
        val existsCoupon: Boolean,
        val discount: BigDecimal?,
        val netAmount: BigDecimal,
        val status: Status
) {
    constructor(purchase: Purchase) : this(
            id = purchase.id,
            email = purchase.email,
            name = purchase.name,
            surname = purchase.surname,
            document = purchase.document,
            address = purchase.address,
            complement = purchase.complement,
            city = purchase.city,
            country = purchase.country.name,
            state = purchase.state?.name,
            phone = purchase.phone,
            cep = purchase.cep,
            items = LineItemResponse.of(purchase.items),
            existsCoupon = purchase.couponApplied != null,
            discount = purchase.discount(),
            netAmount = purchase.netAmount(),
            status = purchase.status
    )
}
