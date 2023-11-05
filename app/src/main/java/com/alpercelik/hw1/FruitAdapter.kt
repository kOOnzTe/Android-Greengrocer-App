package com.alpercelik.hw1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class FruitAdapter(private val context: Context, private val fruitList: List<Fruit>) : BaseAdapter() {

    override fun getCount(): Int {
        return fruitList.size
    }

    override fun getItem(position: Int): Any {
        return fruitList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.stock_spinner_row, parent, false)
        }

        val fruitImage = convertView?.findViewById<ImageView>(R.id.iv_image)
        val fruitName = convertView?.findViewById<TextView>(R.id.tv_name)

        val fruit = fruitList[position]
        fruitImage?.setImageResource(fruit.getImageResource())
        fruitName?.text = fruit.getName()

        return convertView!!
    }
}
