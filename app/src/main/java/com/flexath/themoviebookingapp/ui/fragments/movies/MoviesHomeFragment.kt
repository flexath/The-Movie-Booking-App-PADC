package com.flexath.themoviebookingapp.ui.fragments.movies

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.activities.MainActivity
import com.flexath.themoviebookingapp.ui.adapters.BannerHomeAdapter
import com.flexath.themoviebookingapp.ui.adapters.MoviesHomeViewPagerAdapter
import com.flexath.themoviebookingapp.ui.dummy.MoviesData
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_movies_home.*
import kotlinx.android.synthetic.main.layout_app_bar_home.*

class MoviesHomeFragment : Fragment(), MenuProvider {

    private lateinit var mBannerHomeAdapter:BannerHomeAdapter
    private lateinit var mMoviesHomeAdapter: MoviesHomeViewPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return inflater.inflate(R.layout.fragment_movies_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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


    private fun setUpBannerHome() {
        mBannerHomeAdapter = BannerHomeAdapter(MoviesData().advertisementImages)
        viewPagerBannerMoviesHome.adapter = mBannerHomeAdapter
        dotsIndicatorHome.attachTo(viewPagerBannerMoviesHome)
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