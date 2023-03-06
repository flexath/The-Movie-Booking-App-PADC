package com.flexath.themoviebookingapp.ui.adapters.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.viewholders.movies.MoviesPaymentViewHolder
import kotlinx.android.synthetic.main.view_holder_movies_payment_list.view.*

class MoviesPaymentAdapter : RecyclerView.Adapter<MoviesPaymentViewHolder>() {

    private val paymentList = listOf(R.drawable.button_upi,R.drawable.button_gift_voucher,R.drawable.button_quick_pay
    ,R.drawable.button_credit_card,R.drawable.button_redeem,R.drawable.button_mobile_wallet,R.drawable.button_net_banking)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesPaymentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movies_payment_list,parent,false)
        return MoviesPaymentViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesPaymentViewHolder, position: Int) {
        val paymentMethod = paymentList[position]
        holder.itemView.ivPaymentMovies.setImageResource(paymentMethod)
    }

    override fun getItemCount(): Int {
        return paymentList.count()
    }
}