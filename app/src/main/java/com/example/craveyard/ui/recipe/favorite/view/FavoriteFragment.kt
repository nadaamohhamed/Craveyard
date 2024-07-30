package com.example.craveyard.ui.recipe.favorite.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.craveyard.R
import com.example.craveyard.data.model.meal.Meal
import com.example.craveyard.utils.clickhandler.ClickHandler


class FavoriteFragment : Fragment(), ClickHandler {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        // get favorites user list and inject to meals adapter
        val favoriteList = ArrayList<Meal>()
//        val adapter = MealsAdapter(favoriteList, this)

        // initialize recycler view
//        val rv = view.findViewById<RecyclerView>(R.id.rv_favorites)
//        rv.layoutManager = LinearLayoutManager(context)
//        rv.adapter = adapter

        // check if  empty list, show empty text view
        val emptyText = view.findViewById<TextView>(R.id.emptyText)
        emptyText.visibility = if (favoriteList.isEmpty()) View.VISIBLE else View.GONE

        return view
    }

    override fun onMealClick(meal: Meal) {
        val action = FavoriteFragmentDirections.actionFavoriteIconToRecipeDetailFragment(meal)
        findNavController().navigate(action)
    }

}