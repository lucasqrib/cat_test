package com.lucasqrib.cats.platform.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.lucasqrib.cats.R
import com.lucasqrib.cats.databinding.FragmentCatListBinding
import com.lucasqrib.cats.platform.presentation.CatSharedViewModel
import com.lucasqrib.cats.platform.presentation.list.recycler_view.CatListRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatListFragment : Fragment() {

    private val viewModel: CatSharedViewModel by activityViewModels()

    private lateinit var binding: FragmentCatListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvAdapter = CatListRecyclerAdapter(loadMore = viewModel::loadCats, ::navigateToDetail)

        binding.rvCatList.apply {
            layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = rvAdapter
        }

        viewModel.apply {
            binding.srCatList.setOnRefreshListener {
                loadCats(true)
            }

            getCatsLiveData().observe(viewLifecycleOwner) {
                rvAdapter.addItems(it)
                onLoadDone()

            }
            getErrorLiveData().observe(viewLifecycleOwner) {

                if (it) {
                    onLoadDone()
                    Snackbar.make(binding.root, getString(R.string.loading_error), 5000)
                        .setAction(R.string.try_again) {
                            loadCats()
                        }.show()
                }
            }

        }
    }

    private fun onLoadDone() {
        binding.clpCatList.visibility = View.GONE
        binding.srCatList.isRefreshing = false
    }

    private fun navigateToDetail(catId: String) {
        CatListFragmentDirections.showCatDetail().apply { setCatId(catId) }
            .let {
                findNavController().navigate(it)
            }
    }

}