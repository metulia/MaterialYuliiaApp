package com.example.materialyuliiaapp.ui.picture

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.SpannedString
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import coil.load
import com.example.materialyuliiaapp.R
import com.example.materialyuliiaapp.databinding.FragmentPictureOfTheDayStartBinding
import com.example.materialyuliiaapp.ui.MainActivity
import com.example.materialyuliiaapp.ui.bottomnavigationdrawer.BottomNavigationDrawerFragment
import com.example.materialyuliiaapp.ui.bottomnavigationview.BottomNavigationActivity
import com.example.materialyuliiaapp.ui.recycler.RecyclerActivity
import com.example.materialyuliiaapp.ui.settings.SettingsFragment
import com.example.materialyuliiaapp.ui.viewpager.ViewPagerActivity
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar

class PictureOfTheDayFragment : Fragment() {

    private var _binding: FragmentPictureOfTheDayStartBinding? = null
    private val binding get() = _binding!!

    private var isExpanded = false

    private var isMain = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureOfTheDayStartBinding.inflate(inflater, container, false)
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

        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
        binding.bottomAppBar.navigationIcon =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_hamburger)

        (requireActivity() as MainActivity).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_favourite -> {
                        activity?.let {
                            startActivity(Intent(it, ViewPagerActivity::class.java))
                        }
                        true
                    }
                    R.id.action_settings -> {
                        (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                            .hide(this@PictureOfTheDayFragment)
                            .add(R.id.container, SettingsFragment().newInstance())
                            .addToBackStack("")
                            .commit()
                        true
                    }
                    R.id.action_bottom_navigation -> {
                        activity?.let {
                            startActivity(Intent(it, BottomNavigationActivity::class.java))
                        }
                        true
                    }
                    R.id.action_recycler -> {
                        activity?.let {
                            startActivity(Intent(it, RecyclerActivity::class.java))
                        }
                        true
                    }
                    android.R.id.home -> {
                        activity?.let {
                            BottomNavigationDrawerFragment().show(it.supportFragmentManager, "tag")
                        }
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding.imageView.setOnClickListener {

            isExpanded = !isExpanded

            val params = (it as ImageView).layoutParams

            TransitionManager.beginDelayedTransition(
                binding.root,
                TransitionSet().addTransition(ChangeBounds()).addTransition(ChangeImageTransform())
            )
            if (isExpanded) {
                params.height = CoordinatorLayout.LayoutParams.MATCH_PARENT
                it.scaleType = ImageView.ScaleType.CENTER_CROP
            } else {
                params.height = CoordinatorLayout.LayoutParams.WRAP_CONTENT
                binding.imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
            }
            it.layoutParams = params
        }

        val bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet.bottomSheetContainer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_DRAGGING -> {}
                    BottomSheetBehavior.STATE_COLLAPSED -> {}
                    BottomSheetBehavior.STATE_EXPANDED -> {}
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {}
                    BottomSheetBehavior.STATE_HIDDEN -> {}
                    BottomSheetBehavior.STATE_SETTLING -> {}
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })

        binding.exFab.setOnClickListener {
            if (isMain) {
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_navigation_drawer_other_screen)
            } else {
                binding.bottomAppBar.navigationIcon =
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_hamburger)
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.bottomAppBar.replaceMenu(R.menu.menu_main)
            }
            isMain = !isMain
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
                with(binding) {
                    imageView.load(appState.pictureOfTheDayResponseData.url) {
                        lifecycle(this@PictureOfTheDayFragment)
                        error(R.drawable.ic_load_error_vector)
                        placeholder(R.drawable.ic_no_photo_vector)
                        crossfade(true)
                    }

                    if (appState.pictureOfTheDayResponseData.explanation.isEmpty()) {
                        Snackbar.make(
                            binding.explanationTextView,
                            "Explanation is empty",
                            Snackbar.LENGTH_LONG
                        ).show()
                    } else {
                        var spannableStringBuilder =
                            SpannableStringBuilder(appState.pictureOfTheDayResponseData.explanation)

                        explanationTextView.setText(
                            spannableStringBuilder,
                            TextView.BufferType.EDITABLE
                        )

                        spannableStringBuilder = explanationTextView.text as SpannableStringBuilder

                        spannableStringBuilder.setSpan(
                            RelativeSizeSpan(2f),
                            0,
                            spannableStringBuilder.length,
                            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                        )
                    }

                    bottomSheet.bottomSheetExplanationTitle.text =
                        appState.pictureOfTheDayResponseData.title

                    bottomSheet.bottomSheetExplanation.text =
                        appState.pictureOfTheDayResponseData.explanation
                }
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