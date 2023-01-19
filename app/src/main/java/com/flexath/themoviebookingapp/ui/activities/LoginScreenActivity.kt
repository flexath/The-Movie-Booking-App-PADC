package com.flexath.themoviebookingapp.ui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.helpers.MobileNumberValidationChecker
import kotlinx.android.synthetic.main.activity_login_screen.*

class LoginScreenActivity : AppCompatActivity() {


    private lateinit var mMobileNumberValidationChecker:MobileNumberValidationChecker

    companion object {
        fun newIntentFromLoginScreen(context: Context): Intent {
            return Intent(context, LoginScreenActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        onClickVerifyPhoneNumberButton()
    }

    private fun onClickVerifyPhoneNumberButton() {
        rlVerifyPhoneNumber.setOnClickListener {
            val mobileNumber = etMobileNumber.editText?.text.toString()
            mMobileNumberValidationChecker = MobileNumberValidationChecker(mobileNumber)

            if(isValidatedMobileNumber()){
                etMobileNumber.error = null
                etMobileNumber.isErrorEnabled = false
                startActivity(OtpScreenActivity.newIntentFromOtpScreen(this))
            }else{
                etMobileNumber.error = mMobileNumberValidationChecker.mobileNumberError
                outlineMobileNumber.visibility = View.INVISIBLE
            }
        }

        rlVerifyGoogleAccount.setOnClickListener {
            Intent(Intent.ACTION_VIEW).also {
                startActivity(it)
            }
        }
    }

    private fun isValidatedMobileNumber() : Boolean {
        return mMobileNumberValidationChecker.isValidatedMobileNumber()
    }
}