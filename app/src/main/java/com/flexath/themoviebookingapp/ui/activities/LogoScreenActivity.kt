package com.flexath.themoviebookingapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.flexath.themoviebookingapp.R

class LogoScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            LoginScreenActivity.newIntentFromLoginScreen(this).also {
                startActivity(it)
                finish()
            }
        },2000)

    }
}