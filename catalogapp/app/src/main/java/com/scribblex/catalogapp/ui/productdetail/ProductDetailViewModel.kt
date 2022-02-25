package com.scribblex.catalogapp.ui.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scribblex.catalogapp.data.repository.Repository
import com.scribblex.catalogapp.ui.cataloglisting.BaseUiModel
import com.scribblex.catalogapp.ui.cataloglisting.ProductUIModel.ProductUpdated
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val repository: Repository
) :
    ViewModel() {

    private val viewState: MutableLiveData<BaseUiModel> = MutableLiveData()

    fun getProduct(categoryId: Int, productId: Int) {
        viewModelScope.launch {
            repository.getProduct(categoryId, productId).catch { exception ->
                notifyError(exception)
            }.collect { productModel ->
                setViewState(ProductUpdated(productModel))
            }
        }
    }

    fun getViewState(): LiveData<BaseUiModel> {
        return viewState
    }

    private fun setViewState(baseUiModel: BaseUiModel) {
        viewState.value = baseUiModel
    }

    private fun notifyError(exception: Throwable) {
        val message: String =
            if (exception.message.isNullOrBlank()) "Something went wrong!!" else exception.message!!
        setViewState(ProductUpdated(message = message))
    }

}