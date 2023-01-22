package app.jersonb.mysimplelist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import app.jersonb.mysimplelist.daos.ProductDao
import app.jersonb.mysimplelist.databinding.ActivityProductFormBinding
import app.jersonb.mysimplelist.dialogs.FormImageDialog
import app.jersonb.mysimplelist.extensions.loadImage
import app.jersonb.mysimplelist.models.Product

class ProductFormActivity : AppCompatActivity() {

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
    }

    private fun configureAddImageButton() {
        binding.imageProduct.setOnClickListener {
            FormImageDialog(this).show(urlDefault = url) { urlImage, image ->
                url = urlImage
                binding.imageProduct.loadImage(image)
            }
        }
    }

    private fun configureSaveButton() {
        binding.buttonSave.setOnClickListener {
            val productDao = ProductDao()
            val product = getProductFromForm()
            Log.i("ProductFormActivity", "product: $product")

            if (product != null) {
                productDao.create(product)
                finish()
            }
        }
    }

    private fun getProductFromForm(): Product? {
        val fieldName = binding.inputName
        val fieldDescription = binding.inputDescription
        val fieldValue = binding.inputValue

        return try {
            Product(
                name = fieldName.text.toString(),
                description = fieldDescription.text.toString(),
                value = fieldValue.text.toString(),
                image = url
            )
        } catch (err: Throwable) {
            Log.e("ProductFormActivity", "Error", err)
            Toast.makeText(this, err.message, Toast.LENGTH_LONG).show()
            return null
        }
    }
}