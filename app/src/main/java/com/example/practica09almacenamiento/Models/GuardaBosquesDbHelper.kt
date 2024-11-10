package com.example.practica09almacenamiento.Models

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class GuardaBosquesDbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "Guardabosques.db"
        const val DATABASE_VERSION = 1

        const val TABLE_NAME = "guardabosques"
        const val COLUMN_ID = "id"
        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_EDAD = "edad"
        const val COLUMN_EXPERIENCIA = "experiencia"
        const val COLUMN_PARQUE = "parque"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_NOMBRE TEXT," +
                    "$COLUMN_EDAD INTEGER," +
                    "$COLUMN_EXPERIENCIA TEXT," +
                    "$COLUMN_PARQUE TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun buscarGuardabosque(nombre: String): Cursor {
        val db = this.readableDatabase
        return db.query(TABLE_NAME, null, "$COLUMN_NOMBRE=?", arrayOf(nombre), null, null, null)
    }

    fun editarGuardabosque(id: Int, nombre: String, edad: Int, experiencia: String, parque: String): Int {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, nombre)
            put(COLUMN_EDAD, edad)
            put(COLUMN_EXPERIENCIA, experiencia)
            put(COLUMN_PARQUE, parque)
        }
        return db.update(TABLE_NAME, values, "$COLUMN_ID=?", arrayOf(id.toString()))
    }

    fun borrarGuardabosque(id: Int): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "$COLUMN_ID=?", arrayOf(id.toString()))
    }
}