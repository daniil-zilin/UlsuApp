package com.example.ulsuapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment


class HomeFragment : Fragment() {
    var dbHelper: DBHelper? = null
    var listView: ListView? = null
    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbHelper = DBHelper(requireContext())
//        val database = dbHelper!!.writableDatabase
        val database = dbHelper!!.readableDatabase
        val cursor = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null)

        val list = ArrayList<String>()
        while (cursor.moveToNext()) {
            val item_id: Int = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            val item_content: String = cursor.getString(cursor.getColumnIndex(DBHelper.TABLE_NAME_FIRST))
            val pet_sex: String = cursor.getString(cursor.getColumnIndex(DBHelper.TABLE_PET_SEX))
//          Log.d("Log", pet_name.toString())
            list.add(item_id.toString() + "\n" + item_content.toString() + "\n" + pet_sex.toString())
        }

        listView = view.findViewById(R.id.list_view)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, list)
        Log.d("Log", list.toString())
        listView?.adapter = adapter

        listView?.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedItemText = parent.getItemAtPosition(position).toString()
                val pet_name = parent.getItemAtPosition(1).toString()
                val intent = Intent(requireContext(), MainActivity2::class.java)
                intent.putExtra("product", selectedItemText)
                intent.putExtra("pet_name", pet_name)
                startActivity(intent)
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}
