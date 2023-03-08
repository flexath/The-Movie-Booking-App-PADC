package com.flexath.themoviebookingapp.ui.fragments.cinema

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.model.CinemaModel
import com.flexath.themoviebookingapp.data.model.CinemaModelImpl
import com.flexath.themoviebookingapp.ui.generaldata.CinemaInfoFactory
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.fragment_cinema_info.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import java.util.*

class CinemaInfoFragment : Fragment() {

    private val safetyList = CinemaInfoFactory().safetyList
    private var isCinemaVideoPlaying:Boolean = false
    private val mCinemaModel:CinemaModel = CinemaModelImpl

    private lateinit var outerLinearLayout: LinearLayout
    private lateinit var facilityChipGroup:ChipGroup
    private lateinit var stack: Stack<String>
    private val args:CinemaInfoFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cinema_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        outerLinearLayout = (activity as AppCompatActivity).findViewById(R.id.llOuterSafetyCinemaInfo)
        facilityChipGroup = (activity as AppCompatActivity).findViewById(R.id.chipGroupFacilityCinemaInfo)

        bindCinemaInfoData()
    }

    private fun bindCinemaInfoData(){
        mCinemaModel.getCinemaInfo(args.argCinemaId)?.also {
            tvCinemaNameCinemaInfo.text = it.name
            tvLocationCinemaInfo.text = it.address
            it.promoVdoUrl?.let { url ->
                playCinemaVideo(url)
            }

            it.safety?.forEach { safety ->
                safetyList.add(safety)
            }
            setSafetyLinearLayout()

            it.facilities?.forEach { facility ->
                facilityChipGroup.addView(createDynamicChipButton(facility.title,facility.img))
            }
        }
    }

    private fun playCinemaVideo(urlString: String) {
        setUpVideoView(urlString)
        vvVideoCinemaInfo.start()
        if(!isCinemaVideoPlaying) {
//            btnPlayCinemaInfo.setImageResource(R.drawable.ic_baseline_pause_white_22dp)
            vvVideoCinemaInfo.resume()
            isCinemaVideoPlaying = true
        }else {
//            btnPlayCinemaInfo.setImageResource(R.drawable.ic_baseline_play_arrow_white_22dp)
            vvVideoCinemaInfo.pause()
            isCinemaVideoPlaying = false
        }
    }

    private fun setUpVideoView(urlString:String) {
        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(vvVideoCinemaInfo)

        val videoUri = Uri.parse(urlString)
        vvVideoCinemaInfo.setMediaController(mediaController)
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
            outerLinearLayout.addView(setTextViewStyle())
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

    private fun createDynamicChipButton(chipText:String?,imageUrl:String?) : Chip {
        val chip = Chip(requireContext())
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(0, 0, 15, 0)
        chip.layoutParams = layoutParams
        chip.gravity = Gravity.CENTER
        chip.textSize = 14.0f
        chip.typeface = ResourcesCompat.getFont(requireContext(), R.font.inter_medium)
        chip.text = chipText
        chip.setTextColor(Color.parseColor("#00FF6A"))
        chip.setChipBackgroundColorResource(R.color.colorPrimary)

        lifecycleScope.launch {
            val bitmap = imageUrl?.let { downloadImage(it) }
            val drawable = BitmapDrawable(resources, bitmap)
            chip.chipIcon = drawable
        }
        return chip
    }

    private suspend fun downloadImage(url: String): Bitmap? = withContext(Dispatchers.IO) {
        try {
            val bitmap = BitmapFactory.decodeStream(URL(url).openConnection().getInputStream())
            bitmap
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}