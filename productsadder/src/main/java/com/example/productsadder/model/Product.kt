package com.example.productsadder.model

data class Product(
    val id: Int,
    val name: String,
    val category: String,
    val type: String,
    val price: Float,
    val productDetails: String,
    val offerPercentage: Float? = null,
    val description: String? = null,
    val colors: List<String>? = null,
    val images: List<String>
)