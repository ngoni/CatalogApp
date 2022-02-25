package com.scribblex.catalogapp.ui.cataloglisting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scribblex.catalogapp.data.repository.Repository
import com.scribblex.catalogapp.ui.cataloglisting.CatalogListUiModel.ResourceUpdated
import com.scribblex.catalogapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogListViewModel @Inject constructor(
    private val repository: Repository
) :
    ViewModel() {

    private val viewState: MutableLiveData<BaseUiModel> = MutableLiveData()

    fun queryCatalog() {
        viewModelScope.launch {
            repository.fetchCatalog().catch { exception ->
                notifyError(exception)
            }.collect { results ->
                val state = ResourceUpdated(Resource.success(results.data!!))
                setViewState(state)
            }
        }
    }

    fun notifyError(exception: Throwable) {
        val message: String =
            if (exception.message.isNullOrBlank()) "Something went wrong!!" else exception.message!!

        val state = ResourceUpdated(Resource.error(message = message, null))
        setViewState(state)
    }

    fun getViewState(): LiveData<BaseUiModel> {
        return viewState
    }

    fun setViewState(resource: ResourceUpdated) {
        viewState.value = resource
    }

}