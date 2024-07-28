package com.example.craveyard.recipe.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.craveyard.R
import com.example.craveyard.recipe.model.Recipe

class SearchAdapter(val data: List<Recipe>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_search_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}