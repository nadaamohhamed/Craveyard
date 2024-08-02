package com.example.craveyard.ui.auth.login.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.craveyard.R
import com.example.craveyard.data.model.auth.User
import com.example.craveyard.databinding.FragmentLoginBinding
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
        Log.d("TAG", "onCreateView: ")
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this)[LoginViewModel::class.java]
        initViews()
        Log.d("TAG", "onViewCreated: ")
        observeLiveData()
    }



    private fun initViews() {
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }

    private fun observeLiveData() {
        Log.d("TAG", "observeLiveData: ")
        viewModel.events.observe(viewLifecycleOwner) {
            Log.d("TAG", "observeLiveData: $it")
            when (it) {
                is LoginViewEvents.navigatToRegister -> {
                    navigateToRegister()
                }

                is LoginViewEvents.navigateToHome -> {
                    Log.d("TAG", "observeLiveData: $it")
                    navigateToHome(it.user)
                }

            }
        }
    }

    private fun navigateToHome(user: User) {
        val intent = Intent(requireContext(), RecipeActivity::class.java)
        intent.putExtra("user", user)

        if(intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun navigateToRegister() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }
}
