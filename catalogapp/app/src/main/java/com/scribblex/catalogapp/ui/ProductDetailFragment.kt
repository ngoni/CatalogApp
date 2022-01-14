package com.scribblex.catalogapp.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.scribblex.catalogapp.Constants
import com.scribblex.catalogapp.R
import com.scribblex.catalogapp.data.entities.ProductModel
import com.scribblex.catalogapp.databinding.FragmentProductDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)

        initViews()

        return binding.root
    }

    private fun initViews() {
        val args: ProductDetailFragmentArgs by navArgs()
        val model = args.baseModel as ProductModel
        bindDataToUi(model)
    }

    private fun bindDataToUi(productModel: ProductModel) {
        binding.apply {
            productDetailContainer.apply {

                productName.text = String.format(
                    getString(R.string.product_name),
                    productModel.productName
                )
                productPrice.text =
                    String.format(
                        getString(R.string.product_price),
                        productModel.salePrice.currency,
                        productModel.salePrice.amount
                    )

                val url = Constants.BASE_URL + productModel.url
                Glide.with(requireContext())
                    .load(url)
                    .placeholder(ColorDrawable(Color.GRAY))
                    .into(binding.productDetailContainer.productPosterImage)
            }
        }
    }
}