package com.rudione.tranquilhaven.data.model.order

import com.rudione.tranquilhaven.data.model.Address
import com.rudione.tranquilhaven.data.model.CartProduct

data class Order(
    val orderStatus: String,
    val totalPrice: Float,
    val products: List<CartProduct>,
    val address: Address
)