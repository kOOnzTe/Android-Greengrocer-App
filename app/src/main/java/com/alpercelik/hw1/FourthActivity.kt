package com.alpercelik.hw1

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.alpercelik.hw1.databinding.ActivityFourthBinding
import com.alpercelik.hw1.databinding.ActivitySecondBinding

class FourthActivity : AppCompatActivity() {


    lateinit var binding: ActivityFourthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFourthBinding.inflate(layoutInflater)
        val view = binding.root

        supportActionBar?.hide() // Hiding title bar

        setContentView(view)

        @Suppress("DEPRECATION") // for using deprecated codes without any highlights or errors
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN) // Hiding the status bar
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) // Locking the orientation to Portrait


        // "THANKS" ANIMATION
        val colorBlinkAnimation = AnimationUtils.loadAnimation(this, R.anim.color_blink_animation) // Load the blink animation from the XML resource
        binding.tvThanks.startAnimation(colorBlinkAnimation)


        // turn back to starting
        binding.btnBackAgain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // finishing the app
        binding.btnExitt.setOnClickListener {
            finishAffinity() // Close all activities and exit the app
        }
    }
}