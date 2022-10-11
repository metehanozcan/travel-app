package com.kenbu.travelapp.presentation.search.searchengine

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.kenbu.travelapp.databinding.FragmentSearchEngineBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchEngineFragment : Fragment() {
    private lateinit var binding: FragmentSearchEngineBinding
    private lateinit var searchEngineAdapter: SearchEngineAdapter
    private val viewModel: SearchEngineViewModel by viewModels()
    val args: SearchEngineFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchEngineBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchString = args.searchString
        getData(searchString)
        recyclerViewSetup()
//        observeData()

    }

    private fun getData(id:String){
        viewModel.viewModelScope.launch {
            viewModel.getSearchData(id)
            delay(1000L)
            viewModel.uiState.collect{
                it.searchItem.let { list ->
                    searchEngineAdapter.differ.submitList(list)
                }

            }
        }
    }

    private fun observeData() {
        viewModel.viewModelScope.launch {
            viewModel.uiState.collect {
                it.isLoading.let {
                    if (!it) {
                        Log.d("test", "guide fragment loading")
                    } else {
                        Log.d("test2222", "guide fragment loading")
                    }
                }
                it.searchItem.let { list ->
                    Log.d("Search Engine",list.toString())
                    searchEngineAdapter.differ.submitList(list)

                }
            }
        }
    }


    private fun recyclerViewSetup() {
        searchEngineAdapter = SearchEngineAdapter()
        binding.searchEngineRecyclerview.adapter = searchEngineAdapter
    }

}

