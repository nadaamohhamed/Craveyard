package com.example.craveyard.recipe.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.craveyard.R
import com.example.craveyard.recipe.fragments.FavoriteFragment
import com.example.craveyard.recipe.fragments.HomeFragment
import com.example.craveyard.recipe.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class RecipeActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right,0)
            insets
        }
         // init toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "CraveYard"

        // init bottom nav bar
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_bar)
        bottomNav.setOnItemSelectedListener(this)
        bottomNav.setSelectedItemId(R.id.nav_home);
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                return true
            }
            R.id.action_about -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragmentManager = supportFragmentManager
        when (item.itemId) {
            R.id.nav_home -> {
                fragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
                return true
            }
            R.id.nav_search -> {
                fragmentManager.beginTransaction().replace(R.id.fragment_container, SearchFragment()).commit()
                return true
            }
            R.id.nav_favorites -> {
                fragmentManager.beginTransaction().replace(R.id.fragment_container, FavoriteFragment()).commit()
                return true
            }
        }
        return false
    }


}