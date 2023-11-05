package com.alpercelik.hw1.activityPackage

import android.os.Parcel
import android.os.Parcelable

class Product(val name: String, val price: Double) : Parcelable {

    // secondary constructor
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeDouble(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {

        // static member
        val sttcMem = "this object is parcelized"
        val cnt = 1

        // static methods
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }

    // member method
    fun calculateTotalPrice(count: Int): Double {
        return price * count
    }


    override fun toString(): String {
        return "\nFruit Name: $name\nPrice: $price"
    }
}
