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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "ProductDetailActivity"

class ProductDetailActivity : AppCompatActivity() {

    private var productId = 0L

    private val binding by lazy {
        ActivityProductDetailBinding.inflate(layoutInflater)
    }

    private val database by lazy {
        AppDatabase.getInstance(this)
    }
    private val scope = MainScope()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Detalhes do produto"
        setContentView(binding.root)
        productId = intent.getLongExtra(KEY_PRODUCT, 0L)
    }

    override fun onResume() {
        super.onResume()
        if (productId != 0L) {
            scope.launch {
                val product = withContext(Dispatchers.IO) {
                    database.getById(productId)
                }
                with(binding) {
                    imageProductDetail.loadImage(product.image)
                    labelProductNameDetail.text = product.name
                    labelProductDescriptionDetail.text = product.description
                    labelProductValueDetail.text = product.formattedValue
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (productId == 0L) return false

        when (item.itemId) {
            R.id.menu_product_edit -> {
                Intent(this, ProductFormActivity::class.java).apply {
                    putExtra(KEY_PRODUCT, productId)
                    startActivity(this)
                }

                Log.i(TAG, "onOptionsItemSelected: update ")
            }
            R.id.menu_product_remove -> {
                scope.launch {
                    withContext(Dispatchers.IO) {
                        val product = database.getById(productId)
                        database.delete(product)
                    }
                }
                Log.i(TAG, "onOptionsItemSelected: delete")
                finish()
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