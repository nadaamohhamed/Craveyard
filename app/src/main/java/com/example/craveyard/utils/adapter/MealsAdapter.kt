package com.example.craveyard.utils.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.craveyard.R
import com.example.craveyard.data.model.meal.Meal
import com.example.craveyard.utils.clickhandler.ClickHandler

class MealsAdapter (
    val mealsList: List<Meal>,
    val clickHandler: ClickHandler
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

        // TODO:check if favorite: ic_favorite else ic_favorite_border
        holder.getFavoriteBtn().setImageResource(R.drawable.ic_favorite)

        holder.meal.setOnClickListener(){
            clickHandler.onMealClick(meal)
        }

    }


    class ViewHolder(
        val meal: View
    ) : RecyclerView.ViewHolder(meal){
        private var mealName: TextView = meal.findViewById(R.id.recipe_name)
        private var mealImage: ImageView = meal.findViewById(R.id.recipe_image)
        private val favoriteBtn: ImageButton = meal.findViewById(R.id.favorite_btn)


        fun getMealName(): TextView{
            return mealName
        }

        fun getMealImage(): ImageView {
            return mealImage
        }

        fun getFavoriteBtn(): ImageButton {
            return favoriteBtn
        }

    }
}
