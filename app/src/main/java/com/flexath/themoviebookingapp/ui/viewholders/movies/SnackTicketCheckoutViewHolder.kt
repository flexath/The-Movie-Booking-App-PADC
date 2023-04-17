package com.flexath.themoviebookingapp.ui.viewholders.movies

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.data.vos.movie.SnackVO
import com.flexath.themoviebookingapp.data.vos.movie.confirmation.TicketCheckoutSnackVO
import com.flexath.themoviebookingapp.ui.delegates.SnackTicketCheckoutViewHolderDelegate
import kotlinx.android.synthetic.main.view_holder_movies_ticket_checkout_food_orders_list.view.*

class SnackTicketCheckoutViewHolder(itemView: View,private val delegate:SnackTicketCheckoutViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    fun bindData(snack: SnackVO,type:String) {
        itemView.tvSnackNameTicket.text = snack.name

        val snackQuantity = "(Qt.${snack.quantity})"
        val snackPrice = "${(snack.price?.times(snack.quantity))}Ks"
        itemView.tvSnackQuantityTicket.text = snackQuantity
        itemView.tvSnackPriceTicket.text = snackPrice

        if(type == "Checkout") {
            itemView.ivCancelSnack.setOnClickListener {
                delegate.onTapSnack(snack.id ?: 0)
            }
        } else {
            itemView.ivCancelSnack.visibility = View.GONE
        }
    }

    fun bindDataForCancellation(snack: TicketCheckoutSnackVO) {
        itemView.tvSnackNameTicket.text = snack.name

        val snackQuantity = "(Qt.${snack.quantity})"
        val snackPrice = "${(snack.price?.times(snack.quantity ?: 0) ?: 0)}Ks"
        itemView.tvSnackQuantityTicket.text = snackQuantity
        itemView.tvSnackPriceTicket.text = snackPrice
    }
}