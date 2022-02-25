package com.scribblex.catalogapp.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.scribblex.catalogapp.ui.theme.CatalogAppTheme

@Composable
fun CatalogApp() {
    CatalogAppTheme {
        ProvideWindowInsets {
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = MaterialTheme.colors.isLight

            SideEffect {
                systemUiController.setStatusBarColor(Color.Transparent, darkIcons = useDarkIcons)
            }

            Scaffold(topBar = {
                TopAppBar(
                    title = {
                        Text(text = "TopApp Bar")
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White,
                    elevation = 4.dp
                )
            }, content = {
                val navController = rememberNavController()
                val navigationActions = remember(navController) {
                    CatalogAppNavigationActions(navController = navController)
                }
                AppNavGraph(
                    navController = navController,
                    navigationActions = navigationActions
                )
            })
        }
    }
}