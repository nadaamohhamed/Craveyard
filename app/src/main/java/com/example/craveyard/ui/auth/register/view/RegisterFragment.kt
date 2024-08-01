
package com.example.craveyard.ui.auth.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.craveyard.data.model.User
import com.example.craveyard.databinding.FragmentRegisterBinding
import com.example.craveyard.ui.auth.register.events.RegisterViewEvents
import com.example.craveyard.ui.auth.register.viewmodel.RegisterViewModel

class RegisterFragment : Fragment() {


    lateinit var viewModel: RegisterViewModel

    var _binding : FragmentRegisterBinding?=null
    private val binding : FragmentRegisterBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        initView()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.events.observe(viewLifecycleOwner){
            when(it){
                is RegisterViewEvents.navigatToHome ->{
                    navigatetoHome(it.user)
                }
            }
        }
    }

    private fun navigatetoHome(user: User) {
        val action = RegisterFragmentDirections.actionRegisterFragmentToHomeFragment(user)
        findNavController().navigate(action)
    }

    private fun initView() {
        binding.vm=viewModel
        binding.lifecycleOwner=this
    }

}