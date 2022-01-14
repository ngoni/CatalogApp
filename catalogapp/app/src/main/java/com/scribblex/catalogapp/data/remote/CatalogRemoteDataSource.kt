package com.scribblex.catalogapp.data.remote


import com.scribblex.catalogapp.data.entities.Categories
import com.scribblex.catalogapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CatalogRemoteDataSource @Inject constructor(
    private val catalogApiService: CatalogApiService
) : BaseDataSource() {
    val catalogList: Flow<Resource<List<Categories>>> = flow {
        val catalog = getResult { catalogApiService.fetchCatalogData() }
        emit(catalog)
    }
}