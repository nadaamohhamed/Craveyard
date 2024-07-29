package com.example.craveyard.ui.recipe.search.view

import APIClient
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.craveyard.R

import com.example.craveyard.utils.clickhandler.ClickHandler
import com.example.craveyard.data.model.Meal
import com.example.craveyard.utils.adapter.SearchAdapter
import com.example.craveyard.ui.recipe.search.repo.SearchRepositoryImplementation
import com.example.craveyard.ui.recipe.search.viewmodel.SearchViewModel
import com.example.craveyard.ui.recipe.search.viewmodel.SearchViewModelFactory

class SearchFragment : Fragment() , ClickHandler {
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter : SearchAdapter

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
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText != null) {
                    viewModel.search(newText)
                }
                return true
            }
        })
        viewModel.recipes.observe(viewLifecycleOwner) {
            if (it!=null){
                meals.clear()
                meals.addAll(it)
            }
            else{
                meals.clear()
            }
            adapter.notifyDataSetChanged()

        }

        adapter = SearchAdapter(meals,this)
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