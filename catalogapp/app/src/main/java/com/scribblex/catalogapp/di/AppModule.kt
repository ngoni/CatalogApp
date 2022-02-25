package com.scribblex.catalogapp.di

import com.scribblex.catalogapp.data.remote.CatalogApiService
import com.scribblex.catalogapp.data.remote.CatalogRemoteDataSource
import com.scribblex.catalogapp.data.repository.Repository
import com.scribblex.catalogapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = setupRetrofit()

    @Provides
    fun provideCatalogApiService(retrofit: Retrofit): CatalogApiService =
        retrofit.create(CatalogApiService::class.java)

    @Singleton
    @Provides
    fun provideCatalogRemoteDataSource(catalogApiService: CatalogApiService) =
        CatalogRemoteDataSource(catalogApiService)

    @Singleton
    @Provides
    fun provideCatalogRepository(
        catalogRemoteDataSource: CatalogRemoteDataSource
    ) = Repository(catalogRemoteDataSource)

    private fun setupRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}