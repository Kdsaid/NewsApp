package com.example.newapp.presentation.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.newapp.R
import com.example.newapp.common.Constants
import com.example.newapp.common.clearBackStackAndNavigate
import com.example.newapp.data.read
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        getIsToHome()
    }

    private fun getIsToHome() {
        lifecycleScope.launch {
            delay(3000)

            val isHome = requireContext().read(Constants.SELECTED_TO_HOME, false)


            withContext(Dispatchers.Main) {
                if (isHome) {
                    clearBackStackAndNavigate(
                        R.id.mobile_navigation, R.id.navigation_home
                    )
                } else {
                    clearBackStackAndNavigate(
                        R.id.mobile_navigation, R.id.navigation_on_boarding
                    )
                }


            }
        }
    }


}


