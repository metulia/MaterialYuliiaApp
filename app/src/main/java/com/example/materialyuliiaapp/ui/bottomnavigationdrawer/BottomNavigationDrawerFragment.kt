package com.example.materialyuliiaapp.ui.bottomnavigationdrawer

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.materialyuliiaapp.R
import com.example.materialyuliiaapp.databinding.BottomNavigationDrawerBinding
import com.example.materialyuliiaapp.ui.bottomnavigationview.BottomNavigationActivity
import com.example.materialyuliiaapp.ui.viewpager.ViewPagerActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    private var _binding: BottomNavigationDrawerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomNavigationDrawerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.action_bottom_navigation -> {
                    activity?.let {
                        startActivity(Intent(it, BottomNavigationActivity::class.java))
                    }
                    true
                }

                R.id.action_viewpager -> {
                    activity?.let {
                        startActivity(Intent(it, ViewPagerActivity::class.java))
                    }
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}