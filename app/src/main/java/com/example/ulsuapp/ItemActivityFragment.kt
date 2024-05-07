package com.example.ulsuapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import java.util.regex.Pattern

class ItemActivityFragment : Fragment() {
    var dbHelper: DBHelper? = null
    var listView: ListView? = null
    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbHelper = DBHelper(requireContext())

        val database = dbHelper!!.readableDatabase
        val cursor = database.query(DBHelper.FAVORITES, null, null, null, null, null, null)

        val list = ArrayList<String>()
        while (cursor.moveToNext()) {
            val item_id: Int = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            val item_content: String = cursor.getString(cursor.getColumnIndex(DBHelper.TABLE_NAME_FIRST))

            list.add(item_id.toString() + "\n" + item_content.toString())
        }

        listView = view.findViewById(R.id.list_view_2)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, list)
//        Log.d("Log", list.toString())
        listView?.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_item, container, false)
    }
}