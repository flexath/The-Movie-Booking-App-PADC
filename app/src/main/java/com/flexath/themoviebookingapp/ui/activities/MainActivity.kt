package com.flexath.themoviebookingapp.ui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.adapters.BannerHomeAdapter
import com.flexath.themoviebookingapp.ui.dummy.BannerImagesData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_app_bar_home.*

class MainActivity : AppCompatActivity() {

//    private lateinit var mBannerHomeAdapter:BannerHomeAdapter

    companion object{
        const val CITY_NAME_EXTRA = "CITY"

        fun newIntentFromMainActivity(context: Context, message:String) : Intent {
            val intent = Intent(context,MainActivity::class.java)
            intent.putExtra(CITY_NAME_EXTRA,message)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setUpAppBarIcons()
        //setUpBannerHome()
    }

//    private fun setUpBannerHome(){
//        mBannerHomeAdapter = BannerHomeAdapter(BannerImagesData().images)
//        viewPagerBannerHome.adapter =mBannerHomeAdapter
//        dotsIndicatorHome.attachTo(viewPagerBannerHome)
//    }

//    private fun setUpAppBarIcons(){
//        setSupportActionBar(toolbarAppbarHome)
//        supportActionBar?.title = null
//        tvCityNameExtraHome.text = intent?.getStringExtra(CITY_NAME_EXTRA).toString()
//    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.app_bar_icons_menu,menu)
//        return true
//    }
}