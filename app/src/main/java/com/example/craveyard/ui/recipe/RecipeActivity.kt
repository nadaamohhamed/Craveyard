package com.example.craveyard.ui.recipe

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.craveyard.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class RecipeActivity : AppCompatActivity(){

    private lateinit var bottomNavigationView : BottomNavigationView
    private lateinit var navController : NavController
    private lateinit var  toolbar:Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        // initialize toolbar
         toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "CraveYard"
    }

    override fun onStart() {
        super.onStart()

        // initialize bottom nav
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        navController = findNavController(R.id.recipe_nav_host)
        bottomNavigationView.setupWithNavController(navController)


        navController.addOnDestinationChangedListener(){_,destination,_ ->
            if(destination.id == R.id.recipeDetailFragment || destination.id==R.id.aboutUsFragment) {

                bottomNavigationView.visibility = View.GONE
                toolbar.visibility=View.GONE

            } else {

                bottomNavigationView.visibility = View.VISIBLE
                toolbar.visibility=View.VISIBLE
            }


        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }



}