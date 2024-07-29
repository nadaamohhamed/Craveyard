package com.example.craveyard.recipe.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.craveyard.R
import com.example.craveyard.recipe.model.Meal

class MealsAdapter (
    val mealsList: List<Meal>
) : RecyclerView.Adapter<MealsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.custom_meal_item, parent, false)
        return ViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val meal = mealsList[position]

        holder.getMealName().text = meal.strMeal
        Glide.with(holder.getMealImage())
            .load(meal.strMealThumb)
            .into(holder.getMealImage())

        holder.itemView.setOnClickListener {
            // go to recipe details fragment
        }
    }


    class ViewHolder(
        val meal: View
    ) : RecyclerView.ViewHolder(meal){
        private var mealName: TextView = meal.findViewById(R.id.recipe_name)
        private var mealImage: ImageView = meal.findViewById(R.id.recipe_image)

        fun getMealName(): TextView{
            return mealName
        }

        fun getMealImage(): ImageView {
            return mealImage
        }

    }
}
