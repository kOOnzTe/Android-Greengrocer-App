package com.alpercelik.hw1.activityPackage

import android.app.AlertDialog
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.alpercelik.hw1.FourthActivity
import com.alpercelik.hw1.R
import com.alpercelik.hw1.SecondActivity
import com.alpercelik.hw1.databinding.ActivitySecondBinding
import com.alpercelik.hw1.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThirdBinding.inflate(layoutInflater)
        val view = binding.root

        supportActionBar?.hide() // Hiding title bar

        setContentView(view)

        @Suppress("DEPRECATION") // for using deprecated codes without any highlights or errors
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN) // Hiding the status bar
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) // Locking the orientation to Portrait


        val rintent = intent
        val receivedCount = rintent.getIntExtra("count", 0)
        val rb = rintent.extras
        val p = rb!!.getParcelable<Product>("prod")

        binding.tvOrders.setText(
            """
                ${p.toString()}
              Count: $receivedCount
                """.trimIndent()
        )

        //
        val alertDialogResult = AlertDialog.Builder(this)
        alertDialogResult.setTitle("Here is the result")
        alertDialogResult.setMessage("${p.toString()}\n" +
                "Count: $receivedCount")

        alertDialogResult.setNeutralButton("OK") { dialog, which ->

        }

        val alDialog = alertDialogResult.create()
        alDialog.show()


        // PAY ALERT DIALOG
        binding.btnPay.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Confirmation")
            alertDialogBuilder.setMessage("Are you sure you want to proceed with the payment?")

            alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
                // User clicked "Yes," navigate to another activity
                val intent = Intent(this, FourthActivity::class.java)
                startActivity(intent)
            }

            alertDialogBuilder.setNegativeButton("No") { _, _ ->
                // User clicked "No," do nothing
            }

            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }


        // turn back to the second activity
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

    }
}