package com.alpercelik.hw1

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.*
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.alpercelik.hw1.activityPackage.Product
import com.alpercelik.hw1.activityPackage.ThirdActivity
import com.alpercelik.hw1.databinding.ActivitySecondBinding
import com.alpercelik.hw1.databinding.ResultDialogBinding


class SecondActivity : AppCompatActivity() {

    lateinit var binding: ActivitySecondBinding // for the entire activity except Dialog
    lateinit var dialogBinding: ResultDialogBinding // for the Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root

        supportActionBar?.hide() // Hiding title bar

        setContentView(view)

        @Suppress("DEPRECATION") // for using deprecated codes without any highlights or errors
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN) // Hiding the status bar
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) // Locking the orientation to Portrait


        var selectedProduct: Product? = null // to find the selected product - to send it another activity
        var selectedCount: Int? = null // to find the count of the chosen fruits - to send it another activity

        // CUSTOMIZED SPINNER
        val fruitList = ArrayList<Fruit>()

        // adding the fruit data to the customized spinner
        fruitList.add(Fruit("Apple (\$1 each)", R.drawable.apple))
        fruitList.add(Fruit("Banana (\$2 each)", R.drawable.banana))
        fruitList.add(Fruit("Carrot (\$3 each)", R.drawable.carrot))
        fruitList.add(Fruit("Cherry (\$4 each)", R.drawable.cherry))
        fruitList.add(Fruit("Orange (\$5 each)", R.drawable.orange))

        // Display the array content in the LOGCAT panel
        for (fruit in fruitList) {
            Log.d("FruitList", "Fruit Name: ${fruit.getName()}, Image Resource: ${fruit.getImageResource()}")
        }

        val adapterr = FruitAdapter(this, fruitList)
        binding.customizedSpinnerStock.adapter = adapterr



        // adding images for the ImageView manipulated by CUSTOMER SPINNER'S CHOICES
        val selectedFruitsList = ArrayList<Fruit>()
        selectedFruitsList.add(Fruit("Apple", R.drawable.apples))
        selectedFruitsList.add(Fruit("Banana", R.drawable.bananas))
        selectedFruitsList.add(Fruit("Carrot", R.drawable.carrots))
        selectedFruitsList.add(Fruit("Cherry", R.drawable.cherries))
        selectedFruitsList.add(Fruit("Orange", R.drawable.oranges))

        // Setting an OnItemSelectedListener for the customized spinner's choice
        binding.customizedSpinnerStock.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected( parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                val selectedFruit = selectedFruitsList[position]
                val selectedImageResource = selectedFruit.getImageResource()
                binding.ivSelectedImage.setImageResource(selectedImageResource)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //nothing
            }
        }



        // finding the count from SeekBar
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                binding.tvSelectedCount.text = "Count: $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Not used, but required to implement
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Not used, but required to implement
            }

        })




        // GETTING NEEDED INFO FROM USER - PART
        binding.btnAdd.setOnClickListener {

            val selectedFruit = fruitList[binding.customizedSpinnerStock.selectedItemPosition]

            // getting the selected count from the SeekBar
            selectedCount = binding.seekBar.progress

            // Log the selected values to LOGCAT with tag names (for debugging)
            Log.d("FruitSelection", selectedFruit.getName().toString())
            Log.d("CountSelection", selectedCount.toString())

            // calculation part
            val totalPrice: Double = calculateTotalPrice(selectedFruit.getName().toString(), selectedCount!!)

            selectedProduct = Product(selectedFruit.getName().toString(), totalPrice)
            Log.d("Product", "${Product.toString()}")

            // Display the result using DIALOG
            val dialog = Dialog(this)

            dialogBinding = ResultDialogBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)

            // Use dialogBinding to access views in the dialog layout
            dialogBinding.tvRes.text = "Total Price of ${selectedFruit.getName()}: $$totalPrice"
            dialogBinding.btnOK.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()


        }


        // FINISHING AND PAYING
        binding.btnFinish.setOnClickListener {
            if (selectedProduct != null) {
                val intent = Intent(this, ThirdActivity::class.java)
                intent.putExtra("count", selectedCount)

                val b = Bundle()
                b.putParcelable("prod", selectedProduct)
                intent.putExtras(b)


                startActivity(intent)
            } else {
                // If selectedProduct is null (empty), show a WARNING MESSAGE using TOAST
                Toast.makeText(this, "You haven't added anything to your basket!", Toast.LENGTH_SHORT).show()
            }
        }


    }


    // Function to calculate the total price
    private fun calculateTotalPrice(selectedFruitName: String, selectedCount: Int): Double {
        var res: Double? = null

        if(selectedFruitName.trim() == "Apple ($1 each)") {
            res = 1.0 * selectedCount
        } else if(selectedFruitName.trim() == "Banana ($2 each)") {
            res = 2.0 * selectedCount
        } else if(selectedFruitName.trim() == "Carrot ($3 each)") {
            res = 3.0 * selectedCount
        } else if(selectedFruitName.trim() == "Cherry ($4 each)") {
            res = 4.0 * selectedCount
        } else if(selectedFruitName.trim() == "Orange ($5 each)") {
            res = 5.0 * selectedCount
        } else {
            res = 0.0
        }

        return res
    }


}