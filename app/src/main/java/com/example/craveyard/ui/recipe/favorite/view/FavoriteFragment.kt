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
import com.example.craveyard.data.model.entity.FavMeal
import com.example.craveyard.data.model.localdata.LocalDs
import com.example.craveyard.data.model.meals.Meal
import com.example.craveyard.ui.recipe.favorite.repo.FavRepo
import com.example.craveyard.ui.recipe.favorite.viewmodel.FavViewModel
import com.example.craveyard.ui.recipe.favorite.viewmodel.FavViewModelFactory

import com.example.craveyard.ui.recipe.utils.adapter.MealsAdapter
import com.example.craveyard.ui.recipe.utils.clickhandler.ClickHandler
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class FavoriteFragment : Fragment(), ClickHandler {

    private lateinit var favViewModel: FavViewModel
    private lateinit var adapter : MealsAdapter

    private fun initializeViewModel(){
        val localDs=LocalDs(requireContext())
        val favViewModelFactory = FavViewModelFactory(FavRepo(localDs))
        favViewModel = ViewModelProvider(this, favViewModelFactory).get(FavViewModel::class.java)

    }
    private fun getfavorites() {

        favViewModel.getFavMeals(Firebase.auth.currentUser!!.email!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // initialize view model
        initializeViewModel()
        // get user's favorites
        getfavorites()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_favorites)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val favorites : MutableList<Meal> = mutableListOf()

        favViewModel.getFavMeals(Firebase.auth.currentUser!!.email!!)

        favViewModel.favMeals.observe(viewLifecycleOwner) {
            val empty = view.findViewById<TextView>(R.id.emptyText)

            if (it.isNotEmpty()){
                favorites.clear()
                for (mymeal in it){
                    val meal =Meal(idMeal = mymeal.mealId,
                        strMeal = mymeal.strMeal,
                        strArea = mymeal.strArea,
                        strCategory = mymeal.strCategory,
                        strInstructions = mymeal.strInstructions,
                        strYoutube = mymeal.strYoutube,
                        strMealThumb = mymeal.MealThumb
                    )
                    favorites.add(meal)
                }

                // remove empty results text
                empty.visibility = View.GONE

            }
            else{
                favorites.clear()
                // show empty results text
                empty.visibility = View.VISIBLE
            }

            adapter.notifyDataSetChanged()

        }

        adapter = MealsAdapter(favorites, this, favViewModel)
        recyclerView.adapter = adapter

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