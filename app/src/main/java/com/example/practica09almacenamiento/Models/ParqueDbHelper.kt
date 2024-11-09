package com.example.practica09almacenamiento.Models

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ParqueDbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "Parques.db"
        const val DATABASE_VERSION = 1

        const val TABLE_NAME = "parques"
        const val COLUMN_ID = "id"
        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_UBICACION = "ubicacion"
        const val COLUMN_TAMANO = "tamano"
        const val COLUMN_FLORA = "flora"
        const val COLUMN_FAUNA = "fauna"

    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE Parques (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT," +
                    "ubicacion TEXT," +
                    "tamano REAL," +
                    "flora TEXT," +
                    "fauna TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Parques")
        onCreate(db)
    }
}