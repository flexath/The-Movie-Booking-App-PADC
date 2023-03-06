package com.flexath.themoviebookingapp.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.flexath.themoviebookingapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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

        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        bottomNvgViewHome.setupWithNavController(navHost.navController)
    }
}