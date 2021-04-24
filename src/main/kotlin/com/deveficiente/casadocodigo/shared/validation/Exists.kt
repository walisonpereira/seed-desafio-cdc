package com.deveficiente.casadocodigo.shared.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [ExistsValidator::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Exists(
        val message: String = "not found",
        val entityClass: KClass<*>,
        val entityField: String = "id",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)
