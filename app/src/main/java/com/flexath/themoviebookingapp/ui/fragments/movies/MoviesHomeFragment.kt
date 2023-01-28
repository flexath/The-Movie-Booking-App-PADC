package com.flexath.themoviebookingapp.ui.fragments.movies

import android.content.res.Resources
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
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
import java.lang.Math.abs

class MoviesHomeFragment : Fragment(), MenuProvider {

    private lateinit var mBannerHomeAdapter: BannerMoviesHomeAdapter
    private lateinit var mMoviesHomeAdapter: MoviesHomeViewPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return inflater.inflate(R.layout.fragment_movies_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).bottomNvgViewHome.visibility = View.VISIBLE

        setUpActionBar()            // For Action Bar of Home Screen
        setUpBannerHome()           // For Banner Section of Home Screen
        setUpViewPagerAdapter()     // For TabLayout Movies
    }

    private fun setUpViewPagerAdapter(){
        mMoviesHomeAdapter = MoviesHomeViewPagerAdapter(this)
        viewPagerTabLayoutMoviesHome.adapter = mMoviesHomeAdapter

        TabLayoutMediator(tabLayoutMoviesHome,viewPagerTabLayoutMoviesHome){ tab,position ->
            when(position){
                0 -> tab.text = "Now Showing"
                else -> tab.text ="Coming Soon"
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

    private fun setUpActionBar() {
        (activity as AppCompatActivity).setSupportActionBar(toolbarAppbarHome)
        (activity as AppCompatActivity).supportActionBar?.title = null
        tvCityNameExtraHome.text = (activity as AppCompatActivity).intent.getStringExtra(MainActivity.CITY_NAME_EXTRA)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.app_bar_icons_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.searchIconAppBar -> {
                Toast.makeText(requireContext(), "Search's clicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.bellIconAppBar -> {
                Toast.makeText(requireContext(), "Bell's clicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.scanIconAppBar -> {
                Toast.makeText(requireContext(), "Search clicked", Toast.LENGTH_SHORT).show()
                true
            }
            else -> false
        }
    }
}