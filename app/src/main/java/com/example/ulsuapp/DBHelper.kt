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
            const val FAVORITES = "favorites"
            const val TABLE_NAME_FIRST = "pet_name"
            const val TABLE_PET_SEX = "sex"
            const val TABLE_PET_BREED = "breed"
            const val INFORMATION = "information"
            const val ADDRESS = "address"
        }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE pets (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
            "pet_name VARCHAR(20)," +
            "breed VARCHAR(50), " +
            "sex VARCHAR(20), " +
            "information VARCHAR(255)," +
            "address VARCHAR(255))"
        )

        db?.execSQL("INSERT INTO pets (pet_name, breed, sex, information, address) VALUES " +
            "('Симка', 'Уличный', 'Девочка', 'Приучена к лотку, отдам бесплатно', 'г. Ульяновск, ул. Ульяновская д.77')," +
            " ('Масяня', 'Домашняя', 'Девочка', 'Отдам в хорошие руки, любит беситься', 'г. Ульяновск, Пушкарёва д.22, кв.4')," +
            " ('Барсик', 'Уличный', 'Мальчик', 'Взрослый кот, любит покушать', 'г. Сызрань, ул. Космонавтов 9, кв.22')"
        )

        db?.execSQL("CREATE TABLE favorites (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
            "pet_name VARCHAR(255))"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME)
    }
}