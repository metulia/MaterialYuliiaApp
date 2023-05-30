package com.example.materialyuliiaapp.ui.bottomnavigationview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialyuliiaapp.R
import com.example.materialyuliiaapp.databinding.ActivityBottomNavigationBinding
import com.example.materialyuliiaapp.ui.marsphoto.MarsFragment
import com.example.materialyuliiaapp.ui.viewpager.EarthFragment
import com.example.materialyuliiaapp.ui.viewpager.WeatherFragment

class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_earth -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.bottom_navigation_container, EarthFragment())
                        .commit()
                    true
                }
                R.id.bottom_view_mars -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.bottom_navigation_container, MarsFragment())
                        .commit()
                    true
                }
                R.id.bottom_view_weather -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.bottom_navigation_container, WeatherFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
        binding.bottomNavigationView.selectedItemId = R.id.bottom_view_earth

        val badge = binding.bottomNavigationView.getOrCreateBadge(R.id.bottom_view_earth)
        badge.number = 222
    }
}