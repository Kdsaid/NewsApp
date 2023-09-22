package com.example.newapp.presentation.on_boarding

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.newapp.R
import com.example.newapp.data.local.Category
import com.example.newapp.databinding.FragmentOnBoardingBinding
import com.example.newapp.presentation.common.viewBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class OnboardingFragment : Fragment(R.layout.fragment_on_boarding) {
    private val binding by viewBinding<FragmentOnBoardingBinding>()
    private val viewModel by viewModels<CountriesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUIStateCountries()
        observeUIStateCategories()
    }

    private fun observeUIStateCountries() = lifecycleScope.launch {
        viewModel.countries.flowWithLifecycle(lifecycle).collectLatest { countries ->
                val adapter = CountriesAdapter {

                }
                binding.countriesRv.apply {
                    this.adapter = adapter
                }
                adapter.setData(countries)

        }
    }
    private fun observeUIStateCategories() = lifecycleScope.launch {
        viewModel.categories.flowWithLifecycle(lifecycle).collectLatest { categories ->
            withContext(Dispatchers.Main) {
                createChips(categories, binding.categoriesChips)
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
//                viewModel.updateSubscribedCategory(value = tag,isChecked = isChecked)
                buttonView.isChecked = !isChecked
            }
            chipGroup.addView(chip)
        }
    }

}