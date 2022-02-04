package com.scribblex.catalogapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.scribblex.catalogapp.utils.Constants.PRODUCT_MODEL
import com.scribblex.catalogapp.data.entities.ProductModel
import com.scribblex.catalogapp.ui.CatalogAppDestinations.CATALOG_LIST_ROUTE
import com.scribblex.catalogapp.ui.CatalogAppDestinations.PRODUCT_DETAIL_ROUTE

@Composable
fun CatalogAppNavGraph(
    navController: NavHostController,
    startDestination: String = CATALOG_LIST_ROUTE,
    navigationActions: CatalogAppNavigationActions
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(CATALOG_LIST_ROUTE) {
            CatalogListScreen(navigationActions)
        }
        composable(PRODUCT_DETAIL_ROUTE) { backStackEntry ->
            val productModel =
                backStackEntry.arguments?.getParcelable<ProductModel>(PRODUCT_MODEL) as ProductModel
            ProductDetailScreen(navigationActions, productModel = productModel)
        }
    }
}