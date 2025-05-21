package com.example.cinetrack

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sortSpinner: Spinner
    private lateinit var filterSpinner: Spinner
    private lateinit var favoritesBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        sortSpinner = findViewById(R.id.sortSpinner)
        filterSpinner = findViewById(R.id.categorySpinner)
        favoritesBtn = findViewById(R.id.Favoritesbtn)

        // Load name from SharedPreferences
        val sharedPref = getSharedPreferences("JustPosePrefs", MODE_PRIVATE)
        val name = sharedPref.getString("USERNAME", "User")

        // Update UI
        val userNameText = findViewById<TextView>(R.id.userNameText)
        userNameText.text = "$name !"


        // Set up all functionalities
        setupSortSpinner()
        setupFilterSpinner()
        setupFavoritesButton()
        setupInfoIcon()
    }

    private fun setupSortSpinner() {
        val sortOptions = listOf("Sort by Rating", "Sort by Name", "Sort by Date")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sortOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sortSpinner.adapter = adapter

        sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selected = sortOptions[position]
                Toast.makeText(this@MainActivity, "Sorting by $selected", Toast.LENGTH_SHORT).show()
                // TODO: Add sorting logic here
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupFilterSpinner() {
        val filterOptions = listOf("All", "Series", "Movies")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, filterOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        filterSpinner.adapter = adapter

        filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selected = filterOptions[position]
                Toast.makeText(this@MainActivity, "Filtering: $selected", Toast.LENGTH_SHORT).show()
                when (selected) {
                    "Series" -> startActivity(Intent(this@MainActivity, series::class.java))
                    "Movies" -> startActivity(Intent(this@MainActivity, movies::class.java))
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupFavoritesButton() {
        favoritesBtn.setOnClickListener {
            startActivity(Intent(this, favourates::class.java))
        }
    }

    private fun setupInfoIcon() {
        val infoIcon = findViewById<ImageView>(R.id.infoIcon)
        infoIcon.setOnClickListener {
            val builder = androidx.appcompat.app.AlertDialog.Builder(this)
            builder.setTitle("About CineTrack 🎬")
            builder.setMessage("CineTrack helps you explore, save, and track your favorite movies.\nManage your watchlist and never miss a must-watch again!")
            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            builder.create().show()
        }
    }
}
