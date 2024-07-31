package com.example.craveyard.ui.auth.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.craveyard.R
import com.example.craveyard.databinding.FragmentLoginBinding
import com.example.craveyard.ui.auth.AuthActivity

// TODO: Rename parameter arguments, choose names that match

class LoginFragment : Fragment() {

    var _binding : FragmentLoginBinding?=null
    private val binding : FragmentLoginBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.register.setOnClickListener{
            navigateToRegister()
        }
    }
    private fun navigateToRegister() {
      //  if(activity==null) return
        // (activity as AuthActivity).navController.navigate(R.id.action_loginFragment_to_registerFragment)
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)


    }

}