package com.example.craveyard.ui.aboutus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.craveyard.R


class AboutUsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about_us, container, false)
        val Devs = arrayOf(
            Dev("Nada Mohammed", R.drawable.female),
            Dev("Beshoy Mamdouh", R.drawable.male),
            Dev("Omar Ramadan", R.drawable.male),
            Dev("Eslam Magdy", R.drawable.male)
        )
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = AboutAdapter(Devs)
        return view
    }
}