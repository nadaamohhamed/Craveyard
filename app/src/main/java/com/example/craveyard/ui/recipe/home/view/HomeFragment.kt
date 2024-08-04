package com.example.craveyard.ui.recipe.home.view

import APIClient
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.craveyard.R
import com.example.craveyard.data.model.meals.Meal
import com.example.craveyard.ui.recipe.home.repo.HomeRepositoryImpl
import com.example.craveyard.data.model.Category
import com.example.craveyard.data.model.localdata.LocalDs
import com.example.craveyard.ui.recipe.home.viewmodel.HomeViewModel
import com.example.craveyard.ui.recipe.home.viewmodel.HomeViewModelFactory
import com.example.craveyard.ui.recipe.utils.adapter.MealsAdapter
import com.example.craveyard.ui.recipe.utils.clickhandler.ClickHandler
import com.example.craveyard.ui.recipe.favorite.repo.FavRepo
import com.example.craveyard.ui.recipe.favorite.viewmodel.FavViewModel
import com.example.craveyard.ui.recipe.favorite.viewmodel.FavViewModelFactory
import com.example.craveyard.ui.recipe.category.adapter.CategoriesAdapter


class HomeFragment : Fragment(), ClickHandler {

    lateinit var homeViewModel: HomeViewModel
    lateinit var favViewModel: FavViewModel

    private fun initializeViewModel() {

        val homeViewModelFactory = HomeViewModelFactory(homeRepository = HomeRepositoryImpl(remoteDataSource = APIClient))
        homeViewModel = ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)

        val localDs= LocalDs(requireContext())
        val favViewModelFactory = FavViewModelFactory(FavRepo(localDs))
        favViewModel = ViewModelProvider(this, favViewModelFactory).get(FavViewModel::class.java)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // initialize view model
        initializeViewModel()

        // get data
        homeViewModel.getAllMeals()
        homeViewModel.getRandomMeal()
        homeViewModel.getCategories()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Handle the back press in Fragment
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val builder= AlertDialog.Builder(requireContext())
                builder.setTitle("Exit")
                builder.setMessage("Are you sure u want to Back")
                builder.setPositiveButton("Yes"){_, _->
                    requireActivity().finishAffinity()
                }
                builder.setNegativeButton("No"){_,_->

                }
                builder.create()
                builder.show()

            }
        })
    }

    private fun initializeCategoriesView(view: View) {
        // initialize categories recycler view
        val rv = view.findViewById<RecyclerView>(R.id.rv_categories)

        homeViewModel.categories.observe(viewLifecycleOwner) {
            val adapter = CategoriesAdapter(it, this)
            rv.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            rv.adapter = adapter
        }
    }

    private fun initializeAllMealsView(view: View) {
        // initialize meals recycler view
        val rv = view.findViewById<RecyclerView>(R.id.rv_meals)

        homeViewModel.recipes.observe(viewLifecycleOwner) {
            val adapter = MealsAdapter(it.toMutableList(), this, favViewModel)
            rv.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            rv.adapter = adapter
        }
    }

    private fun initializeTrendingMealView(view: View) {
        // initialize trending random meal
        val trendingMeal = view.findViewById<RecyclerView>(R.id.trending_meal)

        homeViewModel.randomMeal.observe(viewLifecycleOwner) {
            val adapter = MealsAdapter(it.toMutableList(), this, favViewModel)
            trendingMeal.layoutManager = LinearLayoutManager(context)
            trendingMeal.adapter = adapter
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