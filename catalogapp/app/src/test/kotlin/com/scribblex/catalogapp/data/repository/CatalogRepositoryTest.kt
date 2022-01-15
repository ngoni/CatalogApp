package com.scribblex.catalogapp.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.scribblex.catalogapp.ui.CatalogListUiModel
import com.scribblex.catalogapp.ui.CatalogListViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@DelicateCoroutinesApi
class CatalogRepositoryTest {

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
    fun testFetchCatalogSendsDataToViewModel(): Unit = runBlocking {
        launch(Dispatchers.Main) {  // Will be launched in the mainThreadSurrogate dispatcher
            //setup
            val data = mock<CatalogListUiModel.ResourceUpdated>()
            //act
            viewModel.setViewState(data)
            //validate
            verify(viewStateObserver).onChanged(data)
        }
    }
}