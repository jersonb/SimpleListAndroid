package app.jersonb.mysimplelist.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.jersonb.mysimplelist.R
import app.jersonb.mysimplelist.models.Product

class ProductViewAdapter(
    private val context: Context,
    products: List<Product>
) : RecyclerView.Adapter<ProductViewAdapter.ViewHolder>() {

    private val products = products.toMutableList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun linkTo(product: Product) {
            val name = itemView.findViewById<TextView>(R.id.label_name)
            name.text = product.name

            val description = itemView.findViewById<TextView>(R.id.label_description)
            description.text = product.description

            val value = itemView.findViewById<TextView>(R.id.label_value)
            "R$ ${product.value}".also { value.text = it }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.product_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.linkTo(product)
    }

    override fun getItemCount(): Int = products.size

    fun update(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }
}
