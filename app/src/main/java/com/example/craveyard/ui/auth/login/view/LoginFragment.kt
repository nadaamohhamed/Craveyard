package com.example.craveyard.ui.auth.login.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.craveyard.R
import com.example.craveyard.databinding.FragmentLoginBinding
import com.example.craveyard.ui.auth.AuthActivity
import com.example.craveyard.ui.auth.login.events.LoginViewEvents
import com.example.craveyard.ui.auth.login.viewmodel.LoginViewModel
import com.example.craveyard.ui.recipe.RecipeActivity


class LoginFragment : Fragment() {

    var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!
    lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this)[LoginViewModel::class.java]
        initViews()
        observeLiveData()

        // Handle the back press in Fragment
                requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        val builder= AlertDialog.Builder(requireContext())
                        builder.setTitle("Exit")
                        builder.setMessage("Are you sure u want to Back")
                        builder.setPositiveButton("Yes"){_, _->
                            requireActivity().finishAffinity()
                        }
                        builder.setNegativeButton("No"){_,_->

                        }
                        builder.create()
                        builder.show()

                    }
                })
    }


    private fun initViews() {
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }

    private fun observeLiveData() {
        viewModel.events.observe(viewLifecycleOwner) {
            when (it) {

                is LoginViewEvents.navigateToRegister -> {
                    navigateToRegister()
                }

                is LoginViewEvents.navigateToHome -> {
                    navigateToHome()
                }

            }
        }
    }

    private fun navigateToHome() {
        val intent = Intent(requireActivity(), RecipeActivity::class.java)
        // finish the current activity
       // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        if(intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun navigateToRegister() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }
}
