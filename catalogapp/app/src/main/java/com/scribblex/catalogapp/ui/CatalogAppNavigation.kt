package com.scribblex.catalogapp.ui

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.scribblex.catalogapp.ui.CatalogAppDestinations.CATALOG_LIST_ROUTE
import com.scribblex.catalogapp.ui.CatalogAppDestinations.PRODUCT_DETAIL_ROUTE

/**
 * Destinations used in the [CatalogApp].
 */
object CatalogAppDestinations {
    const val CATALOG_LIST_ROUTE = "home"
    const val PRODUCT_DETAIL_ROUTE = "product_detail"
}

/**
 * Models the navigation actions in the app.
 */
class CatalogAppNavigationActions(navController: NavHostController) {
    val navigateToCatalogList = {
        navController.navigate(CATALOG_LIST_ROUTE) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // re-selecting the same item
            launchSingleTop = true
            // Restore state when re-selecting a previously selected item
            restoreState = true
        }
    }

    val navigateToProductDetail = {
        navController.navigate(PRODUCT_DETAIL_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}