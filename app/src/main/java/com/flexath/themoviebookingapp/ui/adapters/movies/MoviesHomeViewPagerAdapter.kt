package com.flexath.themoviebookingapp.ui.adapters.movies

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.flexath.themoviebookingapp.data.model.CinemaModel
import com.flexath.themoviebookingapp.ui.fragments.movies.TabLayoutMoviesHomeFragment

class MoviesHomeViewPagerAdapter(private val fragment:Fragment,private val movieModel: CinemaModel) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return TabLayoutMoviesHomeFragment(position,movieModel,fragment)
    }

}