package com.scribblex.catalogapp.data.entities

import com.scribblex.catalogapp.utils.Constants.VIEW_TYPE_LIST_ITEM
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductModel(
    override val viewType: Int = VIEW_TYPE_LIST_ITEM,
    val categoryId: Int,
    val productId: Int,
    val productName: String,
    val url: String,
    val productDescription: String? = null,
    val salePrice: SalePrice?
) : BaseModel()