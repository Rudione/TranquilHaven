package com.rudione.tranquilhaven.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartProduct(
    val product: Product,
    val quantity: Int,
    val selectedColor: Int? = null,
    val selectedSize: Int? = null
): Parcelable {
    constructor(): this(Product(), 1, null, null)
}