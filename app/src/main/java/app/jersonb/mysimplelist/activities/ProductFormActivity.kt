package app.jersonb.mysimplelist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import app.jersonb.mysimplelist.R
import app.jersonb.mysimplelist.dao.ProductDao
import app.jersonb.mysimplelist.models.Product

class ProductFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_form)

        configureSaveButton()
    }

    private fun configureSaveButton() {
        findViewById<Button>(R.id.button_save).setOnClickListener {
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
        val fieldName = findViewById<EditText>(R.id.input_name)
        val fieldDescription = findViewById<EditText>(R.id.input_description)
        val fieldValue = findViewById<EditText>(R.id.input_value)

        return try {
            Product(
                name = fieldName.text.toString(),
                description = fieldDescription.text.toString(),
                value = fieldValue.text.toString()
            )
        } catch (err: Throwable) {
            Log.e("ProductFormActivity", "Error", err)
            Toast.makeText(this, err.message, Toast.LENGTH_LONG).show()
            return null
        }
    }
}