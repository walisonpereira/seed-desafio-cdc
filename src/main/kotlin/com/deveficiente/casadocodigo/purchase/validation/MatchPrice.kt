package com.deveficiente.casadocodigo.purchase.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [MatchPriceValidator::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class MatchPrice(
        val message: String = "amount should match total price of items",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = [])
