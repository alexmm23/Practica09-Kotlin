package com.example.practica09almacenamiento
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class MenuActivity : AppCompatActivity() {

    private lateinit var parquesButton: Button
    private lateinit var guardabosquesButton: Button
    private lateinit var listadoParquesButton: Button
    private lateinit var listadoGuardabosquesButton: Button
    private lateinit var logoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // Configurar Toolbar como ActionBar
        val toolbar = findViewById<Toolbar>(R.id.menuToolbar)
        setSupportActionBar(toolbar)
    /*
        // Inicializar botones
        parquesButton = findViewById(R.id.parquesButton)
        guardabosquesButton = findViewById(R.id.guardabosquesButton)
        listadoParquesButton = findViewById(R.id.listadoParquesButton)
        listadoGuardabosquesButton = findViewById(R.id.listadoGuardabosquesButton)
        logoutButton = findViewById(R.id.logoutButton)

        parquesButton.setOnClickListener {
            startActivity(Intent(this, ParqueActivity::class.java))
            Log.d("MenuActivity", "Parques")
        }

        guardabosquesButton.setOnClickListener {
            startActivity(Intent(this, GuardaBosquesActivity::class.java))
        }

        listadoParquesButton.setOnClickListener {
            startActivity(Intent(this, ListadoParquesActivity::class.java))
        }

        listadoGuardabosquesButton.setOnClickListener {
            startActivity(Intent(this, ListadoGuardabosquesActivity::class.java))
        }
        logoutButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

     */
    }

    // Crear el menú de opciones en la Toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return true
    }

    // Manejar las acciones de los elementos del menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_parques -> {
                startActivity(Intent(this, ParqueActivity::class.java))
                Toast.makeText(this, "Parques", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_guardabosques -> {
                startActivity(Intent(this, GuardaBosquesActivity::class.java))
                return true
            }
            R.id.action_listado_parques -> {
                startActivity(Intent(this, ListadoParquesActivity::class.java))
                return true
            }

            R.id.action_listado_guardabosques -> {
                startActivity(Intent(this, ListadoGuardabosquesActivity::class.java))
                return true
            }


            R.id.action_logout -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
