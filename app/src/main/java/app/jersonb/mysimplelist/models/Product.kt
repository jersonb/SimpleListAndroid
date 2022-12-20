package app.jersonb.mysimplelist.models

import java.math.BigDecimal

data class Product(
    val name: String,
    val description: String,
    val value: BigDecimal
) {

    var isValid: Boolean = false
        private set

    constructor(name: String, description: String, value: String = "0.0") : this(
        name,
        description,
        value.ifBlank { "0.0" }.toBigDecimal()
    )

    init {
        require(name.length in 4..10) {
            "Nome precisa ser válido"

        }
        require(description.length in 10..20) {
            "Descrição precisa ser válida"
        }
        require(value > BigDecimal.ZERO) {
            "Valor deve ser maior que zero."
        }

        isValid = true
    }
}
