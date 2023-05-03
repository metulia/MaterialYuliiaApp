package com.example.materialyuliiaapp.ui.viewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialyuliiaapp.databinding.ActivityViewPagerBinding

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
    }

    companion object {
        private const val EARTH = 0
        private const val MARS = 1
        private const val WEATHER = 2
    }
}