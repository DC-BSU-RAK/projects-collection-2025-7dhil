package com.example.moodweather

import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    private lateinit var feelingSpinner: Spinner
    private lateinit var weatherSpinner: Spinner
    private lateinit var actButton: Button
    private lateinit var suggestionText: TextView

    private val moodOptions = arrayOf("Happy", "Sad", "Tired", "Excited")
    private val weatherOptions = arrayOf("Sunny", "Rainy", "Cloudy", "Snowy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        feelingSpinner = findViewById(R.id.feelingSpinner)
        weatherSpinner = findViewById(R.id.weatherSpinner)
        actButton = findViewById(R.id.actButton)

        // Add suggestion text programmatically
        suggestionText = TextView(this).apply {
            textSize = 18f
            setTextColor(resources.getColor(android.R.color.white))
            setBackgroundColor(resources.getColor(android.R.color.black)) // Black background
            visibility = View.INVISIBLE
            setPadding(24, 16, 24, 16)
        }

        val layout = findViewById<ConstraintLayout>(R.id.main)
        layout.addView(suggestionText)


        val moodAdapter = ArrayAdapter(this, R.layout.spinner_item, moodOptions)
        val weatherAdapter = ArrayAdapter(this, R.layout.spinner_item, weatherOptions)

        feelingSpinner.adapter = moodAdapter
        weatherSpinner.adapter = weatherAdapter

        actButton.setOnClickListener {
            val mood = feelingSpinner.selectedItem.toString()
            val weather = weatherSpinner.selectedItem.toString()
            showSuggestion(getSuggestedActivity(mood, weather))
        }

        // the info btn
        val infoIcon = findViewById<ImageView>(R.id.infoIcon) //
        infoIcon.setOnClickListener {
            val builder = androidx.appcompat.app.AlertDialog.Builder(this)
            builder.setTitle("About The Application")
            builder.setMessage("Choose your mood and the weather and I'll give you an activity for you to do")
            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun getSuggestedActivity(mood: String, weather: String): String {
        return when (mood to weather) {
            "Happy" to "Sunny" -> "Go for swimming in the pool and have your fav Ice cream!"
            "Happy" to "Rainy" -> "Stay cozy in the bed watching a series while eating a ramen"
            "Happy" to "Cloudy" -> "Go for a walk"
            "Happy" to "Snowy" -> "Build a snowman!"

            "Sad" to "Sunny" -> "Take a relaxing nap in AC"
            "Sad" to "Rainy" -> "Make a cup of tea and talk to someone"
            "Sad" to "Cloudy" -> "Cook your favourate meal!"
            "Sad" to "Snowy" -> "Organize your space"

            "Tired" to "Sunny" -> "Do some light yoga in the sun."
            "Tired" to "Rainy" -> "Take a nap while listening to rain sounds."
            "Tired" to "Cloudy" -> "Enjoy a quiet book with tea."
            "Tired" to "Snowy" -> "Wrap up and binge-watch a series."

            "Excited" to "Sunny" -> "Plan a beach day or picnic!"
            "Excited" to "Rainy" -> "Splash in puddles or do crafts indoors."
            "Excited" to "Cloudy" -> "Go to a game center or indoor trampoline park!"
            "Excited" to "Snowy" -> "Go sledding or have a snowball fight!"

            else -> "Stay cozy and enjoy some quiet time."
        }
    }

    private fun showSuggestion(text: String) {
        suggestionText.text = text
        suggestionText.visibility = View.VISIBLE

        // Fade-in animation
        val fadeIn = AlphaAnimation(0f, 1f).apply {
            duration = 1000
            fillAfter = true
        }
        suggestionText.startAnimation(fadeIn)

        // Position below the button
        val params = suggestionText.layoutParams as ConstraintLayout.LayoutParams
        params.topToBottom = R.id.actButton
        params.startToStart = R.id.main
        params.endToEnd = R.id.main
        params.topMargin = 24
        suggestionText.layoutParams = params
    }
}
