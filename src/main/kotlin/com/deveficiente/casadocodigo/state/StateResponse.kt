package com.deveficiente.casadocodigo.state

import com.deveficiente.casadocodigo.country.Country

class StateResponse(
        val id: Long?,
        val name: String,
        val country: Country
) {
    constructor(state: State) : this(state.id, state.name, state.country)
}
