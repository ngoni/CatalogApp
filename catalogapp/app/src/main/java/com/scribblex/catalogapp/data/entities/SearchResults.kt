package com.scribblex.catalogapp.data.entities

import com.google.gson.annotations.SerializedName


data class SearchResults(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("products")
    val products: List<Products>
)