package com.kenbu.travelapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.kenbu.travelapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
        private lateinit var binding: FragmentHomeBinding
       override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
           binding = FragmentHomeBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
           binding.flightsButton.setOnClickListener {
               Log.v("Test","Test124124")
           }



           return binding.root


    }


}