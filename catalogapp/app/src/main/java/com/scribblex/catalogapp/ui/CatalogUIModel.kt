package com.scribblex.catalogapp.ui

import com.scribblex.catalogapp.data.entities.BaseModel
import com.scribblex.catalogapp.utils.Resource

sealed class CatalogListUiModel {

    data class ResourceUpdated(val resource: Resource<HashMap<String, MutableList<BaseModel>>>) :
        CatalogListUiModel()
}