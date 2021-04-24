package com.deveficiente.casadocodigo.shared.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [UniqueValidator::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention
annotation class Unique(
        val message: String = "value has already been taken",
        val entityClass: KClass<*>,
        val entityField: String,
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = [])
