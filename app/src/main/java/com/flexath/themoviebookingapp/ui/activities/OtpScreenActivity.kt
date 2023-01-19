package com.flexath.themoviebookingapp.ui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.helpers.OtpValidationChecker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_otp_screen.*

class OtpScreenActivity : AppCompatActivity() {

    private lateinit var mOtpValidationChecker: OtpValidationChecker

    companion object {
        fun newIntentFromOtpScreen(context: Context): Intent {
            return Intent(context, OtpScreenActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_screen)

        btnOtpScreenBack.setOnClickListener {
            onBackPressed()
        }

        onClickConfirmOtpButton()

    }

    private fun onClickConfirmOtpButton() {
        btnConfirmOtp.setOnClickListener {
            val otpCode = otpPinCode.text.toString()
            mOtpValidationChecker = OtpValidationChecker(otpCode)

            if (isValidatedOtpCode()) {
                Toast.makeText(this,"Logic successful", Toast.LENGTH_SHORT).show()
                startActivity(LocationScreenActivity.newIntentFromLocationScreen(this))
                finish()
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