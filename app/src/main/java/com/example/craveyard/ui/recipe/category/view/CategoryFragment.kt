package com.example.craveyard.ui.recipe.category

import APIClient
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.craveyard.R
import com.example.craveyard.data.model.Category
import com.example.craveyard.data.model.localdata.LocalDs
import com.example.craveyard.data.model.meals.Meal
import com.example.craveyard.ui.recipe.favorite.repo.FavRepo
import com.example.craveyard.ui.recipe.favorite.viewmodel.FavViewModel
import com.example.craveyard.ui.recipe.favorite.viewmodel.FavViewModelFactory
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
            // show progress bar
            val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)
            progressBar.setVisibility(View.VISIBLE);

            if (it!=null){

                val localDs= LocalDs(requireContext())
                val favViewModelFactory = FavViewModelFactory(FavRepo(localDs))

                var favViewModel = ViewModelProvider(this, favViewModelFactory).get(FavViewModel::class.java)

                adapter = MealsAdapter(mealsList = it, clickHandler = this,favViewModel )
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = adapter

                // hide progress bar
                progressBar.setVisibility(View.GONE);
            }
        }



        return view
    }
    private fun getViewModel() {
        val categoryViewModelFactory = CategoryViewModelFactory(categoryRepository = CategoryRepositoryImpl(remoteDataSource = APIClient))
        viewModel = ViewModelProvider(this, categoryViewModelFactory).get(CategoryViewModel::class.java)
    }

    override fun onMealClick(meal: Meal) {

        val action = CategoryFragmentDirections.actionCategoryFragmentToRecipeDetailFragment(meal)
        Log.d("asd","category")
        findNavController().navigate(action)
    }

    override fun onCategoryClick(category: Category) {
        TODO("Not yet implemented")
    }
}