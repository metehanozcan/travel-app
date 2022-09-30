package com.kenbu.travelapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.kenbu.travelapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding.apply {
            bottomNavigationView.setOnApplyWindowInsetsListener(null)
            bottomNavigationView.setPadding(12,12,12,12)
            val navHostFragment = supportFragmentManager
                .findFragmentById(fragmentContainerView.id) as NavHostFragment
            NavigationUI.setupWithNavController(
                binding.bottomNavigationView,
                navHostFragment.navController
            )
        }

    }
}