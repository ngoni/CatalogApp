package com.scribblex.catalogapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.scribblex.catalogapp.R
import com.scribblex.catalogapp.data.entities.Products
import com.scribblex.catalogapp.databinding.FragmentCatalogListBinding
import com.scribblex.catalogapp.utils.Resource
import com.scribblex.catalogapp.utils.ViewUtils.hideProgressBar
import com.scribblex.catalogapp.utils.ViewUtils.showProgressBar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CatalogListFragment : Fragment() {

    private lateinit var binding: FragmentCatalogListBinding
    private lateinit var navController: NavController
    private lateinit var catalogListAdapter: CatalogListAdapter

    private val viewModel: CatalogListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatalogListBinding.inflate(inflater, container, false)

        setupAdapter()
        initViews()
        observeViewModel()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    private fun initViews() {
        binding.recyclerview.apply {
            adapter = catalogListAdapter
            layoutManager =
                LinearLayoutManager(requireContext())
        }
    }


    private fun setupAdapter() {
        val callback = fun(product: Products) {
            val action =
                CatalogListFragmentDirections.actionOpenProductDetailScreen(product)
            navController.navigate(action)
        }
        CatalogListAdapter(callback = callback).also { catalogListAdapter = it }
    }

    private fun observeViewModel() {
        viewModel.catalogList.observe(requireActivity(), Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    hideProgressBar(binding.progressBar)
                    if (it.data?.isEmpty() == true) {
                        binding.resultsState.apply {
                            text = getString(R.string.no_results)
                            visibility = View.VISIBLE
                        }
                    } else {
                        binding.resultsState.visibility = View.GONE
                        catalogListAdapter.updateData(it.data)
                    }
                }
                Resource.Status.ERROR -> {
                    hideProgressBar(binding.progressBar)
                    binding.resultsState.apply {
                        text = it.message
                        visibility = View.VISIBLE
                    }
                }
                Resource.Status.LOADING -> {
                    binding.resultsState.visibility = View.GONE
                    showProgressBar(binding.progressBar)
                }
            }
        })
    }
}