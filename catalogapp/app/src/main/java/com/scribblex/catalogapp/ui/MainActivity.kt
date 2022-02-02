package com.scribblex.catalogapp.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.scribblex.catalogapp.R
import com.scribblex.catalogapp.databinding.ActivityMainBinding
import com.scribblex.catalogapp.ui.theme.CatalogAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { CatalogApp() }
        //  setupNavController()
    }

    private fun setupNavController() {
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.toolbar, navController)
    }

    @Composable
    fun CatalogApp() {
        CatalogAppTheme {
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
                Text(text = "Body Content")
            })
        }
    }

    @Preview("CatalogApp")
    @Preview("CatalogApp (dark)", uiMode = UI_MODE_NIGHT_YES)
    @Composable
    fun PreviewCatalogApp() {
        CatalogApp()
    }
}