
package com.example.craveyard.authentication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chat.database.User
import com.example.craveyard.R
import com.example.craveyard.databinding.FragmentRegisterBinding
import com.example.craveyard.ui.auth.register.view.RegisterFragmentDirections
import com.example.craveyard.ui.auth.register.viewmodel.RegisterViewModel

class RegisterFragment : Fragment() {


    val viewModel: RegisterViewModel
    lateinit var binding :  FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)

    }
    init {
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.events.observe(viewLifecycleOwner){
            when(it){
                is RegisterViewEvents.navigatToHome->{
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