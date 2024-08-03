package com.example.craveyard.utils.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.craveyard.R
import com.example.craveyard.data.model.Category
import com.example.craveyard.utils.clickhandler.ClickHandler

class CategoriesAdapter(val data: List<Category>, var clickHandler: ClickHandler) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryImage: ImageView? = null
        private val categoryName: TextView? = null

        fun getCategoryImage(): ImageView? {
            return categoryImage ?: itemView.findViewById(R.id.category_image)
        }

        fun getCategoryName(): TextView? {
            return categoryName ?: itemView.findViewById(R.id.category_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_category_item, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = data[position]
        Glide.with(holder.getCategoryImage()!!)
            .load(category.strCategoryThumb)
            .into(holder.getCategoryImage()!!)
        holder.getCategoryName()?.text = category.strCategory
        holder.itemView.setOnClickListener(){
            clickHandler.onCategoryClick(category)
        }
    }


}