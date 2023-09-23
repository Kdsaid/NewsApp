package com.example.newapp.presentation.on_boarding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.newapp.R
import com.example.newapp.common.navigateToPopBackStack
import com.example.newapp.common.viewBinding
import com.example.newapp.data.local.Category
import com.example.newapp.databinding.FragmentOnBoardingBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class OnboardingFragment : Fragment(R.layout.fragment_on_boarding) {
    private val binding by viewBinding<FragmentOnBoardingBinding>()
    private val viewModel by viewModels<CountriesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.submitButton.setOnClickListener {
            navigateToPopBackStack(R.id.navigation_home)
            viewModel.toHome()
        }

        observeUIState()
    }

    private fun observeUIState() = lifecycleScope.launch {

        launch {
            viewModel.countries.flowWithLifecycle(lifecycle).collectLatest { countries ->
                val adapter = CountriesAdapter {
                    viewModel.selectedCountry(it)
                }
                binding.countriesRv.apply {
                    this.adapter = adapter
                }
                adapter.setData(countries)

            }
        }
        launch {
            viewModel.categories.flowWithLifecycle(lifecycle).collect {

                createChips(it, binding.categoriesChips)
            }

        }
        launch {
            viewModel.countFlow.flowWithLifecycle(lifecycle).collect {
                binding.submitButton.isEnabled = it == 3
            }
        }
    }

    private fun createChips(
        categories: List<Category>,
        chipGroup: ChipGroup,
    ) {
        chipGroup.removeAllViews()
        chipGroup.removeAllViewsInLayout()
        categories.forEach {
            val chip = Chip(context)
            chip.isCheckable = true
            chip.isClickable = true
            chip.tag = it.code

            chip.text = it.categoryName
            if (it.isFollowed) {
                chip.isChecked = true
                chip.setChipBackgroundColorResource(R.color.blue_200)
            }
            chip.setOnCheckedChangeListener { buttonView, isChecked ->
                val tag = buttonView.tag as String
                viewModel.updateSubscribedCategory(tag, isChecked)
                buttonView.isChecked = !isChecked
            }
            chipGroup.addView(chip)
        }
    }
}

