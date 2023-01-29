package app.jersonb.mysimplelist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import app.jersonb.mysimplelist.R
import app.jersonb.mysimplelist.database.AppDatabase
import app.jersonb.mysimplelist.databinding.ActivityProductDetailBinding
import app.jersonb.mysimplelist.extensions.loadImage
import app.jersonb.mysimplelist.models.Product

private const val TAG = "ProductDetailActivity"

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var product: Product
    private val binding by lazy {
        ActivityProductDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Detalhes do produto"
        setContentView(binding.root)
        intent.getParcelableExtra<Product>(KEY_PRODUCT)?.let { product ->
            this.product = product
            with(binding) {
                imageProductDetail.loadImage(product.image)
                labelProductNameDetail.text = product.name
                labelProductDescriptionDetail.text = product.description
                labelProductValueDetail.text = product.formattedValue
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (::product.isInitialized) {
            val db = AppDatabase.getInstance(this)
            when (item.itemId) {

                R.id.menu_product_edit -> {
                    Intent(this, ProductFormActivity::class.java).apply {
                        putExtra(KEY_PRODUCT, product)
                        startActivity(this)
                    }

                    Log.i(TAG, "onOptionsItemSelected: update ")
                }
                R.id.menu_product_remove -> {
                    db.delete(product)
                    Log.i(TAG, "onOptionsItemSelected: delete")
                    finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        Intent(this, ProductListActivity::class.java).apply {
            startActivity(this)
        }
    }
}