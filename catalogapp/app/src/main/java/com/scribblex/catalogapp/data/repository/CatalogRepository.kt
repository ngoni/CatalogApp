package com.scribblex.catalogapp.data.repository

import com.scribblex.catalogapp.data.entities.ProductModel
import com.scribblex.catalogapp.data.remote.CatalogRemoteDataSource
import com.scribblex.catalogapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class CatalogRepository @Inject constructor(
    remoteDataSource: CatalogRemoteDataSource
) {
    val catalog: Flow<Resource<MutableList<ProductModel>>> by lazy {
        remoteDataSource.catalogList
            .transform {
                val productsList: MutableList<ProductModel> = mutableListOf()
                // TODO: 2022/01/14 Optimize the code below
                for (item in it.data!!) {
                    for (product in item.products) {
                        val productModel = ProductModel(
                            categoryId = item.id,
                            categoryName = item.name,
                            productId = product.id, productName = product.name, url = product.url,
                            productDescription = product.description, salePrice = product.salePrice
                        )
                        productsList.add(productModel)
                    }
                }
                emit(Resource.success(productsList))
            }
    }
}
