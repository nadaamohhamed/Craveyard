package com.example.craveyard.utils.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.craveyard.R
import com.example.craveyard.data.model.developer.Dev

class AboutAdapter(val data: Array<Dev>) : RecyclerView.Adapter<AboutAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val DevImage: ImageView? = null
        private val DevName: TextView? = null

        fun getDevImage(): ImageView? {
            return DevImage ?: itemView.findViewById(R.id.dev_image)
        }

        fun getDevName(): TextView? {
            return DevName ?: itemView.findViewById(R.id.dev_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_about_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dev = data[position]
        holder.getDevImage()?.setImageResource(dev.image)
        holder.getDevName()?.text = dev.name
    }

    override fun getItemCount(): Int {
        return data.size
    }
}