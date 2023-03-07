package com.rudione.tranquilhaven.data.model

sealed class Category(val category: String) {

    object AmazonAlexa: Category("AmazonAlexa")
    object GoogleHome: Category("GoogleHome")
    object HomeKit: Category("HomeKit")
    object SmartThings: Category("SmartThings")
}