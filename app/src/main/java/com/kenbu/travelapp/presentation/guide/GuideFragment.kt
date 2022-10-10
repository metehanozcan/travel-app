package com.kenbu.travelapp.presentation.guide

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.kenbu.travelapp.databinding.FragmentGuideBinding
import com.kenbu.travelapp.presentation.guide.GuideViewModel
import com.kenbu.travelapp.presentation.guide.adapter.ArticlesAdapter
import com.kenbu.travelapp.presentation.guide.adapter.CategoriesAdapter
import com.kenbu.travelapp.presentation.guide.adapter.MighNeedAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class GuideFragment : Fragment() {
    private lateinit var binding: FragmentGuideBinding
    private lateinit var articleAdapter: ArticlesAdapter
    private lateinit var mightNeedADapter: MighNeedAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter
    private val viewModel: GuideViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGuideBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeData()
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.uiState.collect {
                it.isLoading.let {
                    if (!it) {
                        Log.d("test", "guide fragment loading")
                    } else {
                        Log.d("test2222", "guide fragment loading")
                    }
                }
                it.categoryItem.let { list ->
                    categoriesAdapter.differ.submitList(list)
                }
                it.mightNeedItem.let { list ->
                    mightNeedADapter.differ.submitList(list)
                }
                it.topPickItem.let { list ->
                    articleAdapter.differ.submitList(list)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        articleAdapter = ArticlesAdapter()
        mightNeedADapter = MighNeedAdapter()
        categoriesAdapter = CategoriesAdapter()
        binding.apply {
            binding.toppickRecyclerview.adapter = articleAdapter
            binding.mightneedRecyclerview.adapter = mightNeedADapter
            binding.categoryRecyclerview.adapter = categoriesAdapter
        }
    }
}




