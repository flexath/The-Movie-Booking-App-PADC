package com.flexath.themoviebookingapp.ui.adapters.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.vos.test.SnackVO
import com.flexath.themoviebookingapp.ui.viewholders.movies.OrderedFoodMoviesTicketCheckoutViewHolder

class OrderedFoodMoviesTicketCheckoutAdapter : RecyclerView.Adapter<OrderedFoodMoviesTicketCheckoutViewHolder>() {

    private var mSnackList:List<SnackVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderedFoodMoviesTicketCheckoutViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movies_ticket_checkout_food_orders_list,parent,false)
        return OrderedFoodMoviesTicketCheckoutViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderedFoodMoviesTicketCheckoutViewHolder, position: Int) {
        holder.bindData(mSnackList[position])
    }

    override fun getItemCount(): Int {
        return mSnackList.size
    }

    fun bindNewData(snackList: List<SnackVO>) {
        mSnackList = snackList
        notifyDataSetChanged()
    }
}