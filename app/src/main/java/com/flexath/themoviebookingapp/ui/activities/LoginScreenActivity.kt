package com.flexath.themoviebookingapp.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.model.CinemaModel
import com.flexath.themoviebookingapp.data.model.CinemaModelImpl
import com.flexath.themoviebookingapp.ui.helpers.MobileNumberValidationChecker
import kotlinx.android.synthetic.main.activity_login_screen.*


class LoginScreenActivity : AppCompatActivity() {

    private val mMovieModel: CinemaModel = CinemaModelImpl

    private lateinit var mMobileNumberValidationChecker: MobileNumberValidationChecker

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
                ccpLogin.registerCarrierNumberEditText(etMobileNumber?.editText)
                mMovieModel.sendOTP(
                    ccpLogin.fullNumber,
                    onSuccess = {
                        Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                        startActivity(OtpScreenActivity.newIntentFromOtpScreen(this, ccpLogin.fullNumber))
                    },
                    onFailure = {
                        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                    }
                )
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

    private fun isValidatedMobileNumber(): Boolean {
        return mMobileNumberValidationChecker.isValidatedMobileNumber()
    }
}