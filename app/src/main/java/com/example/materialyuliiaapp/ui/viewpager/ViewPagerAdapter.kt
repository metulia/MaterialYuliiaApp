package com.example.materialyuliiaapp.ui.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(
        fragmentManager,
        FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> fragments[EARTH_FRAGMENT]
            1 -> fragments[MARS_FRAGMENT]
            2 -> fragments[WEATHER_FRAGMENT]
            else -> fragments[EARTH_FRAGMENT]
        }
    }

    private val fragments = arrayOf(EarthFragment(), MarsFragment(), WeatherFragment())

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            EARTH_FRAGMENT -> "EARTH"
            MARS_FRAGMENT -> "MARS"
            WEATHER_FRAGMENT -> "WEATHER"
            else -> {
                "EARTH"
            }
        }
    }

    companion object {
        private const val EARTH_FRAGMENT = 0
        private const val MARS_FRAGMENT = 1
        private const val WEATHER_FRAGMENT = 2
    }
}


