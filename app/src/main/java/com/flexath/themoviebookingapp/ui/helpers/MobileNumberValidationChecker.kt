package com.flexath.themoviebookingapp.ui.helpers

class MobileNumberValidationChecker(private var mobileNumber: String) {

    var mobileNumberError: String = ""

    fun isValidatedMobileNumber() : Boolean {
        return isNotEmptyMobileNumber() && isTenMobileNumbers()
    }

    private fun isNotEmptyMobileNumber(): Boolean {
        if (mobileNumber.isNotEmpty()) {
            return true
        } else {
            mobileNumberError = "Invalid! Phone number can't be empty"
            return false
        }
    }

    private fun isTenMobileNumbers(): Boolean {

        if (mobileNumber.length == 10) {
            if (mobileNumber.startsWith("9")) {
                return true
            } else {
                mobileNumberError = "Invalid! Starts with 9 or 09"
                return false
            }
        } else {
            mobileNumberError = "Invalid! must be 10 numbers starts with 9"
            return false
        }
    }
}