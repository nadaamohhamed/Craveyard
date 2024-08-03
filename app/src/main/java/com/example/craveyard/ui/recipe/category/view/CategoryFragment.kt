package com.example.craveyard.ui.recipe.category.view

import APIClient
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.craveyard.R
import com.example.craveyard.data.model.Category
import com.example.craveyard.data.model.meals.Meal
import com.example.craveyard.ui.recipe.category.repo.CategoryRepositoryImpl
import com.example.craveyard.ui.recipe.category.viewmodel.CategoryViewModel
import com.example.craveyard.ui.recipe.category.viewmodel.CategoryViewModelFactory
import com.example.craveyard.ui.recipe.utils.adapter.MealsAdapter
import com.example.craveyard.ui.recipe.utils.clickhandler.ClickHandler


class CategoryFragment : Fragment(), ClickHandler {

    private lateinit var viewModel: CategoryViewModel
    private lateinit var adapter : MealsAdapter

    private val args : CategoryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_category, container, false)
        getViewModel()

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_category)
        val category = args.Category
        viewModel.getMealsByCategory(category.strCategory)
        viewModel.meals.observe(viewLifecycleOwner){
            if (it!=null){
                adapter = MealsAdapter(mealsList = it, clickHandler = this )
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = adapter
            }
        }



        return view
    }
    private fun getViewModel() {
        val categoryViewModelFactory = CategoryViewModelFactory(categoryRepository = CategoryRepositoryImpl(remoteDataSource = APIClient))
        viewModel = ViewModelProvider(this, categoryViewModelFactory).get(CategoryViewModel::class.java)
    }

    override fun onMealClick(meal: Meal) {
        Log.d("asd","${meal.strMeal}")
        Log.d("asd","${meal.strCategory}")
        Log.d("asd","${meal.idMeal}")
        Log.d("asd","${meal.strArea}")
        Log.d("asd","${meal.strYoutube}")
        Log.d("asd","${meal.strMeal}")

        val action = CategoryFragmentDirections.actionCategoryFragmentToRecipeDetailFragment(meal)
        Log.d("asd","category")
        findNavController().navigate(action)
    }

    override fun onCategoryClick(category: Category) {
        TODO("Not yet implemented")
    }
}