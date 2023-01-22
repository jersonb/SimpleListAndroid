package app.jersonb.mysimplelist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.jersonb.mysimplelist.databinding.ActivityProductDetailBinding
import app.jersonb.mysimplelist.extensions.loadImage
import app.jersonb.mysimplelist.models.Product

class ProductDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProductDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Detalhes do produto"
        setContentView(binding.root)
        intent.getParcelableExtra<Product>(KEY_PRODUCT)?.let { product ->

            with(binding) {
                binding.imageProductDetail.loadImage(product.image)
                labelProductNameDetail.text = product.name
                labelProductDescriptionDetail.text = product.description
                labelProductValueDetail.text = product.formattedValue
            }
        }
    }
}