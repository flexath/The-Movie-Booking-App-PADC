package com.flexath.themoviebookingapp.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.model.CinemaModel
import com.flexath.themoviebookingapp.data.model.CinemaModelImpl
import com.flexath.themoviebookingapp.ui.helpers.OtpValidationChecker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_otp_screen.*

class OtpScreenActivity : AppCompatActivity() {

    private lateinit var mOtpValidationChecker: OtpValidationChecker
    private val mMovieModel: CinemaModel = CinemaModelImpl

    companion object {
        const val EXTRA_PHONE_NUMBER = "PhoneNumber"
        fun newIntentFromOtpScreen(context: Context,phone:String): Intent {
            val intent = Intent(context, OtpScreenActivity::class.java)
            intent.putExtra(EXTRA_PHONE_NUMBER,phone)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_screen)

        btnOtpScreenBack.setOnClickListener {
            finish()
        }

        onClickConfirmOtpButton()
    }

    override fun onRestart() {
        super.onRestart()
        finish()
    }

    private fun onClickConfirmOtpButton() {
        btnConfirmOtp.setOnClickListener {
            val otpCode = otpPinCode.text.toString()
            mOtpValidationChecker = OtpValidationChecker(otpCode)

            val phone = intent.getStringExtra(EXTRA_PHONE_NUMBER).toString()
            Log.i("Phone",phone)
            Log.i("Phone",otpCode)

            if (isValidatedOtpCode()) {
                if( otpCode == "123456" ) {
                    mMovieModel.signInWithPhoneNumber(
                        phone,
                        otpCode,
                        onSuccess = {
                            Toast.makeText(this,"Logic successful", Toast.LENGTH_SHORT).show()
                            startActivity(LocationScreenActivity.newIntentFromLocationScreen(this))
                        },
                        onFailure = {
                            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                        })
                } else {
                    Toast.makeText(this,"Wrong OTP !",Toast.LENGTH_SHORT).show()
                }

            } else {
                setUpInvalidationMessage()
            }
        }
    }

    private fun setUpInvalidationMessage() {
        val dialog = MaterialAlertDialogBuilder(this,R.style.RoundedAlertDialog)
            .setTitle("Invalid Code")
            .setMessage(mOtpValidationChecker.otpCodeError)
            .setCancelable(true)
            .setIcon(R.drawable.lock)
            .setPositiveButton("OK",null)
            .create()
        dialog.show()
    }

    private fun isValidatedOtpCode(): Boolean {
        return mOtpValidationChecker.isValidatedOtpCode()
    }

}