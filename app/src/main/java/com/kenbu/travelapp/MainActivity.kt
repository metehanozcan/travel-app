package com.kenbu.travelapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kenbu.travelapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)



        binding.bottomNavigationView.setOnApplyWindowInsetsListener(null)
        binding.bottomNavigationView.setPadding(12, 12, 12, 12)
        val navHostFragment = supportFragmentManager
            .findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        NavigationUI.setupWithNavController(
            binding.bottomNavigationView,
            navHostFragment.navController
        )
            /*
            Bottom Nav Bar'ı belli fragmentlerde saklamak için kullandığımız logic
             */
        val navController = navHostFragment.navController
        navController!!.addOnDestinationChangedListener { controller: NavController?, destination: NavDestination, bundle: Bundle? ->
                if (destination.id == R.id.detailFragment || destination.id == R.id.searchEngineFragment )
                {
                    Log.d("test","Destinations Called")
                    binding.bottomNavigationView.visibility = View.GONE
                }
                else
                    binding.bottomNavigationView.visibility = View.VISIBLE
        }
    }
}