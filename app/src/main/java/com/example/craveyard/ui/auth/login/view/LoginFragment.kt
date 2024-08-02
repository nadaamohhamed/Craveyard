package com.example.craveyard.ui.auth.login.view

import android.os.Bundle
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
    }



    private fun initViews() {
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }

    private fun observeLiveData() {
        viewModel.events.observe(viewLifecycleOwner) {
            when (it) {
                is LoginViewEvents.navigatToRegister -> {
                    navigateToRegister()
                }

                is LoginViewEvents.navigateToHome -> {
                    navigateToHome(it.user)
                }
            }
        }
    }

    private fun navigateToHome(user: User) {
        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment(user)
        findNavController().navigate(action)
    }
    private fun navigateToRegister() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }
}
