package com.example.craveyard.ui.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.craveyard.R
import com.example.craveyard.utils.adapter.AboutAdapter
import com.example.craveyard.data.model.Dev


class AboutUsFragment : Fragment() {
    private lateinit var adapter: AboutAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about, container, false)
        val Devs = arrayOf(
            Dev("Nada Mohammed", R.drawable.female),
            Dev("Beshoy Mamdouh", R.drawable.male),
            Dev("Omar Ramadan", R.drawable.male),
            Dev("Eslam Magdy", R.drawable.male)
        )
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView2)
        adapter = AboutAdapter(Devs)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter
        return view
    }
}