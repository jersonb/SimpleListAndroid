package app.jersonb.mysimplelist.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

@Parcelize
@Entity(tableName = "product")
data class Product(
    @PrimaryKey(autoGenerate = true) var id: Long = 0L,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "amount")val value: BigDecimal,
    @ColumnInfo(name = "url_image") val image: String? = null
) : Parcelable {

    @IgnoredOnParcel
    var isValid: Boolean = false

    @Ignore
    val formattedValue: String = NumberFormat.getCurrencyInstance(Locale("pt", "br")).format(value)

    constructor(
        id: Long =0L,
        name: String,
        description: String,
        value: String = "0.0",
        image: String? = null
    ) : this(
        id,
        name,
        description,
        value.ifBlank { "0.0" }.toBigDecimal(),
        image
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
