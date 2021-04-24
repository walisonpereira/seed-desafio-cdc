package com.deveficiente.casadocodigo.shared.validation

import java.util.regex.Pattern
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class CEPValidator : ConstraintValidator<CEP, String?> {
    private val pattern = Pattern.compile("[0-9]{5}-[0-9]{3}")

    override fun initialize(constraint: CEP) {
        // nothing to initialize
    }

    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        if (value.isNullOrBlank()) {
            return true
        }

        val matcher = pattern.matcher(value)
        return matcher.matches()
    }
}
