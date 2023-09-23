package com.example.newapp.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newapp.R
import com.example.newapp.common.snackRetry
import com.example.newapp.common.viewBinding
import com.example.newapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var countriesAdapter: CountriesAdapter
    private val binding by viewBinding<FragmentHomeBinding>()

    //        private val viewModel by viewModels<CountriesViewModel>()
    private val viewModel by viewModels<HomeViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.swipe) {
            setColorSchemeResources(R.color.blue_400)
            isRefreshing = true
            setOnRefreshListener {
                viewModel.loadData()
                lifecycleScope.launch {
                    delay(700)
                    isRefreshing = false
                }
            }
        }

//        }
        countriesAdapter = CountriesAdapter { it }
        with(binding.articlesRv) {
            this.adapter = countriesAdapter
            this.layoutManager = LinearLayoutManager(context)
        }



        lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(lifecycle).collect(::updateUI)

//
        }
    }

    private fun updateUI(uiState: HomeViewModel.UiState) {
        when (uiState) {
            is HomeViewModel.UiState.Error -> {
//                binding.loader.progressBar.hide()

                binding.swipe.isRefreshing = false
                if (countriesAdapter.itemCount > 0) {
                    snackRetry(
                        "No Internet connection! Please try again.", callback = viewModel::loadData
                    )
                } else {
                }
            }

            is HomeViewModel.UiState.Loading -> {
//                binding.loader.progressBar.show()
//                binding.swipe.isRefreshing = true
            }


            is HomeViewModel.UiState.Success -> {
                binding.swipe.isRefreshing = false

//                binding.loader.progressBar.hide()
//                binding.swipeRefreshLayout.isRefreshing = false
//                if (uiState.articlesModel.isNotEmpty()) {
//                    showContent()
//                } else {
//                    showEmptyState("No Cakes data available right now!")
//                }
                countriesAdapter.setData(uiState.articlesModel.articles)
            }

        }
    }


}