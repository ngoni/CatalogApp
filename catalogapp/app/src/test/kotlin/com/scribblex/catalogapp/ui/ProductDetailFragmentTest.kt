package com.scribblex.catalogapp.ui

import android.os.Bundle
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.scribblex.catalogapp.utils.Constants
import com.scribblex.catalogapp.R
import com.scribblex.catalogapp.data.entities.ProductModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class ProductDetailFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testProductNameIsVisibleInProductDetailsFragment() {
        val productModel = mock<ProductModel>()
        val fragmentArgs : Bundle = bundleOf(Pair(Constants.PRODUCT_MODEL, productModel))
        launchFragmentInHiltContainer<ProductDetailFragment>(fragmentArgs = fragmentArgs) {
            this.view?.findViewById<AppCompatTextView>(R.id.product_name)
                ?.let { assert(it.isVisible) }
        }
    }

}