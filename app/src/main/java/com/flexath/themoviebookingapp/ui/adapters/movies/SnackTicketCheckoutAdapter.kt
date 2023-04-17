package com.flexath.themoviebookingapp.ui.adapters.movies

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.vos.movie.SnackVO
import com.flexath.themoviebookingapp.data.vos.movie.confirmation.TicketCheckoutSnackVO
import com.flexath.themoviebookingapp.ui.delegates.SnackTicketCheckoutViewHolderDelegate
import com.flexath.themoviebookingapp.ui.viewholders.movies.SnackTicketCheckoutViewHolder
import kotlinx.android.synthetic.main.view_holder_movies_ticket_checkout_food_orders_list.view.*

class SnackTicketCheckoutAdapter(private val delegate:SnackTicketCheckoutViewHolderDelegate) : RecyclerView.Adapter<SnackTicketCheckoutViewHolder>() {

    private var mSnackList: List<SnackVO> = listOf()
    private var mTicketCheckoutSnackList: List<TicketCheckoutSnackVO> = listOf()
    private lateinit var mType: String

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SnackTicketCheckoutViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movies_ticket_checkout_food_orders_list, parent, false)
        return SnackTicketCheckoutViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: SnackTicketCheckoutViewHolder, position: Int) {
        holder.bindData(mSnackList[position],mType)
    }

    override fun getItemCount(): Int {
        return mSnackList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun bindNewData(snackList: List<SnackVO>, type: String) {
        mSnackList = snackList
        Log.i("SnackListOne",mSnackList.toString())
        mType = type
        notifyDataSetChanged()
    }
}