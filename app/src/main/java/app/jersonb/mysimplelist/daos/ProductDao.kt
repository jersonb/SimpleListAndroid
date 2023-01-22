package app.jersonb.mysimplelist.daos

import app.jersonb.mysimplelist.models.Product


class ProductDao {

    fun create(product: Product) {
        products.add(product)
    }

    fun findAll(): List<Product> {
        return products.toList()
    }

    companion object {
        private val products = mutableListOf(
            Product("Teste01","Produto de Teste 01", "1.99"),

        )
    }
}