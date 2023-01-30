package com.flexath.themoviebookingapp.ui.fragments.cinema

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.generaldata.CinemaInfoFactory
import kotlinx.android.synthetic.main.fragment_cinema_info.*
import java.util.*

class CinemaInfoFragment : Fragment() {

    private val safetyList = CinemaInfoFactory().safetyList
    private var isCinemaVideoPlaying:Boolean = false

    private lateinit var linearLayoutOuter:LinearLayout
    private lateinit var stack: Stack<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cinema_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearLayoutOuter = (activity as AppCompatActivity).findViewById(R.id.llOuterSafetyCinemaInfo)

        playCinemaVideo()
        setSafetyLinearLayout()
    }

    private fun playCinemaVideo() {
        btnPlayCinemaInfo.setOnClickListener {
            setUpVideoView()
            if(!isCinemaVideoPlaying) {
                btnPlayCinemaInfo.setImageResource(R.drawable.ic_baseline_pause_white_22dp)
                vvVideoCinemaInfo.start()
                isCinemaVideoPlaying = true
            }else {
                btnPlayCinemaInfo.setImageResource(R.drawable.ic_baseline_play_arrow_white_22dp)
                vvVideoCinemaInfo.pause()
                isCinemaVideoPlaying = false
            }
        }
    }

    private fun setUpVideoView() {
        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(vvVideoCinemaInfo)

        val videoUri = Uri.parse("android.resource://com.flexath.themoviebookingapp/${R.raw.black_widow_trailer}")
        vvVideoCinemaInfo.setMediaController(null)
        vvVideoCinemaInfo.setVideoURI(videoUri)
        vvVideoCinemaInfo.requestFocus()
    }

    private fun setSafetyLinearLayout() {
        val safetyFactory = CinemaInfoFactory()
        stack = safetyFactory.pushToStack(safetyList)

        val row: Int = if(safetyList.size%2 == 1) {
            (safetyList.size/2)+1
        }else {
            safetyList.size/2
        }

        for(i in 0 until row) {
            linearLayoutOuter.addView(setTextViewStyle())
        }

    }

    private fun setTextViewStyle() : LinearLayout {
        val linearLayoutInner = LinearLayout(requireContext())
        linearLayoutInner.orientation = LinearLayout.HORIZONTAL

        val innerLinearLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        innerLinearLayoutParams.setMargins(0,0,0,20)
        linearLayoutInner.layoutParams = innerLinearLayoutParams

        for(i in stack.indices){
            if(i == 2){
                stack.removeAt(0)
                stack.removeAt(0)
                break
            }
            val textView = AppCompatTextView(requireContext())
            val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(0,0,15,0)
            textView.layoutParams =layoutParams
            textView.setPadding(20,12,20,12)
            textView.background = ContextCompat.getDrawable(requireContext(),R.drawable.safety_text_background)
            textView.text = stack[i]
            textView.textSize = 14f
            textView.setTextColor(Color.parseColor("#111111"))
            textView.gravity = Gravity.CENTER
            textView.typeface = ResourcesCompat.getFont(requireContext(),R.font.inter_medium)
            linearLayoutInner.addView(textView)
        }
        return linearLayoutInner
    }

}