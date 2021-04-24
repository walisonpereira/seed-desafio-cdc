package com.deveficiente.casadocodigo.country

class CountryResponse(
        val id: Long?,
        val name: String
) {
    constructor(country: Country) : this(country.id, country.name)
}
