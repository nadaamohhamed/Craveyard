package com.example.craveyard.recipe.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.craveyard.R
import com.example.craveyard.recipe.clickhandler.ClickHandler
import com.example.craveyard.recipe.model.Meal


class SearchAdapter(val data: List<Meal>,var clickHandler: ClickHandler) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    class ViewHolder(itemView: View, data: List<Meal>, clickHandler: ClickHandler) : RecyclerView.ViewHolder(itemView) {
        private val MealImage: ImageView? = null
        private val MealName: TextView? = null

/*        init {
            itemView.setOnClickListener(){
                clickHandler.onMealClick(data[adapterPosition])
            }
        }*/

        fun getMealImage(): ImageView? {
            return MealImage ?: itemView.findViewById(R.id.recipe_image)
        }

        fun getMealName(): TextView? {
            return MealName ?: itemView.findViewById(R.id.recipe_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_search_item, parent, false)
        return ViewHolder(view,data,clickHandler)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = data[position]
        holder.itemView.setOnClickListener(){
            clickHandler.onMealClick(meal)
        }
        Glide.with(holder.getMealImage()!!)
            .load(meal.strMealThumb)
            .into(holder.getMealImage()!!)
        holder.getMealName()?.text = meal.strMeal

    }

    override fun getItemCount(): Int {
        return data.size
    }


}