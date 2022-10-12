package com.kenbu.travelapp.presentation.home.homecategorypages.all

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.kenbu.travelapp.databinding.FragmentCategoriesAllBinding
import com.kenbu.travelapp.domain.model.TravelAppModelItem
import com.kenbu.travelapp.presentation.home.adapter.HomeAdapter
import com.kenbu.travelapp.presentation.home.homecategorypages.AllViewModel
import com.kenbu.travelapp.presentation.search.adapter.NearbyAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesAllBinding
    private lateinit var allAdapter: HomeAdapter
    private val viewModel: AllViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesAllBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewSetup()
        observeData()

    }

    private fun observeData() {
        viewModel.viewModelScope.launch {
            viewModel.uiState.collect {
                it.isLoading.let {

                }
                it.homeItems.let { list ->
                    Log.d("home", list.toString())
                    allAdapter.differ.submitList(list)
                }
            }
        }
    }

    private fun recyclerViewSetup() {
        allAdapter = HomeAdapter()
        binding.apply {
           categoriesAllRecyclerview.adapter = allAdapter
        }
    }

}







