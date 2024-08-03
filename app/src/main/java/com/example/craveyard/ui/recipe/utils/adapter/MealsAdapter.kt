package com.example.craveyard.ui.recipe.utils.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.craveyard.R
import com.example.craveyard.data.model.meals.Meal
import com.example.craveyard.ui.recipe.utils.clickhandler.ClickHandler


class MealsAdapter(
    private val mealsList: MutableList<Meal>,
    private val clickHandler: ClickHandler,
) : RecyclerView.Adapter<MealsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.custom_meal_item, parent, false)
        return ViewHolder(layout)
    }

    override fun getItemCount(): Int = mealsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = mealsList[position]

        holder.getMealName().text = meal.strMeal
        Glide.with(holder.getMealImage()).load(meal.strMealThumb).into(holder.getMealImage())

//        val isFavorite = viewModel.isFavorite(meal)
//
//        holder.getFavoriteBtn().setImageResource(
//            if (isFavorite) {
//                R.drawable.ic_favorite
//            }
//            else {
//                R.drawable.ic_favorite_border
//            }
//        )
//
//        // set favorite button click listener
//        holder.getFavoriteBtn().setOnClickListener {
//            if (isFavorite) {
//                removeMealFromFavorites(meal, holder)
//            } else {
//                addMealToFavorites(meal, holder)
//            }
//
//            //notifyItemChanged(position)
//        }

        // set meal card click listener
        holder.meal.setOnClickListener {
            clickHandler.onMealClick(meal)
        }
    }

    private fun addMealToFavorites(meal: Meal, holder: ViewHolder) {
        val context = holder.getFavoriteBtn().context

        //viewModel.addFavorite(meal)
        holder.getFavoriteBtn().setImageResource(R.drawable.ic_favorite)
        Toast.makeText(context, "Added to your favorites!", Toast.LENGTH_SHORT).show()
    }

    private fun removeMealFromFavorites(meal: Meal, holder: ViewHolder) {
        val context = holder.getFavoriteBtn().context

        AlertDialog.Builder(context)
            .setTitle("Remove Favorite")
            .setMessage("Are you sure you want to remove this meal from favorites?")
            .setPositiveButton("Yes") { _, _ ->
                //viewModel.removeFavorite(meal)
                holder.getFavoriteBtn().setImageResource(R.drawable.ic_favorite_border)
                Toast.makeText(context, "Removed from your favorites!", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    class ViewHolder(val meal: View) : RecyclerView.ViewHolder(meal) {
        private var mealName: TextView = meal.findViewById(R.id.recipe_name)
        private var mealImage: ImageView = meal.findViewById(R.id.recipe_image)
        private val favoriteBtn: ImageButton = meal.findViewById(R.id.favorite_btn)

        fun getMealName(): TextView = mealName
        fun getMealImage(): ImageView = mealImage
        fun getFavoriteBtn(): ImageButton = favoriteBtn
    }
}