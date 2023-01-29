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
        @Volatile
        private lateinit var db: AppDatabase

        fun getInstance(context: Context): ProductData {
            if (::db.isInitialized) return db.productDao()

            db = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "simple-list.db"
            ).allowMainThreadQueries()
                .build().also {
                    db = it
                }

            return db.productDao()
        }
    }
}