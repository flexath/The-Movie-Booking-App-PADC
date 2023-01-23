package com.flexath.themoviebookingapp.ui.fragments.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flexath.themoviebookingapp.R
import kotlinx.android.synthetic.main.fragment_tab_layout_movies_food.*

class TabLayoutMoviesFoodFragment(foodIndex:Int) : Fragment() {

    private var foodType = foodIndex

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab_layout_movies_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        food.text = foodType.toString()
    }
}