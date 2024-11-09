package com.example.practica09almacenamiento
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var rememberCheckBox: CheckBox
    private lateinit var loginButton: Button
    private lateinit var exitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        rememberCheckBox = findViewById(R.id.rememberCheckBox)
        loginButton = findViewById(R.id.loginButton)
        exitButton = findViewById(R.id.exitButton)

        val preferences = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE)
        val savedUsername = preferences.getString("username", "")
        val savedPassword = preferences.getString("password", "")

        // Load saved credentials if checkbox was checked previously
        if (savedUsername != "" && savedPassword != "") {
            usernameEditText.setText(savedUsername)
            passwordEditText.setText(savedPassword)
            rememberCheckBox.isChecked = true
        }

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username == "admin" && password == "1234") {
                if (rememberCheckBox.isChecked) {
                    preferences.edit().putString("username", username)
                        .putString("password", password).apply()
                } else {
                    preferences.edit().clear().apply()
                }
                startActivity(Intent(this, MenuActivity::class.java))
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        exitButton.setOnClickListener { finish() }
    }
}