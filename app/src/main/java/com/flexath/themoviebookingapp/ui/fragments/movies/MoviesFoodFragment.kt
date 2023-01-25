package com.flexath.themoviebookingapp.ui.fragments.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.adapters.movies.MoviesFoodViewPagerAdapter
import com.flexath.themoviebookingapp.ui.generaldata.FoodFactory
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_movies_food.*
import kotlinx.android.synthetic.main.layout_app_bar_movies_food.*

class MoviesFoodFragment : Fragment() {

    private lateinit var mMoviesFoodAdapter:MoviesFoodViewPagerAdapter
    private val foodList = FoodFactory()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpFoodTabLayout()                // For Food Types Tab
        setUpListeners()
    }

    private fun setUpListeners() {
        // Order Button for Check Out Screen
        btnFoodOrderPurchaseMoviesFood.setOnClickListener {
            val action = MoviesFoodFragmentDirections.actionMoviesFoodToMoviesTicketCheckout()
            it.findNavController().navigate(action)
        }

        btnSkipButtonMoviesFood.setOnClickListener {
            val action = MoviesFoodFragmentDirections.actionMoviesFoodToMoviesTicketCheckout()
            it.findNavController().navigate(action)
        }

    }

    private fun setUpFoodTabLayout() {
        mMoviesFoodAdapter = MoviesFoodViewPagerAdapter(this,foodList)
        viewPagerTabLayoutMoviesFood.adapter = mMoviesFoodAdapter

        TabLayoutMediator(tabLayoutMoviesFood,viewPagerTabLayoutMoviesFood) { tab,position ->
            for(i in foodList.foodTitleList.indices) {
                if(position == i){
                    tab.text = foodList.foodTitleList[i]
                }
            }
        }.attach()
    }
}