package com.scribblex.catalogapp.ui

import android.util.Log
import com.scribblex.catalogapp.data.entities.Products
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _catalogList = MutableLiveData<Resource<List<Products>>>()

    init {
        viewModelScope.launch {
            repository.catalog.catch { exception ->
                notifyError(exception)
            }.collect { results ->
                val data = results.data!!.products
                _catalogList.value = Resource.success(data)
            }
        }
    }

    private fun notifyError(exception: Throwable) {
        Log.d("CatalogListViewModel", "Throwable: " + exception.message)
    }

    val catalogList: LiveData<Resource<List<Products>>>
        get() = _catalogList

}