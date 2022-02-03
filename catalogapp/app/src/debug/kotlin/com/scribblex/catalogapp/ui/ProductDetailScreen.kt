package com.scribblex.catalogapp.ui

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ProductDetailScreen(navigationActions: CatalogAppNavigationActions) {
    Button(onClick = { navigationActions.navigateToCatalogList() }) {
        Text(text = "Back")
    }
}
