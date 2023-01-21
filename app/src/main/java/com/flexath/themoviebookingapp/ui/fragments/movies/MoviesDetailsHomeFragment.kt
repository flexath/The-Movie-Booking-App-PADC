package com.flexath.themoviebookingapp.ui.fragments.movies

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.adapters.DetailsMoviesCastAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movies_details_home.*

class MoviesDetailsHomeFragment : Fragment() {

    private lateinit var mCastAdapter:DetailsMoviesCastAdapter
    private var isTrailerVideoPlaying:Boolean = false
    private val args:MoviesDetailsHomeFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_details_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).bottomNvgViewHome.visibility = View.INVISIBLE

        btnListenersMoviesDetailsScreen()     // Button Listeners Methods

        setUpCastRecyclerView()              // For Cast
        playOrPauseTrailer()                 // For Trailer Playing or Not
        isNowShowingOrIsComingSoon()         // Now Showing or Coming Soon
    }

    private fun btnListenersMoviesDetailsScreen(){
        btnBackMoviesDetails.setOnClickListener {
            (activity as AppCompatActivity).onBackPressed()
        }

        btnBookingButtonMoviesDetails.setOnClickListener {
            val action = MoviesDetailsHomeFragmentDirections.actionMoviesDetailsHomeToChooseCinema()
            it.findNavController().navigate(action)
        }
    }

    private fun isNowShowingOrIsComingSoon(){
        if(!args.nowShowingOrComingSoonArg){                                    // For Now Showing
            rlReleasingDateMoviesDetails.visibility = View.GONE
            btnBookingButtonMoviesDetails.visibility = View.VISIBLE
        }else{                                                                  // For Coming Soon
            rlReleasingDateMoviesDetails.visibility = View.VISIBLE
            btnBookingButtonMoviesDetails.visibility = View.GONE
        }
    }

    private fun playOrPauseTrailer(){
        btnPlayMoviesDetails.setOnClickListener {
            setUpMovieTrailerVideo()
            if(!isTrailerVideoPlaying){
                btnPlayMoviesDetails.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_baseline_pause_white_22dp))
                vvVideoMovieDetails.start()
                isTrailerVideoPlaying = true
            }else{
                btnPlayMoviesDetails.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_baseline_play_arrow_white_22dp))
                vvVideoMovieDetails.pause()
                isTrailerVideoPlaying = false
            }
        }
    }

    private fun setUpMovieTrailerVideo(){
        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(vvVideoMovieDetails)

        val videoUri = Uri.parse("android.resource://com.flexath.themoviebookingapp/${R.raw.black_widow_video}")
        vvVideoMovieDetails.setMediaController(mediaController)
        vvVideoMovieDetails.setVideoURI(videoUri)
        vvVideoMovieDetails.requestFocus()
    }

    private fun setUpCastRecyclerView() {
        mCastAdapter = DetailsMoviesCastAdapter()
        rvCastMoviesDetails.adapter = mCastAdapter
        rvCastMoviesDetails.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
    }
}