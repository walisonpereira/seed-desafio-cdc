package com.deveficiente.casadocodigo.purchase.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [StateBelongsToCountryValidator::class])
@Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class StateBelongsToCountry(
        val message: String = "state should belongs to selected city",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = [])
