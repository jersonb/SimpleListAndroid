package app.jersonb.mysimplelist.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import app.jersonb.mysimplelist.models.ProductDto

@Dao
interface ProductData {
    @Query("SELECT * FROM product")
    fun getAll(): List<ProductDto>

    @Insert
    fun create(vararg product: ProductDto)
    
}