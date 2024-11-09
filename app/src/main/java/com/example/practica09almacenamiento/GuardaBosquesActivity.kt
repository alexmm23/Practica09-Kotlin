package com.example.practica09almacenamiento

import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practica09almacenamiento.Models.GuardaBosquesDbHelper

class GuardaBosquesActivity : AppCompatActivity() {

    private lateinit var dbHelper: GuardaBosquesDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guarda_bosques)

        dbHelper = GuardaBosquesDbHelper(this)

        val nombreEditText = findViewById<EditText>(R.id.nombreEditText)
        val edadEditText = findViewById<EditText>(R.id.edadEditText)
        val experienciaEditText = findViewById<EditText>(R.id.experienciaEditText)
        val parqueEditText = findViewById<EditText>(R.id.parqueEditText)
        val registrarButton = findViewById<Button>(R.id.registrarButton)

        registrarButton.setOnClickListener {
            val nombre = nombreEditText.text.toString()
            val edad = edadEditText.text.toString().toIntOrNull()
            val experiencia = experienciaEditText.text.toString()
            val parque = parqueEditText.text.toString()

            if (nombre.isNotEmpty() && edad != null && experiencia.isNotEmpty() && parque.isNotEmpty()) {
                val db = dbHelper.writableDatabase
                val values = ContentValues().apply {
                    put(GuardaBosquesDbHelper.COLUMN_NOMBRE, nombre)
                    put(GuardaBosquesDbHelper.COLUMN_EDAD, edad)
                    put(GuardaBosquesDbHelper.COLUMN_EXPERIENCIA, experiencia)
                    put(GuardaBosquesDbHelper.COLUMN_PARQUE, parque)
                }
                db.insert(GuardaBosquesDbHelper.TABLE_NAME, null, values)
                Toast.makeText(this, "Guardabosque registrado", Toast.LENGTH_SHORT).show()
                nombreEditText.text.clear()
                edadEditText.text.clear()
                experienciaEditText.text.clear()
                parqueEditText.text.clear()
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}