package com.example.ema_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore


class splashs : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splashs)


        val logo = findViewById<ImageView>(R.id.imageView3)
        val logo2 = findViewById<ImageView>(R.id.imageView4)
        val text = findViewById<TextView>(R.id.textView)




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        window.decorView.systemUiVisibility = (window.decorView.systemUiVisibility
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        val animation1 = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.up_slide
        )
        val animation2 = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.down_slide
        )

        logo.startAnimation(animation1)
        logo2.startAnimation(animation2)
        text.startAnimation(animation2)
        Handler().postDelayed({
            if (getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).getBoolean("isFirstRun", true)) {
                val intent =
                    Intent(this@splashs, PresentacionActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent =
                    Intent(this@splashs, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 4000)


    }
}