package com.kenbu.travelapp.presentation.tripplan.bookmarkpage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import com.kenbu.travelapp.databinding.FragmentTripPlanBookmarkBinding
import com.kenbu.travelapp.domain.model.TravelAppModelItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookMarkFragment : Fragment() {
    private lateinit var binding: FragmentTripPlanBookmarkBinding
    private lateinit var bookMarkAdapter: BookMarkAdapter
    private val viewModel: BookMarkViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTripPlanBookmarkBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewSetup()
        observeData()
    }


    private fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    it.isLoading.let {
                        if (!it) {
                            Log.d("BookMark Adapter", "tessssssss")
                        } else {
                            viewModel.getBookMarkData()
                        }
                    }
                    it.bookMarkItem.let { list ->
                        Log.d("test", "BookMark Adapter")
                        bookMarkAdapter.differ.submitList(list)
                    }
                }
            }
        }
    }

    private fun recyclerViewSetup() {
        bookMarkAdapter = BookMarkAdapter(::setBookMark)
        binding.bookmarkRecyclerview.adapter = bookMarkAdapter
    }

    private fun setBookMark(bookMark: TravelAppModelItem) {
        viewModel.viewModelScope.launch {
            viewModel.updateData(bookMark.id, bookMark)
        }
    }
}

