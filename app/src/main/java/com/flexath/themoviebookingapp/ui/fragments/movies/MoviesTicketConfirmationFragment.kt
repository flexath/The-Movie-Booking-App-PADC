package com.flexath.themoviebookingapp.ui.fragments.movies

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.flexath.themoviebookingapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movies_ticket_confirmation.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlin.coroutines.coroutineContext

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
            ivBookingSuccessMoviesTicketConfirmation.visibility = View.GONE
            Toast.makeText(requireContext(),"Done!",Toast.LENGTH_SHORT).show()
        },3000)
    }
}


//(activity as AppCompatActivity).supportFragmentManager
//.beginTransaction()
//.add(R.id.fragmentContainerView, MoviesDetailsFragment())
//.add(R.id.fragmentContainerView, MoviesCinemaFragment())
//.add(R.id.fragmentContainerView, MoviesSeatFragment())
//.add(R.id.fragmentContainerView, MoviesFoodFragment())
//.add(R.id.fragmentContainerView, MoviesTicketCheckoutFragment())
//.add(R.id.fragmentContainerView, MoviesTicketConfirmationFragment())
//.commit()
//
//(activity as AppCompatActivity).supportFragmentManager.apply {
//    for (i in 1 until fragments.size) {
//        beginTransaction().remove(fragments[i]).commit()
//    }
//    popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
//}