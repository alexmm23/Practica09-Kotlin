// ListadoGuardabosquesActivity.kt
package com.example.practica09almacenamiento

import android.database.Cursor
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica09almacenamiento.Models.GuardaBosquesDbHelper

class ListadoGuardabosquesActivity : AppCompatActivity() {

    private lateinit var dbHelper: GuardaBosquesDbHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GuardabosqueAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_guardabosques)

        dbHelper = GuardaBosquesDbHelper(this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val guardabosques = loadGuardabosquesFromDatabase()
        adapter = GuardabosqueAdapter(guardabosques)
        recyclerView.adapter = adapter
    }

    private fun loadGuardabosquesFromDatabase(): List<Guardabosque> {
        val guardabosques = mutableListOf<Guardabosque>()
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(GuardaBosquesDbHelper.TABLE_NAME, null, null, null, null, null, null)

        with(cursor) {
            while (moveToNext()) {
                val nombre = getString(getColumnIndexOrThrow(GuardaBosquesDbHelper.COLUMN_NOMBRE))
                val edad = getInt(getColumnIndexOrThrow(GuardaBosquesDbHelper.COLUMN_EDAD))
                val experiencia = getString(getColumnIndexOrThrow(GuardaBosquesDbHelper.COLUMN_EXPERIENCIA))
                val parque = getString(getColumnIndexOrThrow(GuardaBosquesDbHelper.COLUMN_PARQUE))
                guardabosques.add(Guardabosque(nombre, edad, experiencia, parque))
            }
        }
        cursor.close()
        return guardabosques
    }
}