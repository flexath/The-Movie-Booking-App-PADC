package com.flexath.themoviebookingapp.ui.viewholders.movies

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.data.vos.movie.SnackVO
import kotlinx.android.synthetic.main.view_holder_movies_ticket_checkout_food_orders_list.view.*

class SnackTicketCheckoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(snack: SnackVO) {
        itemView.tvSnackNameTicket.text = snack.name

        val snackQuantity = "(Qt.${snack.quantity})"
        itemView.tvSnackQuantityTicket.text = snackQuantity
        itemView.tvSnackPriceTicket.text = (snack.price?.times(snack.quantity)).toString()
    }
}