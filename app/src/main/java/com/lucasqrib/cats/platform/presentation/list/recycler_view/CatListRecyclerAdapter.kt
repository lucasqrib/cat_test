package com.lucasqrib.cats.platform.presentation.list.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucasqrib.cats.databinding.CatListItemBinding
import com.lucasqrib.cats.databinding.CatListItemLoadingBinding
import com.lucasqrib.cats.domain.model.Cat

private const val LOADING_VIEW_TYPE = 0
private const val CAT_ITEM_VIEW_TYPE = 1

class CatListRecyclerAdapter(
    private val loadMore: () -> Unit,
    private val onItemClicked: (catId: String) -> Unit,
) :
    RecyclerView.Adapter<BaseCatViewHolder>() {

    private val catList: ArrayList<Cat> = arrayListOf()
    private var endOfListReached = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            LOADING_VIEW_TYPE -> CatListItemLoadingViewHolder(
                CatListItemLoadingBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
            else ->
                CatListItemViewHolder(
                    CatListItemBinding.inflate(layoutInflater, parent, false)
                )
        }
    }

    override fun onBindViewHolder(holder: BaseCatViewHolder, position: Int) {
        holder.bind(catList[position], loadMore, onItemClicked)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == catList.size - 1 && !endOfListReached) LOADING_VIEW_TYPE else CAT_ITEM_VIEW_TYPE
    }

    fun addItems(newItems: List<Cat>) {
        if (newItems == catList) {
            endOfListReached = true
            notifyItemChanged(catList.size)
            return
        }
        val oldListSize = catList.size
        catList.clear()
        catList.addAll(newItems)
        notifyItemRangeInserted(oldListSize, catList.size - oldListSize)
    }

}