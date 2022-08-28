package com.lucasqrib.cats.platform.presentation.list.recycler_view

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.lucasqrib.cats.domain.model.Cat

abstract class BaseCatViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(item: Cat, loadMore: () -> Unit, onItemClicked: (catId: String) -> Unit)
}