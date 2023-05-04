package com.example.materialyuliiaapp.ui.viewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialyuliiaapp.R
import com.example.materialyuliiaapp.databinding.ActivityViewPagerBinding

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        setTabs()

        /*binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                // Nothing to do
            }

            override fun onPageSelected(position: Int) {
               setHighlightedTab(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                // Nothing to do
            }
        })
    }

    private fun setHighlightedTab(position:Int) {

    }*/
    }

    private fun setTabs() {
        with(binding) {
            tabLayout.getTabAt(EARTH)?.setIcon(R.drawable.ic_earth)
            tabLayout.getTabAt(MARS)?.setIcon(R.drawable.ic_mars)
            tabLayout.getTabAt(WEATHER)?.setIcon(R.drawable.ic_weather)
        }
    }

    companion object {
        private const val EARTH = 0
        private const val MARS = 1
        private const val WEATHER = 2
    }
}