package com.flexath.themoviebookingapp.ui.fragments.movies

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.flexath.themoviebookingapp.R
import kotlinx.android.synthetic.main.fragment_movies_ticket_confirmation.*

class MoviesTicketConfirmationFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_ticket_confirmation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpListeners()
    }

    private fun setUpListeners() {
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            ivBookingSuccessMoviesTicketConfirmation?.visibility = View.INVISIBLE
            if(ivBookingSuccessMoviesTicketConfirmation != null) {
                Toast.makeText(requireContext(),"Done!",Toast.LENGTH_SHORT).show()
            }
        },3000)

        btnDoneMoviesTicketConfirmation.setOnClickListener {
            val navController = Navigation.findNavController(it)
            navController.popBackStack(R.id.moviesHomeFragment, true)
            navController.navigate(R.id.moviesHomeFragment)
        }
    }
}


//val activity = (activity as AppCompatActivity)
//
//val fragmentManager = activity.supportFragmentManager
//val fragments = fragmentManager.fragments

//            val moviesHome = MoviesHomeFragment()
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.add(R.id.fragmentContainerView, moviesHome)
//            fragmentTransaction.commit()
//
//            for (f in fragments) {
//                if (f != moviesHome) {
//                    fragmentManager.beginTransaction().remove(f).commit()
//                }
//            }