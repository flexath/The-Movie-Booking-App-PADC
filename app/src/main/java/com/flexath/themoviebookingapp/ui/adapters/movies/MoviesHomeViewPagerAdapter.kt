package com.flexath.themoviebookingapp.ui.adapters.movies

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.flexath.themoviebookingapp.ui.fragments.movies.TabLayoutMoviesHomeFragment

class MoviesHomeViewPagerAdapter(fragment:Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return TabLayoutMoviesHomeFragment(position)
    }

}