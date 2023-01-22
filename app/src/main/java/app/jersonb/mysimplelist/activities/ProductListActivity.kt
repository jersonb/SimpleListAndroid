package app.jersonb.mysimplelist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import app.jersonb.mysimplelist.daos.ProductDao
import app.jersonb.mysimplelist.databinding.ActivityProductListBinding
import app.jersonb.mysimplelist.views.ProductViewAdapter

class ProductListActivity : AppCompatActivity() {
    private val productDao = ProductDao()
    private val adapter = ProductViewAdapter(this, products = productDao.findAll())

    private val binding by lazy {
        ActivityProductListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configureList()
        configureButton()
    }

    override fun onResume() {
        super.onResume()
        adapter.update(productDao.findAll())
    }

    private fun configureButton() {
        binding.buttonAdd.setOnClickListener {
            val intentProductForm = Intent(this, ProductFormActivity::class.java)
            startActivity(intentProductForm)
        }

    }

    private fun configureList() {
        val listItem = binding.recyclerViewItems
        listItem.adapter = adapter
        adapter.onClick = {
            val intent = Intent(
                this, ProductDetailActivity::class.java
            ).apply {
                putExtra(KEY_PRODUCT, it)
            }
            startActivity(intent)
        }
    }
}

