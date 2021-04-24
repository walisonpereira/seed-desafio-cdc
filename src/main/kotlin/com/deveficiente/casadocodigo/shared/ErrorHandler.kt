package com.deveficiente.casadocodigo.shared

import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*
import javax.validation.ConstraintViolation
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): Map<String, MutableMap<String, String?>> {
        val errors: MutableMap<String, String?> = HashMap()

        ex.bindingResult.allErrors.forEach { error: ObjectError ->
            val name: String = when (error) {
                is FieldError -> error.field
                else -> error.objectName.replace("Request".toRegex(), "")
            }

            val errorMessage: String? = error.defaultMessage
            errors[name] = errorMessage
        }

        return mapOf("errors" to errors)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(ex: ConstraintViolationException): Map<String, MutableMap<String, String?>> {
        val errors: MutableMap<String, String?> = HashMap()

        ex.constraintViolations.forEach { error: ConstraintViolation<*> ->
            val name: String = error.propertyPath.last().name;
            val errorMessage: String? = error.message
            errors[name] = errorMessage
        }

        return mapOf("errors" to errors)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: Exception): Map<String, String?> {
        return mapOf("error" to ex.message)
    }

}
