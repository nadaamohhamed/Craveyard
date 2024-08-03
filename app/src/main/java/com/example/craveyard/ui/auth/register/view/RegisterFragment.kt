
package com.example.craveyard.ui.auth.register.view

import com.example.craveyard.ui.auth.login.repo.UserRepository
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.craveyard.databinding.FragmentRegisterBinding
import com.example.craveyard.ui.auth.register.events.RegisterViewEvents
import com.example.craveyard.ui.auth.register.viewmodel.RegisterViewModel
import com.example.craveyard.ui.recipe.RecipeActivity

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
                is RegisterViewEvents.navigateToHome ->{
                    UserRepository.setUser(it.user)
                    navigateToHome()
                }
            }
        }
    }

    private fun navigateToHome() {
        val intent = Intent(requireActivity(), RecipeActivity::class.java)
        // finish the current activity
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        if(intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun initView() {
        binding.vm=viewModel
        binding.lifecycleOwner=this
    }

}