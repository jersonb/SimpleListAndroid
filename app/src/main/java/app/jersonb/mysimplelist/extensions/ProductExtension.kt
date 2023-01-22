package app.jersonb.mysimplelist.extensions

import app.jersonb.mysimplelist.models.Product
import app.jersonb.mysimplelist.models.ProductDto

fun Product.toDto(): ProductDto {
    return ProductDto(
        name = this.name,
        value = this.value,
        description = this.description,
        url = this.image
    )
}

fun ProductDto.toEntity(): Product {
    return Product(
        name = this.name,
        value = this.value,
        description = this.description,
        image = this.url
    )
}
