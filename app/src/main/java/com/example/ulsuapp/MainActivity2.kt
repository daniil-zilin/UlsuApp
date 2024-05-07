package com.example.ulsuapp

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern


class MainActivity2 : AppCompatActivity() {
    var dbHelper: DBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val context: Context = this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val extras = intent.extras
        val product = extras?.getString("product").toString()

        val textView = findViewById<TextView>(R.id.name_of_item)
        textView.text = product

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val contentValues = ContentValues()

            dbHelper = DBHelper(context)
            val database = dbHelper!!.writableDatabase

            val regex = Regex("\\s\\d\\s")
            val matches = regex.findAll(product)

            for (match in matches) {
                Log.d("wd", match.value.toString())
                contentValues.put(BaseColumns._ID, match.value.toString())
            }

            contentValues.put("pet_name", product)
            database.insert(DBHelper.FAVORITES, null, contentValues)

            val toast = Toast.makeText(context, "Успешно добавлено в избранное", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}