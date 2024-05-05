package com.example.ulsuapp

import android.content.Intent
import android.os.Bundle
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbHelper = DBHelper(requireContext())
//        val database = dbHelper!!.writableDatabase
        val database = dbHelper!!.readableDatabase
        val cursor = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null)

        val list = ArrayList<String>()
        if (cursor.moveToFirst()) {
            val pet_name = cursor.getColumnIndex("pet_name")
            val breed = cursor.getColumnIndex("bredd")
            Log.d("Log", pet_name.toString())
            do {
                list.add(pet_name.toString()+""+ breed.toString())
            } while (cursor.moveToNext())
        }

        listView = view.findViewById(R.id.list_view)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, list)
        Log.d("Log", list.toString())
        listView?.adapter = adapter

        listView?.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedItemText = parent.getItemAtPosition(position).toString()
                val intent = Intent(requireContext(), MainActivity2::class.java)
                intent.putExtra("product", selectedItemText)
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
