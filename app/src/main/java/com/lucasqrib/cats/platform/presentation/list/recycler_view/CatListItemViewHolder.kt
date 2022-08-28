package com.lucasqrib.cats.platform.presentation.list.recycler_view

import com.lucasqrib.cats.R
import com.lucasqrib.cats.databinding.CatListItemBinding
import com.lucasqrib.cats.domain.model.Cat
import com.lucasqrib.cats.platform.di.GlideApp

class CatListItemViewHolder(private val binding: CatListItemBinding) : BaseCatViewHolder(binding) {
    override fun bind(item: Cat, loadMore: () -> Unit, onItemClicked: (catId: String) -> Unit) {
        GlideApp.with(itemView.context)
            .load(item.image?.url)
            .fallback(R.drawable.cat_placeholder)
            .centerCrop()
            .into(binding.ivCatThumbnail)

        binding.tvCatName.text = item.name
        binding.catItemRootContainer.setOnClickListener {
            onItemClicked(item.id)
        }


    }

}