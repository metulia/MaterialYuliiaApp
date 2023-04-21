package com.example.materialyuliiaapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.materialyuliiaapp.databinding.FragmentPictureOfTheDayBinding
import com.google.android.material.snackbar.Snackbar

class PictureOfTheDayFragment : Fragment() {

    private var _binding: FragmentPictureOfTheDayBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root

    }

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(viewLifecycleOwner) { appState ->
            renderData(appState)
        }
        viewModel.sendRequest()

        binding.chipToday.setOnClickListener {
            viewModel.sendRequest()
        }

        binding.chipYesterday.setOnClickListener {
            viewModel.sendRequestByDateYesterday()
        }

        binding.chipTheDayBeforeYesterday.setOnClickListener {
            viewModel.sendRequestByDateBeforeYesterday()
        }

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }

    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                Snackbar.make(
                    binding.imageView,
                    appState.error.message.toString(),
                    Snackbar.LENGTH_LONG
                ).show()
            }
            AppState.Loading -> {
                Snackbar.make(binding.imageView, "Loading image", Snackbar.LENGTH_LONG).show()
            }
            is AppState.Success -> {
                binding.imageView.load(appState.pictureOfTheDayResponseData.url)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): PictureOfTheDayFragment {
            return PictureOfTheDayFragment()
        }
    }
}