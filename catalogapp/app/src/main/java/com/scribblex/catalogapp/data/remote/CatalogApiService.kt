package com.scribblex.catalogapp.data.remote



import com.scribblex.catalogapp.data.entities.SearchResults
import retrofit2.Response
import retrofit2.http.GET

interface CatalogApiService {
    @GET("/")
    suspend fun fetchCatalogData(): Response<SearchResults>
}