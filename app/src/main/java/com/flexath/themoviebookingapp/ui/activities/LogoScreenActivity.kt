package com.flexath.themoviebookingapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.model.CinemaModel
import com.flexath.themoviebookingapp.data.model.CinemaModelImpl

class LogoScreenActivity : AppCompatActivity() {

    private val mMovieModel:CinemaModel = CinemaModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo_screen)

        getCitiesListFromRetrofit()

        Handler(Looper.getMainLooper()).postDelayed({
            LoginScreenActivity.newIntentFromLoginScreen(this).also {
                startActivity(it)
                finish()
            }
        },2000)

    }

    private fun getCitiesListFromRetrofit() {
        mMovieModel.insertCities(
            onSuccess = {
                Toast.makeText(this,"Cities Network call succeeded",Toast.LENGTH_SHORT).show()
            },
            onFailure = {
                Toast.makeText(this,"Cities fail",Toast.LENGTH_SHORT).show()
            }
        )

        mMovieModel.insertCinemaConfig(
            onSuccess = {
                Toast.makeText(this,"Config Network call succeeded",Toast.LENGTH_SHORT).show()
            },
            onFailure = {
                Toast.makeText(this,"Config Fail",Toast.LENGTH_SHORT).show()
            }
        )
    }

}