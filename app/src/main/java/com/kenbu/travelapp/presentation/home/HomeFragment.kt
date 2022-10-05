package com.kenbu.travelapp.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.kenbu.travelapp.databinding.FragmentHomeBinding
import com.kenbu.travelapp.presentation.home.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeData()

    }


    private fun observeData(){
        viewModel.viewModelScope.launch {
            viewModel.uiState.collect{
                it.isLoading.let {
                    if(!it){
                       Log.d("test1111","tessssssss")
                    }else{
                        Log.d("test2222","tessssssss")
                    }
                }

                it.homeItems.let {list ->
                    adapter.differ.submitList(list)
                }
            }
        }

    }
    private fun setupRecyclerView() {
        adapter = HomeAdapter()
        binding.apply {
            binding.recyclerView.adapter = adapter
        }
    }


}