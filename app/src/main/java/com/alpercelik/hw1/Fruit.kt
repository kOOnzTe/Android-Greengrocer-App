package com.alpercelik.hw1

class Fruit(name: String?, imageResource: Int) {
    // data fields
    private var name: String? = name
    private var imageResource = imageResource


    // Define per-item prices for each fruit
    val pricePerItem: Double = when (name) {
        "Apple" -> 1.0
        "Banana" -> 2.0
        "Carrot" -> 3.0
        "Cherry" -> 4.0
        "Orange" -> 5.0
        else -> 0.0 // Default price for unknown fruits
    }

    // Getters
    fun getName(): String? {
        return name
    }

    fun getImageResource(): Int {
        return imageResource
    }


}
