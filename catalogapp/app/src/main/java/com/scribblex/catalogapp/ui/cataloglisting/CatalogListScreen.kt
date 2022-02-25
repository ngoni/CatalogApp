package com.scribblex.catalogapp.ui.cataloglisting

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.scribblex.catalogapp.R
import com.scribblex.catalogapp.data.entities.BaseModel
import com.scribblex.catalogapp.data.entities.ProductModel
import com.scribblex.catalogapp.ui.CatalogAppNavigationActions
import com.scribblex.catalogapp.ui.cataloglisting.CatalogListUiModel.ResourceUpdated
import com.scribblex.catalogapp.ui.theme.getThemedColor
import com.scribblex.catalogapp.utils.Constants.BASE_URL
import com.scribblex.catalogapp.utils.Margins.DP_16
import com.scribblex.catalogapp.utils.Margins.DP_4
import com.scribblex.catalogapp.utils.Margins.DP_40
import com.scribblex.catalogapp.utils.Margins.DP_8
import com.scribblex.catalogapp.utils.Resource.Status.*


private lateinit var navigationActions: CatalogAppNavigationActions

@Composable
fun CatalogListScreen(
    _navigationActions: CatalogAppNavigationActions,
) {
    navigationActions = _navigationActions
    val viewModel = hiltViewModel<CatalogListViewModel>()
    val viewState = viewModel.getViewState().observeAsState().value
    viewState?.let { renderUI(uiModel = it) }

    // TODO: use rememberState
    viewModel.queryCatalog()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CatalogList(grouped: HashMap<String, MutableList<BaseModel>>?) {
    LazyColumn(
        modifier = Modifier
            .padding(start = DP_8, end = DP_8)
            .fillMaxSize()
    ) {
        grouped?.forEach { (categoryName, productsForCategoryName) ->
            stickyHeader {
                CategoryHeader(categoryName)
            }
            items(productsForCategoryName) { product ->
                CatalogRow(baseModel = product)
            }
        }
    }
}

@Composable
fun CategoryHeader(categoryName: String) {
    Row {
        Text(
            text = categoryName,
            modifier = Modifier.padding(start = DP_8),
            color = getThemedColor(isSystemInDarkTheme()).primary,
            style = MaterialTheme.typography.h5
        )
    }
}

@Composable
fun CatalogRow(baseModel: BaseModel) {

    val productModel: ProductModel = baseModel as ProductModel
    val url = BASE_URL + productModel.url
    val painter = rememberImagePainter(data = url, builder = {
        error(R.drawable.ic_globe)
    })

    fun openProductDetailScreen() {
        navigationActions.navigateToProductDetail(productModel.categoryId, productModel.productId)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = DP_4, top = DP_4)
            .clickable { openProductDetailScreen() }) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .animateContentSize()
                .clip(CircleShape)
                .size(DP_40)
        )
        Spacer(modifier = Modifier.width(DP_16))
        Text(
            text = productModel.productName,
            modifier = Modifier.wrapContentSize(),
            color = getThemedColor(isSystemInDarkTheme()).primary,
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
private fun renderUI(uiModel: BaseUiModel) {
    when (uiModel) {
        is ResourceUpdated -> {
            when (uiModel.resource.status) {
                SUCCESS -> {
                    if (uiModel.resource.data?.isEmpty() == true) {

                    } else {
                        CatalogList(uiModel.resource.data)
                    }
                }
                ERROR -> {
                }
                LOADING -> {
                }
            }
        }
        else -> {
            throw IllegalArgumentException()
        }
    }
}

@Preview("CategoryHeader")
@Preview("CategoryHeader (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCategoryHeader() {
    Text(
        text = "Category Name",
        modifier = Modifier.fillMaxWidth(),
        color = getThemedColor(isSystemInDarkTheme()).primary,
    )
}

@Preview("CatalogRow")
@Preview("CatalogRow (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCatalogRow() {
    val painter = painterResource(id = R.drawable.ic_globe)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = DP_4, top = DP_4)
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .animateContentSize()
                .clip(CircleShape)
                .size(DP_40)
        )
        Spacer(modifier = Modifier.width(DP_16))
        Text(
            text = "Baking Powder",
            modifier = Modifier.wrapContentSize(),
            color = getThemedColor(isSystemInDarkTheme()).primary,
            style = MaterialTheme.typography.subtitle1
        )
    }
}