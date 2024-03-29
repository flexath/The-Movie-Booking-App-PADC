package com.flexath.themoviebookingapp.ui.fragments.movies

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.bumptech.glide.Glide
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.model.CinemaModel
import com.flexath.themoviebookingapp.data.model.CinemaModelImpl
import com.flexath.themoviebookingapp.data.vos.movie.CastVO
import com.flexath.themoviebookingapp.data.vos.movie.MovieVO
import com.flexath.themoviebookingapp.network.utils.IMG_BASE_URL
import com.flexath.themoviebookingapp.service.NotificationWorkManager
import com.flexath.themoviebookingapp.ui.adapters.movies.CastMoviesDetailsAdapter
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movies_details.*


class MoviesDetailsFragment : Fragment() {

    private lateinit var mCastAdapter: CastMoviesDetailsAdapter
    private var isTrailerVideoPlaying:Boolean = false
    private val args:MoviesDetailsFragmentArgs by navArgs()

    private var movieId:Int = 0
    private var mMovieName:String? = null

    private var mMovieModel: CinemaModel = CinemaModelImpl

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).bottomNvgViewHome.visibility = View.INVISIBLE

        movieId = args.argMovieId

        setUpListeners()     // Button Listeners Methods

        //playOrPauseTrailer()                 // For Trailer Playing or Not
        isNowShowingOrIsComingSoon()         // Now Showing or Coming Soon
        requestNewData()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun requestNewData() {
        mMovieModel.getMovieDetailsById(
            movieId.toString(),
            onSuccess = {
                mMovieName = it.originalTitle
                bindNewData(it)
            },
            onFailure = {
                Toast.makeText(requireActivity(),it,Toast.LENGTH_SHORT).show()
            }
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun bindNewData(movie: MovieVO?) {
        Glide.with(requireActivity())
            .load("$IMG_BASE_URL${movie?.posterPath}")
            .into(ivPosterMoviesDetail)

        tvTitleMovieDetails.text = movie?.originalTitle ?: ""
        tvRatingMovieDetails.text = movie?.formatVoteAverage()
        tvReleasingDateMoviesDetails.text = movie?.getMovieReleasingDayForNotification()
        tvOverviewMoviesDetails.text = movie?.overview  ?: ""
        tvDurationMovieDetails.text = movie?.changeRunTimeMinToHour()
        tvReleaseDateMovieDetails.text = movie?.changeReleaseDateFormat("details")
        movie?.casts?.let {
            setUpCastRecyclerView(it)               // For Casts
        }
        if (movie != null) {
            bindGenre(movie)
        }

        requestVideoURL(movie?.id.toString())
    }

    private fun requestVideoURL(movieId:String) {

        mMovieModel.getMovieTrailerById(
            movieId,
            onSuccess = {
                var youtubeKey = ""
                for(video in it){
                    if(video.site == "YouTube" && video.type == "Trailer" && video.official == true) {
                        youtubeKey = video.key.toString()
                        break
                    }
                }
                setUpYoutubePlayer(youtubeKey)
            },
            onFailure = {
                Toast.makeText(requireActivity(),"Video call fails",Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun setUpYoutubePlayer(youtubeKey:String) {
//        viewLifecycleOwner.lifecycle.addObserver(ytPlayerMovieDetails)
//        ytPlayerMovieDetails.getYouTubePlayerWhenReady(object: YouTubePlayerCallback {
//            override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
//                youTubePlayer.cueVideo(youtubeKey,0.0f)
//                //playOrPauseTrailer(youTubePlayer,youtubeKey)
//            }
//        })

        // For Non-Controls
        val listener: YouTubePlayerListener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.cueVideo(youtubeKey,0.0f)
//                playOrPauseTrailer(youTubePlayer)

                ytPlayerMovieDetails.setOnClickListener {
                    if(!isTrailerVideoPlaying) {
                        youTubePlayer.play()
                        isTrailerVideoPlaying = true
                    }else{
                        youTubePlayer.pause()
                        isTrailerVideoPlaying = false
                    }
                }
            }
        }
        val options: IFramePlayerOptions = IFramePlayerOptions.Builder().controls(0).build()
        ytPlayerMovieDetails.initialize(listener, options)      // initialize the player
    }

    override fun onDestroyView() {
        super.onDestroyView()
        ytPlayerMovieDetails.release()
    }

    private fun playOrPauseTrailer(youtubePlayer:YouTubePlayer){
        btnPlayMoviesDetails.setOnClickListener {
            if(!isTrailerVideoPlaying){
                //btnPlayMoviesDetails.setImageResource(R.drawable.ic_baseline_pause_white_22dp)
                youtubePlayer.play()
                isTrailerVideoPlaying = true
                btnPlayMoviesDetails.visibility = View.GONE
            }else{
                btnPlayMoviesDetails.visibility = View.VISIBLE
                btnPlayMoviesDetails.setImageResource(R.drawable.ic_baseline_play_arrow_white_22dp)
                youtubePlayer.pause()
                isTrailerVideoPlaying = false
            }
        }
    }

    private fun getNotificationForReleaseDate() {

        val constraints = Constraints.Builder()
            .setRequiresStorageNotLow(true)
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.NOT_ROAMING)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<NotificationWorkManager>()
            .setConstraints(constraints)
            .build()

//        val workRequest = PeriodicWorkRequestBuilder<NotificationWorkManager>(20, TimeUnit.MINUTES)
//            .setConstraints(constraints)
//            .build()

        WorkManager.getInstance(requireActivity()).enqueue(workRequest)
    }

    private fun bindGenre(movie: MovieVO) {
        movie.genres?.count()?.let {
            chipGenreOne.text = movie.genres.firstOrNull() ?: ""
            chipGenreTwo.text = movie.genres.getOrNull(1) ?: ""
            chipGenreThree.text = movie.genres.getOrNull(2) ?: ""
            chipGenreFour.text = movie.genres.getOrNull(3) ?: ""
            chipGenreFive.text = movie.genres.getOrNull(4) ?: ""

            when(it) {
                0 -> {
                    chipGenreOne.visibility = View.GONE
                    chipGenreTwo.visibility = View.GONE
                    chipGenreThree.visibility = View.GONE
                    chipGenreFour.visibility = View.GONE
                    chipGenreFive.visibility = View.GONE
                }
                1 -> {
                    chipGenreTwo.visibility = View.GONE
                    chipGenreThree.visibility = View.GONE
                    chipGenreFour.visibility = View.GONE
                    chipGenreFive.visibility = View.GONE
                }
                2 -> {
                    chipGenreThree.visibility = View.GONE
                    chipGenreFour.visibility = View.GONE
                    chipGenreFive.visibility = View.GONE
                }
                3 -> {
                    chipGenreFour.visibility = View.GONE
                    chipGenreFive.visibility = View.GONE
                }
                4 -> {
                    chipGenreFive.visibility = View.GONE
                }
            }
        }
    }

    private fun setUpListeners(){
        btnBackMoviesDetails.setOnClickListener {
            ytPlayerMovieDetails.release()
            it.findNavController().popBackStack()
        }

        btnBookingButtonMoviesDetails.setOnClickListener {
            val action = MoviesDetailsFragmentDirections.actionMoviesDetailsHomeToChooseCinema()
            action.argMovieName = mMovieName
            action.argMovieId = movieId.toString()
            action.argCityName = args.argCityName
            it.findNavController().navigate(action)
        }

        btnSetNotificationMovieDetails.setOnClickListener {
            getNotificationForReleaseDate()
        }
    }

    private fun isNowShowingOrIsComingSoon(){
        if(args.argNowShowingOrComingSoon == 0){                                    // For Now Showing
            rlReleasingDateMoviesDetails.visibility = View.GONE
            btnBookingButtonMoviesDetails.visibility = View.VISIBLE
        }else{                                                                  // For Coming Soon
            rlReleasingDateMoviesDetails.visibility = View.VISIBLE
            btnBookingButtonMoviesDetails.visibility = View.GONE
        }
    }
//
//    private fun setUpMovieTrailerVideo(){
//        val mediaController = MediaController(requireActivity())
//        mediaController.setAnchorView(vvVideoMovieDetails)
//
//        val videoUri = Uri.parse("android.resource://com.flexath.themoviebookingapp/${R.raw.black_widow_trailer}")
//        vvVideoMovieDetails.setMediaController(null)
//        vvVideoMovieDetails.setVideoURI(videoUri)
//        vvVideoMovieDetails.requestFocus()
//    }

    private fun setUpCastRecyclerView(casts:List<CastVO>) {
        mCastAdapter = CastMoviesDetailsAdapter()
        rvCastMoviesDetails.adapter = mCastAdapter
        rvCastMoviesDetails.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        mCastAdapter.bindCastData(casts)
        mCastAdapter.notifyDataSetChanged()
    }
}