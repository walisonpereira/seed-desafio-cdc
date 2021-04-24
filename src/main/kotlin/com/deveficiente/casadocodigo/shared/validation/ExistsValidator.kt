package com.deveficiente.casadocodigo.shared.validation

import java.util.stream.Stream
import javax.persistence.EntityManager
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import kotlin.reflect.KClass

class ExistsValidator(private val entityManager: EntityManager) : ConstraintValidator<Exists, Any?> {
    private lateinit var entityClass: KClass<*>
    private lateinit var entityField: String

    override fun initialize(constraint: Exists) {
        entityClass = constraint.entityClass
        entityField = constraint.entityField
    }

    override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
        if (value == null) {
            return true
        }

        return exists(entityManager, entityClass, entityField, value)
    }

    companion object {
        fun exists(entityManager: EntityManager, entityClass: KClass<*>, field: String?, fieldValue: Any?): Boolean {
            val criteriaBuilder: CriteriaBuilder = entityManager.criteriaBuilder
            val criteriaQuery: CriteriaQuery<Any> = criteriaBuilder.createQuery()

            val root: Root<out Any> = criteriaQuery.from(entityClass.java)
            val predicate: Predicate = criteriaBuilder.equal(root.get<Any>(field), fieldValue)
            criteriaQuery.select(root).where(predicate)

            val stream: Stream<*> = entityManager
                    .createQuery<Any>(criteriaQuery)
                    .setMaxResults(1)
                    .resultStream

            return stream.findFirst().isPresent
        }
    }
}
