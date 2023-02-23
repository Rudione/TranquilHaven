package com.rudione.tranquilhaven.data.paging

import com.rudione.tranquilhaven.data.model.Product

internal data class PagingInfo(
    var page: Long = 1,
    var oldBestProducts: List<Product> = emptyList(),
    var isPagingEnd: Boolean = false
)