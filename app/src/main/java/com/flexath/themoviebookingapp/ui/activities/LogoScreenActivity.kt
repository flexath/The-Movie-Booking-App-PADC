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

    private val mCinemaModel:CinemaModel = CinemaModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo_screen)

        setUpNetworkCall()

        Handler(Looper.getMainLooper()).postDelayed({

            if(mCinemaModel.getOtp(201)?.token?.isNotEmpty() == true) {
                MainActivity.newIntentFromMainActivity(this,null).also {
                    startActivity(it)
                }
            } else {
                LoginScreenActivity.newIntentFromLoginScreen(this).also {
                    startActivity(it)
                    finish()
                }
            }
        },3000)

    }

    override fun onRestart() {
        super.onRestart()
        finish()
    }

    private fun setUpNetworkCall() {
        mCinemaModel.insertCities(
            onSuccess = {

            },
            onFailure = {
                Toast.makeText(this,"Cities fail",Toast.LENGTH_SHORT).show()
            }
        )

        mCinemaModel.insertCinemaConfig(
            onSuccess = {

            },
            onFailure = {
                Log.i("ConfigATH",it)
                Toast.makeText(this,"Config Fail",Toast.LENGTH_SHORT).show()
            }
        )

        mCinemaModel.insertCinemaInfo(
            onSuccess = {

            },
            onFailure = {
                Toast.makeText(this,"Cinema Info Fail",Toast.LENGTH_SHORT).show()
            }
        )
    }

}