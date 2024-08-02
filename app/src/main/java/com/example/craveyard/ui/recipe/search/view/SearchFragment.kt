package com.example.craveyard.ui.recipe.search.view

import APIClient
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.craveyard.R
import com.example.craveyard.data.model.meals.Meal
import com.example.craveyard.ui.recipe.search.repo.SearchRepositoryImplementation
import com.example.craveyard.ui.recipe.search.viewmodel.SearchViewModel
import com.example.craveyard.ui.recipe.search.viewmodel.SearchViewModelFactory
import com.example.craveyard.ui.recipe.utils.adapter.MealsAdapter
import com.example.craveyard.ui.recipe.utils.clickhandler.ClickHandler


class SearchFragment : Fragment() , ClickHandler {
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter : MealsAdapter

    private var  meals :ArrayList<Meal> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        getViewModel()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val searchView = view.findViewById<SearchView>(R.id.searchView)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.search(query)
                }
                searchView.clearFocus()
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    viewModel.search(newText)
                }
                return true
            }
        })

        val emptyText = view.findViewById<TextView>(R.id.empty_result)
        viewModel.recipes.observe(viewLifecycleOwner) {
            if (it!=null){
                meals.clear()
                meals.addAll(it)
                // remove empty results text
                recyclerView.visibility = View.VISIBLE
                emptyText.visibility = View.GONE
            }
            else{
                meals.clear()
                // show empty results text
                recyclerView.visibility = View.GONE
                emptyText.visibility = View.VISIBLE
            }
            adapter.notifyDataSetChanged()

        }

        adapter = MealsAdapter(meals,this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        return view
    }
    private fun getViewModel() {
        val searchViewModelFactory = SearchViewModelFactory(searchRepository = SearchRepositoryImplementation(remoteDataSource = APIClient))
        viewModel = ViewModelProvider(this, searchViewModelFactory).get(SearchViewModel::class.java)
    }



    override fun onMealClick(meal: Meal) {

        val action=SearchFragmentDirections.actionSearchIconToRecipeDetailFragment(meal)
        findNavController().navigate(action)
    }



}