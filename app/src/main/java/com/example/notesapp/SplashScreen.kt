package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()        //to hide the toolbar or action bar

         Handler(Looper.getMainLooper()).postDelayed(Runnable {                      //used for delaying the code implementation
            startActivity(Intent(this, MainActivity::class.java))       //inside runnable, code that will run after the delay
        }, 2000)

    }
}