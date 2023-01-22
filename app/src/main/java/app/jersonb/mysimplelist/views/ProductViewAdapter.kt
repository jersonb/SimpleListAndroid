package app.jersonb.mysimplelist.views

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.jersonb.mysimplelist.databinding.ProductItemBinding
import app.jersonb.mysimplelist.extensions.loadImage
import app.jersonb.mysimplelist.models.Product
import app.jersonb.mysimplelist.models.ProductDto
import app.jersonb.mysimplelist.extensions.toEntity

class ProductViewAdapter(
    private val context: Context,
    products: List<Product>,
    var onClick: (product: Product) -> Unit = {}
) : RecyclerView.Adapter<ProductViewAdapter.ViewHolder>() {

    private val products = products.toMutableList()

    inner class ViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var product: Product

        init {
            binding.root.setOnClickListener {
                if (::product.isInitialized) {
                    onClick(product)
                }
            }
        }

        fun linkTo(product: Product) {
            this.product = product

            val name = binding.labelName
            name.text = product.name

            val description = binding.labelDescription
            description.text = product.description

            val value = binding.labelValue
            product.formattedValue.also { value.text = it }

            if (!product.image.isNullOrEmpty())
                binding.imageProductItem.loadImage(product.image)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ProductItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.linkTo(product)
    }

    override fun getItemCount(): Int = products.size

    fun update(products: List<ProductDto>) {
        this.products.clear()
        this.products.addAll(
            products.map { productDto ->
                productDto.toEntity()
            })

        notifyDataSetChanged()
    }
}
