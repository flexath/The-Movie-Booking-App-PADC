package com.flexath.themoviebookingapp.ui.generaldata

import java.util.*

class CinemaInfoFactory {
    val safetyList = listOf(
        "Thermal Scanning",
        "Contactless Security Check",
        "Sanitization Before Every Show",
        "Disposable 3D glass",
        "Contactless Food Service",
        "Package Food ",
        "Deep Cleaning of rest room"
    )

    fun pushToStack(list:List<String>) : Stack<String> {
        val st = Stack<String>()
        for(element in list) {
            st.push(element)
        }
        return st
    }

}