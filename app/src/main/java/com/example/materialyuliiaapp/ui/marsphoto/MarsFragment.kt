package com.example.materialyuliiaapp.ui.marsphoto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.materialyuliiaapp.R
import com.example.materialyuliiaapp.databinding.FragmentMarsBinding
import com.google.android.material.snackbar.Snackbar

class MarsFragment : Fragment() {

    private var _binding: FragmentMarsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel: MarsFragmentViewModel by lazy {
        ViewModelProvider(this).get(MarsFragmentViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(viewLifecycleOwner) { appState ->
            renderData(appState)
        }
        viewModel.sendRequest()
    }

    private fun renderData(appState: MarsAppState) {
        when (appState) {
            is MarsAppState.Error -> {
                Snackbar.make(
                    binding.marsImageView,
                    appState.error.message.toString(),
                    Snackbar.LENGTH_LONG
                ).show()
            }
            MarsAppState.Loading -> {
                Snackbar.make(binding.marsImageView, "Loading image", Snackbar.LENGTH_LONG).show()
            }
            is MarsAppState.Success -> {
                val url = appState.marsPhotoResponseData.photos.first().imgSrc
                binding.marsImageView.load(url) {
                    lifecycle(this@MarsFragment)
                    error(R.drawable.ic_load_error_vector)
                    placeholder(R.drawable.ic_no_photo_vector)
                    crossfade(true)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance(): MarsFragment {
            return MarsFragment()
        }
    }
}