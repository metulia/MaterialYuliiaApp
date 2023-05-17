package com.example.materialyuliiaapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialyuliiaapp.R
import com.example.materialyuliiaapp.databinding.ActivityMainBinding
import com.example.materialyuliiaapp.ui.picture.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setTheme(R.style.MyEarthTheme)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance()).commit()
        }
    }
}