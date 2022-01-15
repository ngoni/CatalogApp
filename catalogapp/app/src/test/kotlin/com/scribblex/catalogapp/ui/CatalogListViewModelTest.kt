package com.scribblex.catalogapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.scribblex.catalogapp.data.repository.CatalogRepository
import com.scribblex.catalogapp.ui.CatalogListUiModel.ResourceUpdated
import com.scribblex.catalogapp.utils.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock

@DelicateCoroutinesApi
class CatalogListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val viewStateObserver = mock<Observer<CatalogListUiModel>>()
    private var repository = mock<CatalogRepository>()
    private lateinit var viewModel: CatalogListViewModel
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = CatalogListViewModel(repository)
        viewModel.getViewState().observeForever(viewStateObserver)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun testViewModelSetsDataToView(): Unit = runBlocking {
        launch(Dispatchers.Main) {  // Will be launched in the mainThreadSurrogate dispatcher
            //setup
            val data = mock<ResourceUpdated>()
            //act
            viewModel.setViewState(data)
            //validate
            verify(viewStateObserver).onChanged(data)
        }
    }

    @Test
    fun testNotifyError(): Unit = runBlocking {
        launch(Dispatchers.Main) {  // Will be launched in the mainThreadSurrogate dispatcher
            //setup
            val exception = mock<Throwable>()
            //act
            viewModel.notifyError(exception)
            //validate
            val state = ResourceUpdated(Resource.error(message = "Something went wrong!!", null))
            verify(viewStateObserver).onChanged(state)
        }
    }
}