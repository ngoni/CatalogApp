package com.scribblex.catalogapp.data.repository

import com.scribblex.catalogapp.data.entities.BaseModel
import com.scribblex.catalogapp.data.entities.CategoryModel
import com.scribblex.catalogapp.data.entities.ProductModel
import com.scribblex.catalogapp.data.remote.CatalogRemoteDataSource
import com.scribblex.catalogapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class CatalogRepository @Inject constructor(
    private val remoteDataSource: CatalogRemoteDataSource
) {

    fun fetchCatalog(): Flow<Resource<HashMap<String, MutableList<BaseModel>>>> {
        return remoteDataSource.catalogList
            .transform {

                // TODO: 2022/01/14 Optimize the code below
                val groupedMap: HashMap<String, MutableList<BaseModel>> = HashMap()
                val productList: MutableList<BaseModel> = mutableListOf()

                for (item in it.data!!) {

                    val categoryModel = CategoryModel(
                        categoryId = item.id,
                        categoryName = item.name, categoryDescription = item.description
                    )

                    for (product in item.products) {
                        val productModel = ProductModel(
                            categoryId = item.id,
                            productId = product.id, productName = product.name, url = product.url,
                            productDescription = product.description, salePrice = product.salePrice
                        )
                        productList.add(productModel)
                    }

                    groupedMap[categoryModel.categoryName] = productList.toMutableList()
                    productList.clear()
                }
                emit(Resource.success(groupedMap))
            }
    }
}
