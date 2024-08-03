package com.example.craveyard.ui.recipe.favorite.view


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.craveyard.R
import com.example.craveyard.data.model.Category
import com.example.craveyard.data.model.meals.Meal

import com.example.craveyard.ui.recipe.utils.adapter.MealsAdapter
import com.example.craveyard.ui.recipe.utils.clickhandler.ClickHandler



class FavoriteFragment : Fragment(), ClickHandler {

    private lateinit var adapter : MealsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_favorites)
        recyclerView.layoutManager = LinearLayoutManager(context)


        // remove empty results text
//        empty.visibility = View.GONE
//        // show empty results text
//        empty.visibility = View.VISIBLE



        return view
    }

    override fun onMealClick(meal: Meal) {
        val action = FavoriteFragmentDirections.actionFavoriteIconToRecipeDetailFragment(meal)
        findNavController().navigate(action)
    }

    override fun onCategoryClick(category: Category) {
        Log.d("asd","${category.strCategory}")
    }

}