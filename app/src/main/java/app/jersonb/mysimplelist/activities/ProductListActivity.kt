package app.jersonb.mysimplelist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

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
            Log.i("ProductListActivity", "Click button add")
            val intentProductForm = Intent(this, ProductFormActivity::class.java)
            startActivity(intentProductForm)
        }
    }

    private fun configureList() {
        val listItem = binding.recyclerViewItems
        listItem.adapter = adapter
    }
}

