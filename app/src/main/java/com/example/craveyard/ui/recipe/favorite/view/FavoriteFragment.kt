package com.example.craveyard.ui.recipe.favorite.view


import com.example.craveyard.ui.auth.login.repo.UserRepository
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
import com.example.craveyard.ui.recipe.favorite.repo.FavoriteRepositoryImpl
import com.example.craveyard.ui.recipe.favorite.viewmodel.FavoriteViewModel
import com.example.craveyard.ui.recipe.favorite.viewmodel.FavoriteViewModelFactory
import com.example.craveyard.ui.recipe.utils.adapter.MealsAdapter
import com.example.craveyard.ui.recipe.utils.clickhandler.ClickHandler



class FavoriteFragment : Fragment(), ClickHandler {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var adapter : MealsAdapter

    private fun initializeViewModel(){

        val favoriteViewModelFactory = FavoriteViewModelFactory(favoriteRepository = FavoriteRepositoryImpl(userRepository = UserRepository))
        favoriteViewModel = ViewModelProvider(this, favoriteViewModelFactory).get(FavoriteViewModel::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // initialize view model
        initializeViewModel()

        // get user's favorites
        favoriteViewModel.getAllFavorites()

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

        favoriteViewModel.favorites.observe(viewLifecycleOwner) {
            val empty = view.findViewById<TextView>(R.id.emptyText)

            if (it.isNotEmpty()){
                favorites.clear()
                favorites.addAll(it)
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

        adapter = MealsAdapter(favorites, this, favoriteViewModel)
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