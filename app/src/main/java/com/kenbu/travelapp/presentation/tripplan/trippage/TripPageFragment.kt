package com.kenbu.travelapp.presentation.tripplan.trippage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.kenbu.travelapp.databinding.FragmentTripPageBinding
import com.kenbu.travelapp.domain.model.TripPlanModel
import com.kenbu.travelapp.presentation.tripplan.bookmarkpage.BookMarkAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class TripPageFragment : Fragment() {
    private lateinit var binding: FragmentTripPageBinding
    private var bundle = Bundle()
    private val viewModel: TripPageViewModel by viewModels()
    private lateinit var tripPageAdapter: TripPageAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTripPageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewSetup()
        observeData()
        initBindings()
    }


    private fun initBindings() {
        var date1 = ""
        var date2 = ""
        var daysToStay = ""
        val dateRange: MaterialDatePicker<Pair<Long, Long>> = MaterialDatePicker
            .Builder
            .dateRangePicker()
            .setTitleText("Select a date")
            .build()

        binding.floatingActionButton.setOnClickListener {
            binding.apply {
                tripplanner.visibility = View.VISIBLE
                pickDateButton.setOnClickListener {
                    dateRange.show(parentFragmentManager, "DATE_RANGE_PICKER")
                    dateRange.addOnPositiveButtonClickListener {
                        try {
                            it.first    // first date
                            it.second
                            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                            daysToStay = ((it.second - it.first) / (24 * 60 * 60 * 1000)).toString()
                            date1 = sdf.format(it.first)
                            date2 = sdf.format(it.second)
                            Log.d("Test Trip Page", "$date1 $date2")
                            pickDateButton.text = "$date1 + $date2"
                            bundle.putString("date1", date1)
                            bundle.putString("date2", date2)
                            bundle.putString("daystostay", daysToStay)
                        } catch (e: Exception) {
                        }//
                    }
                }
                addTripButton.setOnClickListener {
                    if (searchTextFieldEditText.text!!.length > 2 && date1.isNotBlank() && date2.isNotBlank() && daysToStay.isNotBlank()) {
                        Snackbar.make(
                            it,
                            "Trip Saved",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        binding.tripplanner.visibility = View.GONE
                        viewModel.viewModelScope.launch {
                            viewModel.saveTripListToLocalDb(
                                TripPlanModel(
                                    date1,
                                    date2,
                                    searchTextFieldEditText.text.toString(),
                                    daysToStay
                                )
                            )
                            delay(200L)
                            viewModel.getAllProducts()
                            delay(200L)
                            observeData()
                        }
                    } else {
                        Snackbar.make(
                            it,
                            "Please give valid date&name. At least 3 characters.",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun observeData() {
        viewModel.viewModelScope.launch {
                viewModel.uiState.collect {
                    it.isLoading.let {
                        if (!it) {
                        }
                        else {
                        }
                    }
                    Log.d("Test","ViewModel çağrıldı")
                    viewModel.getAllProducts()
                    it.tripItem.let { list ->
                        tripPageAdapter.differ.submitList(list)
                    }
                }
            }
        }

    private fun recyclerViewSetup() {
        tripPageAdapter = TripPageAdapter()
        binding.tripPageRecyclerview.adapter = tripPageAdapter
    }
}

