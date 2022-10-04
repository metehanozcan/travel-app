package com.kenbu.travelapp.presentation.dummy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import com.kenbu.travelapp.databinding.FragmentDummyBinding
import com.kenbu.travelapp.domain.model.TravelDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await

class Dummy : Fragment() {
    private lateinit var binding: FragmentDummyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDummyBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        apiCall()
        return binding.root
    }

    private fun apiCall() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val realEstateList: ArrayList<TravelDataModel> =
                    MarsApi.retrofitService.getProperties().await() as ArrayList<TravelDataModel>
                val estateAdapter = RealEstateAdapter(ArrayList(realEstateList))
                Log.d("Test","$realEstateList")
                binding.apply {
                    setVariable(BR.adapter, estateAdapter)
                }
            } catch (e: Exception) {
                Log.d("test", "Test")
            }
        }
    }
}