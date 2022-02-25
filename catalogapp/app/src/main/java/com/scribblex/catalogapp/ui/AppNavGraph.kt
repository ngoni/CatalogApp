package com.scribblex.catalogapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.scribblex.catalogapp.ui.CatalogAppDestinations.CATALOG_LIST_ROUTE
import com.scribblex.catalogapp.ui.CatalogAppDestinations.PRODUCT_DETAIL_ROUTE
import com.scribblex.catalogapp.ui.cataloglisting.CatalogListScreen
import com.scribblex.catalogapp.utils.Constants.CATEGORY_ID
import com.scribblex.catalogapp.utils.Constants.PRODUCT_ID
import com.scribblex.catalogapp.utils.Constants.UN_DEFINED

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String = CATALOG_LIST_ROUTE,
    navigationActions: CatalogAppNavigationActions
) {
    NavHost(navController = navController, startDestination = startDestination) {

        composable(route = CATALOG_LIST_ROUTE) {
            CatalogListScreen(navigationActions)
        }

        val args = listOf(
            navArgument(CATEGORY_ID) {
                type = NavType.IntType
            },
            navArgument(PRODUCT_ID) {
                type = NavType.IntType
            }
        )

        val route = "$PRODUCT_DETAIL_ROUTE/{categoryId}/{productId}"
        composable(route = route, arguments = args) { backStackEntry ->
            val categoryId =
                backStackEntry.arguments?.getInt(CATEGORY_ID) ?: UN_DEFINED
            val productId =
                backStackEntry.arguments?.getInt(PRODUCT_ID) ?: UN_DEFINED
            ProductDetailScreen(navigationActions, categoryId = categoryId, productId = productId)
        }
    }
}