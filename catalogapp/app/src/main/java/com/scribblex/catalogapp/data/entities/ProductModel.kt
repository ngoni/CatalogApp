package com.scribblex.catalogapp.data.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductModel(
    val categoryId: Int,
    val categoryName: String,
    val categoryDescription: String? = null,
    val productId: Int,
    val productName: String,
    val url: String,
    val productDescription: String? = null,
    val salePrice: SalePrice
) : Parcelable