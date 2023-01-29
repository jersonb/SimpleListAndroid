package app.jersonb.mysimplelist.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.jersonb.mysimplelist.models.Product

@Dao
interface ProductData {
    @Query("SELECT * FROM product p ORDER BY p.name ASC")
    fun getAll(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(vararg product: Product)

    @Delete
    fun delete(product: Product)

    @Query("SELECT * FROM product WHERE id = :id")
    fun getById(id: Long): Product

}