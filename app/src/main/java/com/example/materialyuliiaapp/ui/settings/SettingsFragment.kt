package com.example.materialyuliiaapp.ui.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import com.example.materialyuliiaapp.R
import com.example.materialyuliiaapp.databinding.FragmentSettingsBinding
import com.example.materialyuliiaapp.ui.MainActivity

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private lateinit var parentActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentActivity = (context as MainActivity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val context: Context = ContextThemeWrapper(activity, getAppTheme())
        val localInflater = inflater.cloneInContext(context)
        _binding = FragmentSettingsBinding.inflate(localInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.chipEarth.setOnClickListener {
            setAppTheme(EARTH_THEME)
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.settings_fragment_container, this.newInstance()).commit()
        }

        binding.chipMoon.setOnClickListener {
            setAppTheme(MOON_THEME)
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.settings_fragment_container, this.newInstance()).commit()
        }
        binding.chipMars.setOnClickListener {
            setAppTheme(MARS_THEME)
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.settings_fragment_container, this.newInstance()).commit()
        }
    }

    private fun getAppTheme(): Int {
        return codeThemeToThemeId(getCodeTheme());
    }

    private fun getCodeTheme(): Int {
        val sharedPref = (requireActivity() as MainActivity).getSharedPreferences(
            SETTINGS_SHARED_PREFERENCE, AppCompatActivity.MODE_PRIVATE
        )
        return sharedPref.getInt(APP_THEME, MARS_THEME)
    }

    private fun setAppTheme(codeTheme: Int) {
        val sharedPref = (requireActivity() as MainActivity).getSharedPreferences(
            SETTINGS_SHARED_PREFERENCE, AppCompatActivity.MODE_PRIVATE
        )
        val editor = sharedPref.edit();
        editor.putInt(APP_THEME, codeTheme).apply()
    }

    private fun codeThemeToThemeId(codeTheme: Int): Int {
        return when (codeTheme) {
            EARTH_THEME -> {
                R.style.MyEarthTheme
            }
            MOON_THEME -> {
                R.style.MyMoonTheme
            }
            MARS_THEME -> {
                R.style.MyMarsTheme
            }
            else -> {
                R.style.Theme_MaterialYuliiaApp
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val SETTINGS_SHARED_PREFERENCE = "Settings"
        const val APP_THEME = "App_theme"
        const val EARTH_THEME = 0
        const val MOON_THEME = 1
        const val MARS_THEME = 2
    }

    fun newInstance(): SettingsFragment {
        return SettingsFragment()
    }

}