package app.jersonb.mysimplelist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.jersonb.mysimplelist.R
import app.jersonb.mysimplelist.dao.ProductDao
import app.jersonb.mysimplelist.views.ProductViewAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductListActivity : AppCompatActivity(R.layout.activity_product_list) {
    private val productDao = ProductDao()
    private val adapter = ProductViewAdapter(this, products = productDao.findAll())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureList()
    }

    override fun onResume() {
        super.onResume()
        adapter.update(productDao.findAll())
        configureButton()
    }

    private fun configureButton() {
        val buttonAdd = findViewById<FloatingActionButton>(R.id.button_add)

        buttonAdd.setOnClickListener { view: View ->
            Log.i("ProductListActivity", "Click button add")
            Log.d("ProductListActivity", "View: $view")
            val intentProductForm = Intent(this, ProductFormActivity::class.java)
            startActivity(intentProductForm)
        }
    }

    private fun configureList() {
        val listItem = findViewById<RecyclerView>(R.id.recycler_view_items)
        listItem.adapter = adapter
    }
}

