package com.flexath.themoviebookingapp.ui.adapters.movies

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.flexath.themoviebookingapp.ui.fragments.movies.TabLayoutMoviesFoodFragment
import com.flexath.themoviebookingapp.ui.generaldata.FoodFactory

class MoviesFoodViewPagerAdapter(fragment: Fragment,private val foodList:FoodFactory) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return foodList.foodTitleList.size
    }

    override fun createFragment(position: Int): Fragment {
        return TabLayoutMoviesFoodFragment(position)
    }
}