package com.example.craveyard.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.craveyard.R
import com.example.craveyard.ui.auth.login.viewmodel.LoginViewModel
import com.example.craveyard.ui.recipe.RecipeActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val splashShown = sharedPreferences.getBoolean("splashShown", false)


        if (splashShown) {
            navigateNextScreen()
        } else {

            lifecycleScope.launch {
                delay(3000)

                val editor = sharedPreferences.edit()
                editor.putBoolean("splashShown", true)
                editor.apply()

                navigateNextScreen()
            }
        }
    }

    private fun navigateNextScreen() {
        val viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        val loggedIn = viewModel.checkUserLogin()

        if (loggedIn) {
            val intent = Intent(requireContext(), RecipeActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        } else {
            findNavController().navigate(
                R.id.action_splashFragment_to_loginFragment, null, NavOptions.Builder()
                    .setPopUpTo(R.id.splashFragment, inclusive = true)
                    .build()
            )
        }
    }
}