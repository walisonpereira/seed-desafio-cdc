package com.deveficiente.casadocodigo.shared.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [CEPValidator::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class CEP(
        val message: String = "invalid CEP format",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = [])
