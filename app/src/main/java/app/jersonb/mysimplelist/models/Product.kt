package app.jersonb.mysimplelist.models

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

@Parcelize
data class Product(
    val name: String,
    val description: String,
    val value: BigDecimal,
    val image: String? = null
) : Parcelable {

    @IgnoredOnParcel
    var isValid: Boolean = false
    val formattedValue: String = NumberFormat.getCurrencyInstance(Locale("pt", "br")).format(value)

    constructor(
        name: String,
        description: String,
        value: String = "0.0",
        image: String? = null
    ) : this(
        name,
        description,
        value.ifBlank { "0.0" }.toBigDecimal(),
        image
    )

    init {
//        require(name.length in 4..10) {
//            "Nome precisa ser válido"
//        }
//        require(description.length in 10..20) {
//            "Descrição precisa ser válida"
//        }
//        require(value > BigDecimal.ZERO) {
//            "Valor deve ser maior que zero."
//        }
//
//        isValid = true
    }


}
