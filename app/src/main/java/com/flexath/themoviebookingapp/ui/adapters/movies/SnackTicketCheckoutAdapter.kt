package com.flexath.themoviebookingapp.ui.adapters.movies

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.vos.movie.SnackVO
import com.flexath.themoviebookingapp.ui.viewholders.movies.SnackTicketCheckoutViewHolder

class SnackTicketCheckoutAdapter : RecyclerView.Adapter<SnackTicketCheckoutViewHolder>() {

    private var mSnackList:List<SnackVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnackTicketCheckoutViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movies_ticket_checkout_food_orders_list,parent,false)
        return SnackTicketCheckoutViewHolder(view)
    }

    override fun onBindViewHolder(holder: SnackTicketCheckoutViewHolder, position: Int) {
        holder.bindData(mSnackList[position])
    }

    override fun getItemCount(): Int {
        return mSnackList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun bindNewData(snackList: List<SnackVO>) {
        mSnackList = snackList
        Log.i("SnackSize",mSnackList.size.toString())
        notifyDataSetChanged()
    }
}