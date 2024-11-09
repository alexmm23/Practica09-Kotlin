package com.example.practica09almacenamiento

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practica09almacenamiento.Models.ParqueDbHelper

class ParqueActivity : AppCompatActivity() {

    private lateinit var nombreEditText: EditText
    private lateinit var ubicacionEditText: EditText
    private lateinit var tamanoEditText: EditText
    private lateinit var floraEditText: EditText
    private lateinit var faunaEditText: EditText
    private lateinit var registrarButton: Button
    private lateinit var buscarButton: Button
    private lateinit var editarButton: Button
    private lateinit var eliminarButton: Button
    private lateinit var dbHelper: ParqueDbHelper
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parque)

        // Inicializar los componentes de la vista
        nombreEditText = findViewById(R.id.nombreEditText)
        ubicacionEditText = findViewById(R.id.ubicacionEditText)
        tamanoEditText = findViewById(R.id.tamanoEditText)
        floraEditText = findViewById(R.id.floraEditText)
        faunaEditText = findViewById(R.id.faunaEditText)
        registrarButton = findViewById(R.id.registrarButton)
        buscarButton = findViewById(R.id.buscarButton)
        editarButton = findViewById(R.id.editarButton)
        eliminarButton = findViewById(R.id.eliminarButton)

        // Inicializar la base de datos
        dbHelper = ParqueDbHelper(this)
        db = dbHelper.writableDatabase

        // Configurar los botones
        registrarButton.setOnClickListener { registrarParque() }
        buscarButton.setOnClickListener { buscarParque() }
        editarButton.setOnClickListener { editarParque() }
        eliminarButton.setOnClickListener { eliminarParque() }
    }

    private fun registrarParque() {
        val nombre = nombreEditText.text.toString()
        val ubicacion = ubicacionEditText.text.toString()
        val tamano = tamanoEditText.text.toString().toDoubleOrNull()
        val flora = floraEditText.text.toString()
        val fauna = faunaEditText.text.toString()

        if (nombre.isNotEmpty() && ubicacion.isNotEmpty() && tamano != null && flora.isNotEmpty() && fauna.isNotEmpty()) {
            val values = ContentValues().apply {
                put("nombre", nombre)
                put("ubicacion", ubicacion)
                put("tamano", tamano)
                put("flora", flora)
                put("fauna", fauna)
            }

            val newRowId = db.insert("parques", null, values)
            if (newRowId != -1L) {
                Toast.makeText(this, "Parque registrado con éxito", Toast.LENGTH_SHORT).show()
                limpiarCampos()
            } else {
                Toast.makeText(this, "Error al registrar el parque", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun buscarParque() {
        val nombre = nombreEditText.text.toString()
        val cursor = db.query(
            "parques", null, "nombre = ?", arrayOf(nombre), null, null, null
        )

        if (cursor.moveToFirst()) {
            ubicacionEditText.setText(cursor.getString(cursor.getColumnIndexOrThrow("ubicacion")))
            tamanoEditText.setText(
                cursor.getDouble(cursor.getColumnIndexOrThrow("tamano")).toString().format("%.2f")
            )
            floraEditText.setText(cursor.getString(cursor.getColumnIndexOrThrow("flora")))
            faunaEditText.setText(cursor.getString(cursor.getColumnIndexOrThrow("fauna")))
            Toast.makeText(this, "Parque encontrado", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Parque no encontrado", Toast.LENGTH_SHORT).show()
        }
        cursor.close()
    }

    private fun editarParque() {
        val nombre = nombreEditText.text.toString()
        val values = ContentValues().apply {
            put("ubicacion", ubicacionEditText.text.toString())
            put("tamano", tamanoEditText.text.toString().toDoubleOrNull())
            put("flora", floraEditText.text.toString())
            put("fauna", faunaEditText.text.toString())
        }

        val rowsAffected = db.update("parques", values, "nombre = ?", arrayOf(nombre))
        if (rowsAffected > 0) {
            Toast.makeText(this, "Parque actualizado con éxito", Toast.LENGTH_SHORT).show()
            limpiarCampos()
        } else {
            Toast.makeText(this, "Error al actualizar el parque", Toast.LENGTH_SHORT).show()
        }
    }

    private fun eliminarParque() {
        val nombre = nombreEditText.text.toString()
        val rowsDeleted = db.delete("parques", "nombre = ?", arrayOf(nombre))
        if (rowsDeleted > 0) {
            Toast.makeText(this, "Parque eliminado con éxito", Toast.LENGTH_SHORT).show()
            limpiarCampos()
        } else {
            Toast.makeText(this, "Error al eliminar el parque", Toast.LENGTH_SHORT).show()
        }
    }

    private fun limpiarCampos() {
        nombreEditText.text.clear()
        ubicacionEditText.text.clear()
        tamanoEditText.text.clear()
        floraEditText.text.clear()
        faunaEditText.text.clear()
    }
}
