package com.flexath.themoviebookingapp.ui.generaldata

import java.util.*

class CinemaInfoFactory {
    val safetyList = mutableListOf<String>()

    fun pushToStack(list:List<String>) : Stack<String> {
        val st = Stack<String>()
        for(element in list) {
            st.push(element)
        }
        return st
    }

}