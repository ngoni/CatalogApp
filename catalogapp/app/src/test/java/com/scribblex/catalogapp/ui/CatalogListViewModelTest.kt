package com.scribblex.catalogapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.scribblex.catalogapp.data.repository.CatalogRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock

class CatalogListViewModelTest {

    private lateinit var repository: CatalogRepository
    private lateinit var viewModel: CatalogListViewModel

    @get:Rule
    var instantExectorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repository = mock()
        viewModel = CatalogListViewModel(repository)
    }

    @Test
    fun repositoryReturnsError() {

    }

    @Test
    fun repositoryReturnsValidData() {

    }
}