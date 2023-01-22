package app.jersonb.mysimplelist.database

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {
    @TypeConverter
    fun fromDouble(value: Double?): BigDecimal {
        return value?.let { BigDecimal(value.toString()) } ?: BigDecimal.ZERO
    }

    @TypeConverter
    fun fromDecimal(value: BigDecimal?): Double? {
        return value?.let { value.toDouble() }
    }
}