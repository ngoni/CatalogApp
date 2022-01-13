package com.scribblex.catalogapp.ui

import com.scribblex.catalogapp.data.entities.Products
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
import com.scribblex.catalogapp.databinding.FragmentCatalogItemBinding

class CatalogListAdapter(
    private val callback: (Products) -> Unit,
) : RecyclerView.Adapter<CatalogListAdapter.ViewHolder>() {

    private val values: MutableList<Products> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentCatalogItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.apply {
            productTitle.text = item.name 

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

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentCatalogItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imageView: AppCompatImageView = binding.productImage
        val productTitle: AppCompatTextView = binding.productName
        val productItemContainer: ConstraintLayout = binding.productItemContainer
    }

    fun updateData(items: List<Products>?) {
        items?.let {
            values.clear()
            values.addAll(items)
            notifyItemRangeChanged(0, items.size)
        }
    }

}