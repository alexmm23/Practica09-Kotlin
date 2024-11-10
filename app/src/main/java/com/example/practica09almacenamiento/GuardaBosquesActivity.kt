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
    private var currentGuardabosqueId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guarda_bosques)

        dbHelper = GuardaBosquesDbHelper(this)

        val nombreEditText = findViewById<EditText>(R.id.nombreEditText)
        val edadEditText = findViewById<EditText>(R.id.edadEditText)
        val experienciaEditText = findViewById<EditText>(R.id.experienciaEditText)
        val parqueEditText = findViewById<EditText>(R.id.parqueEditText)
        val registrarButton = findViewById<Button>(R.id.registrarButton)
        val buscarButton = findViewById<Button>(R.id.buscarButton)
        val editarButton = findViewById<Button>(R.id.editarButton)
        val borrarButton = findViewById<Button>(R.id.borrarButton)

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
                clearFields()
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        buscarButton.setOnClickListener {
            val nombre = nombreEditText.text.toString()
            if (nombre.isNotEmpty()) {
                val cursor = dbHelper.buscarGuardabosque(nombre)
                if (cursor.moveToFirst()) {
                    currentGuardabosqueId = cursor.getInt(cursor.getColumnIndexOrThrow(GuardaBosquesDbHelper.COLUMN_ID))
                    edadEditText.setText(cursor.getInt(cursor.getColumnIndexOrThrow(GuardaBosquesDbHelper.COLUMN_EDAD)).toString())
                    experienciaEditText.setText(cursor.getString(cursor.getColumnIndexOrThrow(GuardaBosquesDbHelper.COLUMN_EXPERIENCIA)))
                    parqueEditText.setText(cursor.getString(cursor.getColumnIndexOrThrow(GuardaBosquesDbHelper.COLUMN_PARQUE)))
                    Toast.makeText(this, "Guardabosque encontrado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Guardabosque no encontrado", Toast.LENGTH_SHORT).show()
                }
                cursor.close()
            } else {
                Toast.makeText(this, "Por favor, ingrese el nombre del guardabosque", Toast.LENGTH_SHORT).show()
            }
        }

        editarButton.setOnClickListener {
            val id = currentGuardabosqueId
            val nombre = nombreEditText.text.toString()
            val edad = edadEditText.text.toString().toIntOrNull()
            val experiencia = experienciaEditText.text.toString()
            val parque = parqueEditText.text.toString()

            if (id != null && nombre.isNotEmpty() && edad != null && experiencia.isNotEmpty() && parque.isNotEmpty()) {
                dbHelper.editarGuardabosque(id, nombre, edad, experiencia, parque)
                Toast.makeText(this, "Guardabosque actualizado", Toast.LENGTH_SHORT).show()
                clearFields()
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        borrarButton.setOnClickListener {
            val id = currentGuardabosqueId
            if (id != null) {
                dbHelper.borrarGuardabosque(id)
                Toast.makeText(this, "Guardabosque borrado", Toast.LENGTH_SHORT).show()
                clearFields()
            } else {
                Toast.makeText(this, "Por favor, busque un guardabosque primero", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearFields() {
        findViewById<EditText>(R.id.nombreEditText).text.clear()
        findViewById<EditText>(R.id.edadEditText).text.clear()
        findViewById<EditText>(R.id.experienciaEditText).text.clear()
        findViewById<EditText>(R.id.parqueEditText).text.clear()
        currentGuardabosqueId = null
    }
}