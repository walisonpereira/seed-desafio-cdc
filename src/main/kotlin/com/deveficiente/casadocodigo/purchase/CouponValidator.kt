package com.deveficiente.casadocodigo.purchase

import com.deveficiente.casadocodigo.coupon.Coupon
import com.deveficiente.casadocodigo.coupon.Coupon.Companion.FIND_BY_CODE
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import javax.persistence.EntityManager

class CouponValidator(val entityManager: EntityManager) : Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return PurchaseRequest::class.java.isAssignableFrom(clazz)
    }

    override fun validate(target: Any, errors: Errors) {
        val request = target as PurchaseRequest

        val coupon = entityManager.createNamedQuery(FIND_BY_CODE, Coupon::class.java)
                .setParameter("code", request.couponCode)
                .resultList
                .firstOrNull()

        if (coupon != null && !coupon.isValid()) {
            errors.rejectValue("couponCode", "coupon should be valid")
        }
    }
}
