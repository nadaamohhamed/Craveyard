package com.example.craveyard.ui.auth

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.craveyard.R


class AuthActivity : AppCompatActivity() {


    lateinit var navController: NavController
    lateinit var  sharedPreferences:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_auth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)

        Log.d("asd", "onCreate: ${sharedPreferences.getBoolean("splashShown",false)}")
    }

    override fun onStart() {
        super.onStart()
        navController=findNavController(R.id.auth_nav_host)

    }

    override fun onDestroy() {
        val editor= sharedPreferences.edit()
        editor.putBoolean("splashShown",false)
        Log.d("asd", "ondestory")
        editor.commit()
        Log.d("asd","${sharedPreferences.getBoolean("splashShown",true)}")
        super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        navController=findNavController(R.id.auth_nav_host)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}