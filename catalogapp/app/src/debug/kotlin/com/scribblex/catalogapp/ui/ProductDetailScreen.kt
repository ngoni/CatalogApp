package com.scribblex.catalogapp.ui

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import com.scribblex.catalogapp.utils.Constants
import com.scribblex.catalogapp.utils.Constants.DEFAULT_AMOUNT
import com.scribblex.catalogapp.utils.Constants.DEFAULT_CURRENCY
import com.scribblex.catalogapp.utils.Margins.DP_0
import com.scribblex.catalogapp.utils.Margins.DP_24
import com.scribblex.catalogapp.utils.Margins.DP_8
import com.scribblex.catalogapp.R
import com.scribblex.catalogapp.data.entities.ProductModel

@Composable
fun ProductDetailScreen(
    navigationActions: CatalogAppNavigationActions,
    productModel: ProductModel
) {
    productDetailBody(productModel)
}

@Preview("ProductDetailScreen")
@Preview("ProductDetailScreen (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewProductDetailScreen() {
    // TODO: look at how preview renders when there's dependencies
//    ProductDetailScreen(navigationActions =, productModel =)
}


@Composable
fun productDetailBody(productModel: ProductModel) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (textColumn, productImageView) = createRefs()

        val url = Constants.BASE_URL + productModel.url
        val painter = rememberImagePainter(data = url)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .constrainAs(productImageView) {
                    top.linkTo(parent.top, margin = DP_0)
                }
                .wrapContentSize()
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
                    R.string.product_name, productModel.productName
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