package com.scribblex.catalogapp.data.repository

import com.scribblex.catalogapp.data.entities.SearchResults
import com.scribblex.catalogapp.data.remote.CatalogRemoteDataSource
import com.scribblex.catalogapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatalogRepository @Inject constructor(
    remoteDataSource: CatalogRemoteDataSource
) {
    val catalog: Flow<Resource<SearchResults>> = remoteDataSource.catalogList
}
