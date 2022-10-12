package com.kenbu.travelapp.presentation.search.searchengine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
    }

    /*
    Kullanıcıdan alınan string parametresini api'ye gönderir.
    Geri dönen liste ile arama sonuçlarını gösterir.
    Makes api call for search paramaters
    Returns Search List
     */
    private fun getData(id: String) {
        viewModel.viewModelScope.launch {
            viewModel.getSearchData(id)
            delay(1000L)
            viewModel.uiState.collect {
                it.searchItem.let { list ->
                    if (list!!.size == 0) {
                        binding.apply {
                            searchEngineText.visibility = View.VISIBLE
                            searchEngineErrorImage.visibility = View.VISIBLE
                        }
                    } else {
                        binding.apply {
                            searchEngineText.visibility = View.GONE
                            searchEngineErrorImage.visibility = View.GONE
                        }
                        searchEngineAdapter.differ.submitList(list)
                    }
                }
            }
        }
    }


    private fun recyclerViewSetup() {
        searchEngineAdapter = SearchEngineAdapter()
        binding.searchEngineRecyclerview.adapter = searchEngineAdapter
    }

}

