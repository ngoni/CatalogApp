package com.scribblex.catalogapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.scribblex.catalogapp.data.repository.CatalogRepository
import com.scribblex.catalogapp.ui.CatalogListUiModel.ResourceUpdated
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

    private val viewStateObserver: Observer<CatalogListUiModel> = mock()
    private var repository: CatalogRepository = mock()
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
            val data: ResourceUpdated = mock()
            //act
            viewModel.setViewState(data)
            //validate
            verify(viewStateObserver).onChanged(data)
        }
    }

    @Test
    fun testNotifyError() {

    }
}