package com.example.materialyuliiaapp.ui.viewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.materialyuliiaapp.R
import com.example.materialyuliiaapp.databinding.ActivityViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(this)

        setTabs()
    }

    private fun setTabs() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                EARTH -> {
                    getString(R.string.custom_tab_earth)
                }
                MARS -> {
                    getString(R.string.custom_tab_mars)
                }
                WEATHER -> {
                    getString(R.string.custom_tab_weather)
                }
                else -> {
                    getString(R.string.custom_tab_earth)
                }
            }
            tab.icon = when (position) {
                EARTH -> {
                    ContextCompat.getDrawable(this, R.drawable.ic_earth)
                }
                MARS -> {
                    ContextCompat.getDrawable(this, R.drawable.ic_mars)
                }
                WEATHER -> {
                    ContextCompat.getDrawable(this, R.drawable.ic_weather)
                }
                else -> {
                    ContextCompat.getDrawable(this, R.drawable.ic_earth)
                }
            }
        }.attach()
    }

    companion object {
        private const val EARTH = 0
        private const val MARS = 1
        private const val WEATHER = 2
    }
}