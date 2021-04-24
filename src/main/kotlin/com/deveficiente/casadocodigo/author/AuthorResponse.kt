package com.deveficiente.casadocodigo.author

import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class AuthorResponse(
        val id: Long?,
        val name: String,
        val email: String,
        val description: String,
        val createdAt: String
) {
    constructor(model: Author) : this(model.id, model.name, model.email, model.description, dateFormat(model.createdAt))

    companion object {
        private fun dateFormat(date: OffsetDateTime): String {
            return date.toZonedDateTime().withZoneSameInstant(ZoneId.of("Z")).format(DateTimeFormatter.ISO_ZONED_DATE_TIME)
        }
    }
}
