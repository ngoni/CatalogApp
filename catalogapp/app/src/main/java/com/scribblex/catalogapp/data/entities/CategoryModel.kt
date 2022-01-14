package com.scribblex.catalogapp.data.entities

import com.scribblex.catalogapp.Constants.VIEW_TYPE_HEADER
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryModel(
    override val viewType: Int = VIEW_TYPE_HEADER,
    val categoryId: Int,
    val categoryName: String,
    val categoryDescription: String? = null,
) : BaseModel()
