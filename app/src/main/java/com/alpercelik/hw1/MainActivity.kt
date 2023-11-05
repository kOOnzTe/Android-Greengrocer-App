package com.alpercelik.hw1

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.alpercelik.hw1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        supportActionBar?.hide() // Hiding title bar

        setContentView(view)

        @Suppress("DEPRECATION") // for using deprecated codes without any highlights or errors
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN) // Hiding the status bar
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) // Locking the orientation to Portrait

        // ANIMATIONS
        val colorBlinkAnimation = AnimationUtils.loadAnimation(this, R.anim.color_blink_animation) // Load the color blink animation from the XML resource
        binding.tvAppName.startAnimation(colorBlinkAnimation)


        // EVENT HANDLERS
        binding.btnShopping.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        binding.btnExit.setOnClickListener {
            finishAffinity() // Close all activities and exit the app
            //finish()
        }
    }
}