package app.jersonb.mysimplelist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import app.jersonb.mysimplelist.database.AppDatabase
import app.jersonb.mysimplelist.databinding.ActivityProductFormBinding
import app.jersonb.mysimplelist.dialogs.FormImageDialog
import app.jersonb.mysimplelist.extensions.loadImage
import app.jersonb.mysimplelist.models.Product
import kotlinx.coroutines.*

private const val TAG = "ProductFormActivity"

class ProductFormActivity : AppCompatActivity() {

    private var productId = 0L
    private var url: String? = null

    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }

    private val database by lazy {
        AppDatabase.getInstance(this)
    }

    private val scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar Item"
        configureAddImageButton()
        configureSaveButton()
        configureUpdateProduct()
    }

    private fun configureUpdateProduct() {
        productId = intent.getLongExtra(KEY_PRODUCT, 0L)
        if (productId != 0L) {
            title = "Editar Item"
            scope.launch {

                val product = withContext(Dispatchers.IO) {
                    database.getById(productId)
                }

                with(binding) {
                    url = product.image
                    imageProduct.loadImage(product.image)
                    inputName.setText(product.name)
                    inputDescription.setText(product.description)
                    inputValue.setText(product.value.toPlainString())
                }
            }
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

            if (product != null) {
                scope.launch {
                    withContext(Dispatchers.IO) {
                        database.create(product)
                        finish()
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