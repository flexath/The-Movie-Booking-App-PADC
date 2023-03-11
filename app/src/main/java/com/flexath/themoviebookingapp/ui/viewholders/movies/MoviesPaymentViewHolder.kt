package com.flexath.themoviebookingapp.ui.viewholders.movies

import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.flexath.themoviebookingapp.data.vos.test.PaymentVO
import com.flexath.themoviebookingapp.ui.fragments.movies.MoviesPaymentFragmentDirections
import kotlinx.android.synthetic.main.view_holder_movies_payment_list.view.*

class MoviesPaymentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            val action = MoviesPaymentFragmentDirections.actionProfilePaymentToMoviesTicketConfirmation()
            it.findNavController().navigate(action)
        }
    }

    fun bindData(payment: PaymentVO) {
        Glide.with(itemView.context)
            .load(payment.icon)
            .into(itemView.ivPaymentIcon)

        itemView.tvPaymentType.text = payment.title
    }
}