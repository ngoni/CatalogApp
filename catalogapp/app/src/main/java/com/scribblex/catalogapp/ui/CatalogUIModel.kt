package com.scribblex.catalogapp.ui

import com.scribblex.catalogapp.data.entities.ProductModel
import com.scribblex.catalogapp.utils.Resource

sealed class CatalogListUiModel {

    data class ResourceUpdated(val resource: Resource<MutableList<ProductModel>>) :
        CatalogListUiModel()
}