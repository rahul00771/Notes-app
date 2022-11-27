package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController       //manages app navigation within nav host

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.fragmentContainerView)

        setupActionBarWithNavController(navController)      //for setting up the action bar in fragment

    }

    override fun onNavigateUp(): Boolean {          //for up(back) button in actionbar
        return navController.navigateUp() || super.onNavigateUp()
    }
}