package com.scribblex.catalogapp.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.scribblex.catalogapp.R
import com.scribblex.catalogapp.data.entities.BaseModel
import com.scribblex.catalogapp.data.entities.ProductModel
import com.scribblex.catalogapp.ui.cataloglisting.BaseUiModel
import com.scribblex.catalogapp.ui.cataloglisting.ProductUIModel.ProductUpdated
import com.scribblex.catalogapp.ui.productdetail.ProductDetailViewModel
import com.scribblex.catalogapp.utils.Constants.BASE_URL
import com.scribblex.catalogapp.utils.Constants.DEFAULT_AMOUNT
import com.scribblex.catalogapp.utils.Constants.DEFAULT_CURRENCY
import com.scribblex.catalogapp.utils.Margins.DP_0
import com.scribblex.catalogapp.utils.Margins.DP_24
import com.scribblex.catalogapp.utils.Margins.DP_8


private lateinit var navigationActions: CatalogAppNavigationActions

@Composable
fun ProductDetailScreen(
    _navigationActions: CatalogAppNavigationActions,
    categoryId: Int,
    productId: Int
) {
    navigationActions = _navigationActions
    val viewModel = hiltViewModel<ProductDetailViewModel>()
    val viewState = viewModel.getViewState().observeAsState().value
    viewState?.let { renderUI(it) }

    if (viewState == null) {
        viewModel.getProduct(categoryId, productId)
    }
}

@Composable
fun renderUI(uiModel: BaseUiModel) {
    when (uiModel) {
        is ProductUpdated -> {
            uiModel.productModel?.let { ProductDetailContent(it) }
        }
        else -> {
            throw IllegalArgumentException()
        }
    }
}

@Composable
fun ProductDetailContent(baseModel: BaseModel) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (textColumn, productImageView) = createRefs()

        val productModel = baseModel as ProductModel
        val url = BASE_URL + productModel.url
        val painter = rememberImagePainter(data = url, builder = {
            error(R.drawable.ic_globe)
        })
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .constrainAs(productImageView) {
                    top.linkTo(parent.top, margin = DP_0)
                }
                .aspectRatio(1f, false)
                .animateContentSize()
        )
        Column(modifier = Modifier
            .constrainAs(textColumn) {
                top.linkTo(productImageView.bottom, margin = DP_8)
            }
            .fillMaxSize()
        ) {
            val productName =
                stringResource(
                    R.string.product_name,
                    productModel.productName
                )
            Text(
                text = productName,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(DP_24))
            val productPrice =
                stringResource(
                    R.string.product_price,
                    productModel.salePrice?.currency ?: DEFAULT_CURRENCY,
                    productModel.salePrice?.amount ?: DEFAULT_AMOUNT
                )
            Text(
                text = productPrice,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}