package com.example.craveyard.recipe.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.craveyard.R
import com.example.craveyard.recipe.viewmodel.HomeViewModel


class HomeFragment : Fragment() {
    lateinit var viewModel: HomeViewModel

    private fun initializeViewModel() {
        viewModel = HomeViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeViewModel()

//        viewModel.getMyResponse()
//        viewModel.myResponse.observe(this) {
//            val mealList = it.meal
//            val recipesAdapter = MealAdapter(mealList)
//            val rv = findViewById<RecyclerView>(R.id.rv)
//            rv.adapter = recipesAdapter
//            rv.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
//        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }



}