package com.flexath.themoviebookingapp.ui.viewholders.movies

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.flexath.themoviebookingapp.data.vos.movie.PaymentVO
import com.flexath.themoviebookingapp.ui.delegates.PaymentViewHolderDelegate
import com.flexath.themoviebookingapp.ui.fragments.movies.MoviesPaymentFragmentDirections
import kotlinx.android.synthetic.main.view_holder_movies_payment_list.view.*

class MoviesPaymentViewHolder(itemView: View,delegate: PaymentViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mPayment:PaymentVO? = null

    init {
        itemView.setOnClickListener {
            delegate.onTapPayment(mPayment?.id ?: 0)
        }
    }

    fun bindData(payment: PaymentVO) {
        mPayment = payment
        Glide.with(itemView.context)
            .load(payment.icon)
            .into(itemView.ivPaymentIcon)

        itemView.tvPaymentType.text = payment.title
    }
}