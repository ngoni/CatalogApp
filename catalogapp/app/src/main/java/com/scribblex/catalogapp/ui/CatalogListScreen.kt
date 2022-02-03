package com.scribblex.catalogapp.ui

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun CatalogListScreen(navigationActions: CatalogAppNavigationActions) {
    Button(onClick = { navigationActions.navigateToProductDetail() }) {
        Text(text = "Navigate")
    }
}
