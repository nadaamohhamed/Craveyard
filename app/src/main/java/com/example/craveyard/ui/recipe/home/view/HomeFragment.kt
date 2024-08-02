package com.example.craveyard.ui.recipe.home.view

import APIClient
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.craveyard.R
import com.example.craveyard.data.model.Category
import com.example.craveyard.data.model.Meal
import com.example.craveyard.ui.recipe.home.repo.HomeRepository
import com.example.craveyard.ui.recipe.home.viewmodel.HomeViewModel
import com.example.craveyard.ui.recipe.home.viewmodel.HomeViewModelFactory
import com.example.craveyard.utils.adapter.CategoriesAdapter
import com.example.craveyard.utils.adapter.MealsAdapter
import com.example.craveyard.utils.clickhandler.ClickHandler


class HomeFragment : Fragment(), ClickHandler{

    lateinit var viewModel: HomeViewModel

    private fun initializeViewModel() {

        val homeViewModelFactory = HomeViewModelFactory(homeRepository = HomeRepository(remoteDataSource = APIClient))
        viewModel = ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // initialize view model
        initializeViewModel()

        // get data
        viewModel.getAllMeals()
        viewModel.getRandomMeal()
        viewModel.getCategories()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        // initialize views
        initializeTrendingMealView(view)
        initializeAllMealsView(view)
        initializeCategoriesView(view)


        return view
    }


    private fun initializeAllMealsView(view: View) {
        // initialize meals recycler view
        val rv = view.findViewById<RecyclerView>(R.id.rv_meals)

        viewModel.recipes.observe(viewLifecycleOwner) {
            val adapter = MealsAdapter(it, this)
            rv.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            rv.adapter = adapter
        }
    }

    private fun initializeTrendingMealView(view: View) {
        // initialize trending random meal card
        val trendingMeal = view.findViewById<CardView>(R.id.trending_meal)

        viewModel.randomMeal.observe(viewLifecycleOwner) {
            // initialize trending meal card
            val meal = it[0]
            trendingMeal.findViewById<TextView>(R.id.recipe_name).text = meal.strMeal
            val mealImg = trendingMeal.findViewById<ImageView>(R.id.recipe_image)

            // load image
            Glide.with(mealImg)
                .load(meal.strMealThumb)
                .into(mealImg)
            // set on click listener
            trendingMeal.setOnClickListener {
                // go to recipe details fragment by nav controller
                findNavController().navigate(HomeFragmentDirections.actionHomeIconToRecipeDetailFragment(meal))
            }
        }
    }

    private fun initializeCategoriesView(view: View) {
        // initialize categories recycler view
        val rv = view.findViewById<RecyclerView>(R.id.rv_categories)

        viewModel.categories.observe(viewLifecycleOwner) {
            val adapter = CategoriesAdapter(it, this)
            rv.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            rv.adapter = adapter
        }
    }

    override fun onMealClick(meal: Meal) {
        val action = HomeFragmentDirections.actionHomeIconToRecipeDetailFragment(meal)
        findNavController().navigate(action)
    }

    override fun onCategoryClick(category: Category) {
        val action = HomeFragmentDirections.actionHomeIconToCategoryFragment(category)
        findNavController().navigate(action)
    }


}