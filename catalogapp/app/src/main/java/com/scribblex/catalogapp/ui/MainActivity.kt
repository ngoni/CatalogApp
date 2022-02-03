package com.scribblex.catalogapp.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { CatalogApp() }
    }

    @Preview("CatalogApp")
    @Preview("CatalogApp (dark)", uiMode = UI_MODE_NIGHT_YES)
    @Composable
    fun PreviewCatalogApp() {
        CatalogApp()
    }
}