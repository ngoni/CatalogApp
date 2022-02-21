package com.scribblex.catalogapp.ui

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.scribblex.catalogapp.data.entities.BaseModel
import com.scribblex.catalogapp.data.entities.ProductModel
import com.scribblex.catalogapp.ui.theme.getThemedColor
import com.scribblex.catalogapp.utils.Constants
import com.scribblex.catalogapp.utils.Margins.DP_16
import com.scribblex.catalogapp.utils.Resource

@Composable
fun CatalogListScreen(
    navigationActions: CatalogAppNavigationActions,
) {
    val viewModel = hiltViewModel<CatalogListViewModel>()
    val viewState = viewModel.getViewState().observeAsState().value
    viewState?.let { renderUI(uiModel = it) }
    viewModel.queryCatalog()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CatalogList(grouped: HashMap<String, MutableList<BaseModel>>?) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
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
    Text(
        text = categoryName,
        modifier = Modifier.fillMaxWidth(),
        color = getThemedColor(isSystemInDarkTheme()).primary,
    )
}

@Composable
fun CatalogRow(baseModel: BaseModel) {

    val productModel: ProductModel = baseModel as ProductModel
    val url = Constants.BASE_URL + productModel.url
    val painter = rememberImagePainter(data = url)

    Row {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .wrapContentSize()
                .animateContentSize()
        )
        Spacer(modifier = Modifier.width(DP_16))
        Text(
            text = productModel.productName,
            modifier = Modifier.fillMaxWidth(),
            color = getThemedColor(isSystemInDarkTheme()).primary,
        )
    }
}

@Composable
private fun renderUI(uiModel: CatalogListUiModel) {
    when (uiModel) {
        is CatalogListUiModel.ResourceUpdated -> {
            when (uiModel.resource.status) {
                Resource.Status.SUCCESS -> {
                    if (uiModel.resource.data?.isEmpty() == true) {

                    } else {
                        CatalogList(uiModel.resource.data)
                    }
                }
                Resource.Status.ERROR -> {
                }
                Resource.Status.LOADING -> {
                }
            }
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

//@Preview("CatalogRow")
//@Preview("CatalogRow (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun PreviewCatalogRow() {
//    // TODO: Fina a way of using a drawable resource to load an image
//    val painter = painterResource(id = R.drawable.ic_globe)
//    Row {
//        Image(
//            painter = painter,
//            contentDescription = null,
//            modifier = Modifier
//                .wrapContentSize()
//                .animateContentSize()
//        )
//        Spacer(modifier = Modifier.width(DP_16))
//        Text(
//            text = "Baking Powder",
//            modifier = Modifier.fillMaxWidth(),
//            color = getThemedColor(isSystemInDarkTheme()).primary,
//        )
//    }
//}