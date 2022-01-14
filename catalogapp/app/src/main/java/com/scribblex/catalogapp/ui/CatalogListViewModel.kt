package com.scribblex.catalogapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scribblex.catalogapp.utils.StringUtils.getString
import com.scribblex.catalogapp.R
import com.scribblex.catalogapp.data.repository.CatalogRepository
import com.scribblex.catalogapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogListViewModel @Inject constructor(
    private val repository: CatalogRepository
) :
    ViewModel() {

    private val viewState: MutableLiveData<CatalogListUiModel> = MutableLiveData()

    fun queryCatalog() {
        viewModelScope.launch {
            repository.catalog.catch { exception ->
                notifyError(exception)
            }.collect { results ->
                viewState.value =
                    CatalogListUiModel.ResourceUpdated(Resource.success(results.data!!))
            }
        }
    }

    private fun notifyError(exception: Throwable) {
        val message: String = if (exception.message.isNullOrBlank()) getString(R.string.error_message) else exception.message!!
        viewState.value =
            CatalogListUiModel.ResourceUpdated(Resource.error(message = message, null))
    }

    fun getViewState(): LiveData<CatalogListUiModel> {
        return viewState
    }

}