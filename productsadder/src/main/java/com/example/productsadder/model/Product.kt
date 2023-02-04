package com.example.productsadder.model

data class Product(
    val id: String,
    val name: String,
    val category: String,
    val productType: String,
    val price: Float,
    val productDetails: String,
    val offerPercentage: Float? = null,
    val description: String? = null,
    val colors: List<Int>? = null,
    val images: List<String>
)