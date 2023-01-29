package app.jersonb.mysimplelist.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import app.jersonb.mysimplelist.models.Product

@Dao
interface ProductData {
    @Query("SELECT * FROM product")
    fun getAll(): List<Product>

    @Insert
    fun create(vararg product: Product)

    @Update
    fun update(product: Product)

    @Delete
    fun delete(product: Product)

    @Query("SELECT * FROM product WHERE id = :id")
    fun getById(id: Long): Product

}