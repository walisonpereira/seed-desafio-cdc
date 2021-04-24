package com.deveficiente.casadocodigo.shared.validation

import com.deveficiente.casadocodigo.shared.validation.ExistsValidator.Companion.exists
import javax.persistence.EntityManager
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import kotlin.reflect.KClass

class UniqueValidator(private val entityManager: EntityManager) : ConstraintValidator<Unique, Any?> {
    private lateinit var entityClass: KClass<*>
    private lateinit var entityField: String

    override fun initialize(constraint: Unique) {
        entityClass = constraint.entityClass
        entityField = constraint.entityField
    }

    override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
        if (value == null) {
            return true
        }

        return !exists(entityManager, entityClass, entityField, value)
    }
}
