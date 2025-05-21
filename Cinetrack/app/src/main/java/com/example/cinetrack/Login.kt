package com.example.cinetrack

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val nameInput = findViewById<EditText>(R.id.entername)
        val nextButton = findViewById<Button>(R.id.contBtn)

        nextButton.setOnClickListener {
            val name = nameInput.text.toString().trim()

            if (name.isNotEmpty()) {
                // Save name to SharedPreferences
                val sharedPref = getSharedPreferences("JustPosePrefs", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("USERNAME", name)
                editor.apply()

                // Go to MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please enter your name!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}