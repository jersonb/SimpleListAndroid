package app.jersonb.mysimplelist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import app.jersonb.mysimplelist.R

import app.jersonb.mysimplelist.database.AppDatabase
import app.jersonb.mysimplelist.databinding.ActivityProductListBinding
import app.jersonb.mysimplelist.models.Product
import app.jersonb.mysimplelist.views.ProductViewAdapter

private const val TAG = "ProductListActivity"

class ProductListActivity : AppCompatActivity() {
    private val adapter = ProductViewAdapter(this, products = mutableListOf())

    private val binding by lazy {
        ActivityProductListBinding.inflate(layoutInflater)
    }

    private val database by lazy {
        AppDatabase.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configureList()
        configureButton()
    }

    override fun onBackPressed() {}

    override fun onResume() {
        super.onResume()
        val products = database.getAll()
        adapter.update(products)
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
            nextIntent(productId = it.id, ProductDetailActivity::class.java)
        }
        adapter.onLongClick = {
            showAlertDialog(it)
        }
    }

    private fun showAlertDialog(it: Product) {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Alterar Item?")
            .setMessage("Oque deseja fazer com ${it.name}?")
            .setNeutralButton("Cancelar") { _, _ -> }
            .setNegativeButtonIcon(this.getDrawable(R.drawable.ic_action_delete))
            .setNegativeButton("Deletar") { _, _ ->
                showDeleteDialog(it)
            }
            .setPositiveButtonIcon(this.getDrawable(R.drawable.ic_action_edit))
            .setPositiveButton("Editar") { _, _ ->
                nextIntent(productId = it.id, ProductFormActivity::class.java)
            }
            .create()
        alertDialog.show()
    }

    private fun nextIntent(productId: Long, activity: Class<*>) {
        val intent = Intent(
            this, activity
        ).apply {
            putExtra(KEY_PRODUCT, productId)
        }
        startActivity(intent)
    }

    private fun showDeleteDialog(it: Product) {
        val alertDelete = AlertDialog.Builder(this)
            .setIcon(this.getDrawable(R.drawable.ic_action_delete))
            .setTitle("Deletar?")
            .setMessage("Tem certeza que deseja deletar permanentemente o item ${it.name}")
            .setNegativeButton("Cancelar") { _, _ -> }
            .setPositiveButton("Confirmar") { d, _ ->
                database.delete(it)
                adapter.remove(it)
            }

        alertDelete.show()
    }
}

