package com.flexath.themoviebookingapp.ui.helpers

class OtpValidationChecker(private var otpCode:String) {

    var otpCodeError:String = ""

    fun isValidatedOtpCode() : Boolean {
        return isNotEmptyOtpCode() && isSixNumbers()
    }

    private fun isNotEmptyOtpCode() : Boolean {
        return if(otpCode.isNotEmpty()) {
            true
        } else {
            otpCodeError = "Invalid! OTP can't be empty"
            false
        }
    }

    private fun isSixNumbers() : Boolean {
        return if(otpCode.length == 6){
            true
        } else {
            otpCodeError = "Invalid! Number of OTP must be six"
            false
        }
    }

}