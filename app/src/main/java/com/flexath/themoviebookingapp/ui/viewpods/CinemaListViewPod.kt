package com.flexath.themoviebookingapp.ui.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexath.themoviebookingapp.data.vos.movie.cinema.CinemaVO
import com.flexath.themoviebookingapp.ui.adapters.movies.CinemasMoviesCinemaAdapter
import com.flexath.themoviebookingapp.ui.delegates.CinemaListViewHolderDelegate
import kotlinx.android.synthetic.main.view_pod_cinema_list.view.*

class CinemaListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private lateinit var mCinemaListAdapter: CinemasMoviesCinemaAdapter
    private lateinit var mDelegate: CinemaListViewHolderDelegate

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setUpMovieListViewPod(delegate: CinemaListViewHolderDelegate) {
        this.mDelegate = delegate
        setUpCinemaListRecyclerView()
    }

    fun setNewData(cinemaList:List<CinemaVO>) {
        mCinemaListAdapter.setData(cinemaList)
    }

    private fun setUpCinemaListRecyclerView() {
        mCinemaListAdapter = CinemasMoviesCinemaAdapter(mDelegate)
        rvCinemaListViewPod.adapter = mCinemaListAdapter
        val linearLayoutManager = LinearLayoutManager(context)
        rvCinemaListViewPod.layoutManager = linearLayoutManager

        val divider = DividerItemDecoration(context.applicationContext, linearLayoutManager.orientation)
        rvCinemaListViewPod.addItemDecoration(divider)
        mCinemaListAdapter.notifyDataSetChanged()
    }
}