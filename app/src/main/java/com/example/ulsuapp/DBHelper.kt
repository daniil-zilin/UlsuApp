package com.example.ulsuapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns


class DBHelper(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION) {
        companion object {
            const val DATABASE_NAME = "pet_shop"
            const val DATABASE_VERSION = 1
            const val TABLE_NAME = "pets"
        }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE pets (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
            "pet_name VARCHAR(20)," +
            "age INTEGER," +
            "breed VARCHAR(50), " +
            "sex VARCHAR(20), " +
            "information VARCHAR(255))"
        )

        db?.execSQL("INSERT INTO pets (pet_name, age, breed, sex, information) VALUES " +
            "('Симка', '5', 'Уличный', 'Девочка', 'Приучена к лотку, отдам бесплатно')," +
            " ('Масяня', 0.5, 'Домашняя', 'Девочка', 'Отдам в хорошие руки, любит беситься')"
        )

        db?.execSQL("CREATE TABLE owners (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
            "address VARCHAR(255)," +
            "email VARCHAR(100)" +
            ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME)
    }
}