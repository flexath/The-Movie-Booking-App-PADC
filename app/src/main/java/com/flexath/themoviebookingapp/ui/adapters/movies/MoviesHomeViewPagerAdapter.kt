package com.flexath.themoviebookingapp.ui.adapters.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.flexath.themoviebookingapp.ui.fragments.movies.TabLayoutMoviesHomeFragment
import com.flexath.themoviebookingapp.ui.fragments.movies.TabLayoutMoviesHomeFragment.Companion.CINEMA_TIME_EXTRA_KEY

class MoviesHomeViewPagerAdapter(fragment:Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                val tabLayoutFragmentOne = TabLayoutMoviesHomeFragment()
                Bundle().apply {
                    putBoolean(CINEMA_TIME_EXTRA_KEY,false)
                    tabLayoutFragmentOne.arguments = this
                }
                tabLayoutFragmentOne
            }
            else -> {
                val tabLayoutFragmentTwo = TabLayoutMoviesHomeFragment()
                Bundle().apply {
                    putBoolean(CINEMA_TIME_EXTRA_KEY,true)
                    tabLayoutFragmentTwo.arguments = this
                }
                tabLayoutFragmentTwo
            }
        }
    }

}