package com.example.practica09almacenamiento

import com.example.practica09almacenamiento.Models.ParqueAdapter
import com.example.practica09almacenamiento.Models.ParqueDbHelper
import android.database.Cursor
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica09almacenamiento.Models.Parque

class ListadoParquesActivity : AppCompatActivity() {

    private lateinit var dbHelper: ParqueDbHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ParqueAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_parques)

        dbHelper = ParqueDbHelper(this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val parques = loadParquesFromDatabase()
        adapter = ParqueAdapter(parques)
        recyclerView.adapter = adapter
    }

    private fun loadParquesFromDatabase(): List<Parque> {
        val parques = mutableListOf<Parque>()
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query("parques", null, null, null, null, null, null)

        with(cursor) {
            while (moveToNext()) {
                val nombre = getString(getColumnIndexOrThrow("nombre"))
                val ubicacion = getString(getColumnIndexOrThrow("ubicacion"))
                val tamano = getDouble(getColumnIndexOrThrow("tamano"))
                val flora = getString(getColumnIndexOrThrow("flora"))
                val fauna = getString(getColumnIndexOrThrow("fauna"))
                parques.add(Parque(nombre, ubicacion, tamano, flora, fauna))
            }
        }
        cursor.close()
        return parques
    }
}