package app.jersonb.mysimplelist.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.jersonb.mysimplelist.daos.ProductData
import app.jersonb.mysimplelist.models.ProductDto

@Database(entities = [ProductDto::class], version = 1)
@TypeConverters(Converters::class )
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductData
}