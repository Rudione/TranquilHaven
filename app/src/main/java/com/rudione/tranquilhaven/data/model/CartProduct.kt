package com.rudione.tranquilhaven.data.model

data class CartProduct(
    val product: Product,
    val quantity: Int,
    val selectedColor: Int? = null,
    val selectedSize: Int? = null
) {
    constructor(): this(Product(), 1, null, null)
}