package com.example.craveyard.recipe.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.craveyard.R
import com.example.craveyard.recipe.model.Meal
import com.example.craveyard.recipe.model.Meals
import com.example.craveyard.recipe.model.Recipe

class SearchAdapter(val data: Meals) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val MealImage: ImageView? = null
        private val MealName: TextView? = null

        fun getMealImage(): ImageView? {
            return MealImage ?: itemView.findViewById(R.id.recipe_image)
        }

        fun getMealName(): TextView? {
            return MealName ?: itemView.findViewById(R.id.recipe_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_search_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = data.meals[position]
        Glide.with(holder.getMealImage()!!)
            .load(meal.strMealThumb)
            .into(holder.getMealImage()!!)
        holder.getMealName()?.text = meal.strMeal

    }

    override fun getItemCount(): Int {
        return data.meals.size
    }


}