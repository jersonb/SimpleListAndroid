package app.jersonb.mysimplelist.dao

import app.jersonb.mysimplelist.models.Product

class ProductDao {

    fun create(product: Product) {
        products.add(product)
    }

    fun findAll(): List<Product> {
        return products.toList()
    }

    companion object {
        private val products = mutableListOf<Product>()
    }
}