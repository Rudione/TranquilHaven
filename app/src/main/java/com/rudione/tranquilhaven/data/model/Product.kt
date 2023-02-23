package com.rudione.tranquilhaven.data.model

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
) {
    // id = 549g-5gfg5-vk43h, name = "Smart Monitor", "SmartThings", "Monitor", "15999", "32'' ...", images = mipmap
    constructor(): this(id = "0", name = " ", category = " ", productType = " ", 0f, productDetails = " ", images = emptyList())
}