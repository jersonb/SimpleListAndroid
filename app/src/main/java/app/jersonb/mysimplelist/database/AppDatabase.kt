package app.jersonb.mysimplelist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.jersonb.mysimplelist.daos.ProductData
import app.jersonb.mysimplelist.models.Product

@Database(entities = [Product::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductData

    companion object {
        fun getInstance(context: Context): ProductData {
            val db = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "simple-list.db"
            ).allowMainThreadQueries()
                .build()

            return db.productDao()
        }
    }
}