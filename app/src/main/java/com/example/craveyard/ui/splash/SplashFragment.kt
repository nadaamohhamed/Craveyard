package com.example.craveyard.ui.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
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

    lateinit var  sharedPreferences :SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val splashShown = sharedPreferences.getBoolean("splashShown", false)
        Log.d("asd","$splashShown")

        if (splashShown) {
            navigateNextScreen()
        } else {

            lifecycleScope.launch {
                delay(3000)

                val editor = sharedPreferences.edit()
                editor.putBoolean("splashShown", true)
                editor.commit()
                Log.d("asd","${sharedPreferences.getBoolean("splashShown",false)}")
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
        } else {
            findNavController().navigate(
                R.id.action_splashFragment_to_loginFragment, null, NavOptions.Builder()
                    .setPopUpTo(R.id.splashFragment, inclusive = true)
                    .build()
            )
        }
    }
}