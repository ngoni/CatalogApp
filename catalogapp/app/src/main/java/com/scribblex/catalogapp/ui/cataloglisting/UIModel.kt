package com.scribblex.catalogapp.ui.cataloglisting

import com.scribblex.catalogapp.data.entities.BaseModel
import com.scribblex.catalogapp.utils.Resource

sealed class CatalogListUiModel {
    data class ResourceUpdated(val resource: Resource<HashMap<String, MutableList<BaseModel>>>) :
        BaseUiModel()
}

sealed class ProductUIModel {
    data class ProductUpdated(val productModel: BaseModel? = null, val message: String? = null) :
        BaseUiModel()
}

sealed class BaseUiModel