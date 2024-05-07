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
        val database = dbHelper!!.readableDatabase
        val cursor = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null)

        val list = ArrayList<String>()
        while (cursor.moveToNext()) {
            val item_id: Int = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            val item_content: String = cursor.getString(cursor.getColumnIndex(DBHelper.TABLE_NAME_FIRST))
            val pet_sex: String = cursor.getString(cursor.getColumnIndex(DBHelper.TABLE_PET_SEX))
            val breed: String = cursor.getString(cursor.getColumnIndex(DBHelper.TABLE_PET_BREED))
            val information: String = cursor.getString(cursor.getColumnIndex(DBHelper.INFORMATION))
            list.add("ID объявления: " + item_id.toString() + "\nКличка: " + item_content.toString() + "\nПол: "
            + pet_sex.toString() + "\nПорода: " + breed.toString() + "\nДополнительная информация: " + information.toString())
        }

        listView = view.findViewById(R.id.list_view)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, list)
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
