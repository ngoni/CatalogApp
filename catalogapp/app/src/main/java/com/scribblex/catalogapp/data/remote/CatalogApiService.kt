package com.scribblex.catalogapp.data.remote


import com.scribblex.catalogapp.data.entities.Categories
import retrofit2.Response
import retrofit2.http.GET

interface CatalogApiService {
    @GET("/")
    suspend fun fetchCatalogData(): Response<List<Categories>>
}