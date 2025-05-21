package com.example.moodweather

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private val handler = Handler(Looper.getMainLooper())
    private var progressStatus = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logo = findViewById<ImageView>(R.id.logoImage)
        progressBar = findViewById(R.id.progressBar)

        // Start logo animation
        val animation = AnimationUtils.loadAnimation(this, R.anim.combo_anim)
        logo.startAnimation(animation)

        // Update progress bar gradually
        Thread {
            while (progressStatus < 100) {
                progressStatus += 1
                handler.post {
                    progressBar.progress = progressStatus
                }
                Thread.sleep(25) // ~2.5 seconds total
            }

            // After bar is full, navigate to MainActivity
            handler.post {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }.start()
    }
}
