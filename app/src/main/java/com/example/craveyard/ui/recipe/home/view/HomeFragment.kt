package com.example.craveyard.ui.recipe.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.craveyard.R
<<<<<<< Updated upstream:app/src/main/java/com/example/craveyard/recipe/home/view/HomeFragment.kt
import com.example.craveyard.utilities.network.APIClient
import com.example.craveyard.recipe.adapters.MealsAdapter
import com.example.craveyard.recipe.home.repo.HomeRepository
import com.example.craveyard.recipe.home.viewmodel.HomeViewModel
import com.example.craveyard.recipe.home.viewmodel.HomeViewModelFactory
=======
import com.example.craveyard.data.network.APIClient
import com.example.craveyard.utils.adapter.MealsAdapter
import com.example.craveyard.ui.recipe.home.repo.HomeRepository
import com.example.craveyard.ui.recipe.home.viewmodel.HomeViewModel
import com.example.craveyard.ui.recipe.home.viewmodel.HomeViewModelFactory
>>>>>>> Stashed changes:app/src/main/java/com/example/craveyard/ui/home/view/HomeFragment.kt


class HomeFragment : Fragment() {

    lateinit var viewModel: HomeViewModel

    private fun initializeViewModel() {

        val homeViewModelFactory = HomeViewModelFactory(homeRepository = HomeRepository(remoteDataSource = APIClient))
        viewModel = ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // initialize view model
        initializeViewModel()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        // initialize trending random meal card
        viewModel.getRandomMeal()
        val trendingMeal = view.findViewById<CardView>(R.id.trending_meal)
        viewModel.randomMeal.observe(viewLifecycleOwner) {
//            Log.d("TAG", "onCreateView: $it")

            // initialize trending meal card
            val meal = it[0]
            trendingMeal.findViewById<TextView>(R.id.recipe_name).text = meal.strMeal
            val mealImg = trendingMeal.findViewById<ImageView>(R.id.recipe_image)
            Glide.with(mealImg)
                .load(meal.strMealThumb)
                .into(mealImg)

            trendingMeal.setOnClickListener {
                // go to recipe details fragment by nav controller
//                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMealDetailsFragment())
            }
        }

        // initialize recycler view
        viewModel.getAllMeals()
        val rv = view.findViewById<RecyclerView>(R.id.rv_meals)

        viewModel.recipes.observe(viewLifecycleOwner) {
//            Log.d("TAG", "onCreateView: $it")
            val adapter = MealsAdapter(it)
            rv.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            rv.adapter = adapter
        }
        return view
    }



}