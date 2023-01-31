package com.flexath.themoviebookingapp.ui.fragments.movies

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.activities.MainActivity
import com.flexath.themoviebookingapp.ui.adapters.movies.BannerMoviesHomeAdapter
import com.flexath.themoviebookingapp.ui.adapters.movies.MoviesHomeViewPagerAdapter
import com.flexath.themoviebookingapp.ui.dummy.MoviesData
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movies_home.*
import kotlinx.android.synthetic.main.layout_app_bar_movies_home.*

class MoviesHomeFragment : Fragment() {

    private lateinit var mBannerHomeAdapter: BannerMoviesHomeAdapter
    private lateinit var mMoviesHomeAdapter: MoviesHomeViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).bottomNvgViewHome.visibility = View.VISIBLE

        setUpAppBarListeners()
        setUpBannerHome()           // For Banner Section of Home Screen
        setUpViewPagerAdapter()     // For TabLayout Movies
    }

    private fun setUpAppBarListeners() {
        tvCityNameMoviesHome.text = (activity as AppCompatActivity).intent.getStringExtra(MainActivity.CITY_NAME_EXTRA)

        btnSearchMoviesHome.setOnClickListener {
            val action = MoviesHomeFragmentDirections.actionMoviesHomeToMoviesSearch()
            action.argTabPosition = viewPagerTabLayoutMoviesHome.currentItem
            it.findNavController().navigate(action)
        }
    }

    private fun setUpViewPagerAdapter() {
        mMoviesHomeAdapter = MoviesHomeViewPagerAdapter(this)
        viewPagerTabLayoutMoviesHome.adapter = mMoviesHomeAdapter

        TabLayoutMediator(tabLayoutMoviesHome, viewPagerTabLayoutMoviesHome) { tab, position ->
            when (position) {
                0 -> tab.text = "Now Showing"
                else -> tab.text = "Coming Soon"
            }
        }.attach()
    }

    private fun setUpBannerViewPagerPadding() {
        viewPagerBannerMoviesHome.apply {
            clipChildren = false  // No clipping the left and right items
            clipToPadding = false  // Show the viewpager in full width without clipping the padding
            offscreenPageLimit = 3  // Render the left and right items
            (getChildAt(0) as RecyclerView).overScrollMode =
                RecyclerView.OVER_SCROLL_NEVER // Remove the scroll effect
        }
    }

    private fun setUpBannerHome() {
        setUpBannerViewPagerPadding()
        mBannerHomeAdapter = BannerMoviesHomeAdapter(MoviesData().advertisementImages)
        viewPagerBannerMoviesHome.adapter = mBannerHomeAdapter
        dotsIndicatorHome.attachTo(viewPagerBannerMoviesHome)
        mBannerHomeAdapter.notifyDataSetChanged()

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((10 * Resources.getSystem().displayMetrics.density).toInt()))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - kotlin.math.abs(position)
            page.scaleY = (0.80f + r * 0.20f)
        }
        viewPagerBannerMoviesHome.setPageTransformer(compositePageTransformer)
    }
}