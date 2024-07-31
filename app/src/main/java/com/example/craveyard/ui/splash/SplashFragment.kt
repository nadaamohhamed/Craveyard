package com.example.craveyard.ui.splash


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.craveyard.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val view=inflater.inflate(R.layout.fragment_splash, container, false)


        lifecycleScope.launch {
           delay(3000)
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment, null, NavOptions.Builder()
                .setPopUpTo(R.id.splashFragment,inclusive = true)
                .build())
        }


        return view
    }


}