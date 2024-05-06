package com.example.ulsuapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext


class MainActivity2 : AppCompatActivity() {
    var dbHelper: DBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val context: Context = this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val extras = intent.extras
        val product = extras?.getString("product").toString()
        val pet_name = extras?.getString("pet_name").toString()

        val textView = findViewById<TextView>(R.id.name_of_item)
        val textView2 = findViewById<TextView>(R.id.textView2)
        textView.text = product
        textView2.text = pet_name

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val contentValues = ContentValues()

            dbHelper = DBHelper(context)
            val database = dbHelper!!.writableDatabase
            val cursor = database.query(DBHelper.FAVORITES, null, null, null, null, null, null)
            contentValues.put("pet_name", product)
            database.insert(DBHelper.FAVORITES, null, contentValues)


            val toast = Toast.makeText(context, pet_name, Toast.LENGTH_SHORT)
            toast.show()

        }

    }

}