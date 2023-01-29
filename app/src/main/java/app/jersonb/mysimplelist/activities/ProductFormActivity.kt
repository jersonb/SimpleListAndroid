package app.jersonb.mysimplelist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import app.jersonb.mysimplelist.database.AppDatabase
import app.jersonb.mysimplelist.databinding.ActivityProductFormBinding
import app.jersonb.mysimplelist.dialogs.FormImageDialog
import app.jersonb.mysimplelist.extensions.loadImage
import app.jersonb.mysimplelist.models.Product

private const val TAG = "ProductFormActivity"

class ProductFormActivity : AppCompatActivity() {

    private var productId = 0L
    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }
    private var url: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar Item"
        configureAddImageButton()
        configureSaveButton()
        configureUpdateProduct()
    }

    private fun configureUpdateProduct() {
        intent.getParcelableExtra<Product>(KEY_PRODUCT)?.let { product ->
            title = "Editar Item"
            productId = product.id

            binding.imageProduct.loadImage(product.image)
            binding.inputName.setText(product.name)
            binding.inputDescription.setText(product.description)
            binding.inputValue.setText(product.value.toPlainString())
        }
    }

    private fun configureAddImageButton() {
        binding.imageProduct.setOnClickListener {
            FormImageDialog(this)
                .show(urlDefault = url) { urlImage, image ->
                    url = urlImage
                    binding.imageProduct.loadImage(image)
                }
        }
    }

    private fun configureSaveButton() {
        binding.buttonSave.setOnClickListener {
            val product = getProductFromForm()
            Log.i(TAG, "product: $product")

            val db = AppDatabase.getInstance(this)

            if (product != null) {
                if (product.id == 0L) {
                    db.create(product)
                    finish()
                } else {
                    db.update(product)
                    Intent(this, ProductDetailActivity::class.java).apply {
                        putExtra(KEY_PRODUCT, product)
                        startActivity(this)

                    }
                }
            }
        }
    }

    private fun getProductFromForm(): Product? {
        val fieldName = binding.inputName
        val fieldDescription = binding.inputDescription
        val fieldValue = binding.inputValue

        return try {
            Product(
                id = productId,
                name = fieldName.text.toString(),
                description = fieldDescription.text.toString(),
                value = fieldValue.text.toString(),
                image = url
            )
        } catch (err: Throwable) {
            Log.e(TAG, "Error", err)
            Toast.makeText(this, err.message, Toast.LENGTH_LONG).show()
            return null
        }
    }
}