package com.kenbu.travelapp.presentation.tripplan.trippage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.kenbu.travelapp.databinding.FragmentTripPageBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import java.util.Date

@AndroidEntryPoint
class TripPageFragment : Fragment() {
    private lateinit var binding: FragmentTripPageBinding
    //    private lateinit var tripPageAdapter: TripPageAdapter
//    private val viewModel: TripPageViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTripPageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {

            binding.apply {
                tripplanner.visibility = View.VISIBLE
                pickDateButton.setOnClickListener {
                    val dateRange: MaterialDatePicker<Pair<Long, Long>> = MaterialDatePicker
                        .Builder
                        .dateRangePicker()
                        .setTitleText("Select a date")
                        .build()

                    dateRange.show(parentFragmentManager, "DATE_RANGE_PICKER")
                    dateRange.addOnPositiveButtonClickListener {
                        it.first    // first date
                        it.second
                        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        Log.d("Test Days","${(it.second-it.first)/(24*60*60*1000)}")
                        val date = sdf.format(it.first)
                        val date2 = sdf.format(it.second)// second date

                        Log.d("Test Trip Page","$date $date2")
                    }

                }

            }
        }

//        recyclerViewSetup()
//        observeData()
    }
}

//    private fun observeData() {
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.uiState.collect {
//                    it.isLoading.let {
//                        if (!it) {
//                            Log.d("BookMark Adapter", "tessssssss")
//                        } else {
//                            viewModel.getBookMarkData()
//                        }
//                    }
//                    it.tripItem.let { list ->
//                        Log.d("test", "BookMark Adapter")
//                        tripPageAdapter.differ.submitList(list)
//                    }
//                }
//            }
//        }
//    }

//    private fun recyclerViewSetup() {
//        tripPageAdapter = TripPageAdapter(::setBookMark)
//        binding.bookmarkRecyclerview.adapter = tripPageAdapter
//    }

//    private fun setBookMark(bookMark: TravelAppModelItem) {
//        viewModel.viewModelScope.launch {
//            viewModel.updateData(bookMark.id, bookMark)
//        }
//    }
//}

