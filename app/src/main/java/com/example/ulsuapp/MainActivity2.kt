package com.example.ulsuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.view.View


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main2)

        val extras = intent.extras
        val product = extras?.getString("product").toString()

        val textView = findViewById<TextView>(R.id.name_of_item)
        textView.text = product
    }
}