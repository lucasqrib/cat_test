package com.lucasqrib.cats.platform.presentation.list.recycler_view

import com.lucasqrib.cats.databinding.CatListItemLoadingBinding
import com.lucasqrib.cats.domain.model.Cat

class CatListItemLoadingViewHolder(binding: CatListItemLoadingBinding) :
    BaseCatViewHolder(binding) {
    override fun bind(item: Cat, loadMore: () -> Unit, onItemClicked: (catId: String) -> Unit) {
        loadMore()
    }

}