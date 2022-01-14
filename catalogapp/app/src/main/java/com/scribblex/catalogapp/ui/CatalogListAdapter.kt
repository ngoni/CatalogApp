package com.scribblex.catalogapp.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.scribblex.catalogapp.Constants
import com.scribblex.catalogapp.Constants.CATEGORY_DRINK_ID
import com.scribblex.catalogapp.Constants.CATEGORY_FOOD_ID
import com.scribblex.catalogapp.Constants.VIEW_TYPE_HEADER
import com.scribblex.catalogapp.Constants.VIEW_TYPE_LIST_ITEM
import com.scribblex.catalogapp.Constants.VIEW_TYPE_UNSUPPORTED
import com.scribblex.catalogapp.data.entities.ProductModel
import com.scribblex.catalogapp.databinding.FragmentCatalogItemBinding
import com.scribblex.catalogapp.databinding.FragmentCatalogItemHeaderBinding

class CatalogListAdapter(
    private val callback: (ProductModel) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val values: MutableList<ProductModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> HeaderViewHolder(
                FragmentCatalogItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            VIEW_TYPE_LIST_ITEM -> CatalogItemViewHolder(
                FragmentCatalogItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> {
                throw IllegalArgumentException("Unsupported viewType: $viewType")
            }
        }
    }

    override fun onBindViewHolder(viewholder: RecyclerView.ViewHolder, position: Int) {
        val item = values[position]
        when (viewholder) {
            is HeaderViewHolder -> {
                viewholder.headerName.text = item.categoryName
            }
            is CatalogItemViewHolder -> {
                viewholder.apply {

                    productTitle.text = item.productName

                    val url = Constants.BASE_URL + item.url
                    Glide.with(imageView.context)
                        .load(url)
                        .placeholder(ColorDrawable(Color.GRAY))
                        .centerCrop()
                        .circleCrop()
                        .into(imageView)

                    productItemContainer.setOnClickListener { callback.invoke(item) }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (values[position].categoryId) {
            CATEGORY_FOOD_ID -> {
                VIEW_TYPE_HEADER
            }
            CATEGORY_DRINK_ID -> {
                VIEW_TYPE_LIST_ITEM
            }
            else -> VIEW_TYPE_UNSUPPORTED
        }
    }

    override fun getItemCount(): Int = values.size

    fun updateData(items: List<ProductModel>?) {
        items?.let {
            values.clear()
            values.addAll(items)
            notifyItemRangeChanged(0, items.size)
        }
    }

    inner class CatalogItemViewHolder(binding: FragmentCatalogItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imageView: AppCompatImageView = binding.productImage
        val productTitle: AppCompatTextView = binding.productName
        val productItemContainer: ConstraintLayout = binding.productItemContainer
    }

    inner class HeaderViewHolder(binding: FragmentCatalogItemHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val headerName: AppCompatTextView = binding.sectionHeader
    }

}