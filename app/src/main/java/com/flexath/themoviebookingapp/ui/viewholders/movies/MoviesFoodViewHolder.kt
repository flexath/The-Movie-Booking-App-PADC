package com.flexath.themoviebookingapp.ui.viewholders.movies

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.flexath.themoviebookingapp.data.vos.test.SnackVO
import com.flexath.themoviebookingapp.ui.delegates.SnackViewHolderDelegate
import kotlinx.android.synthetic.main.view_holder_movies_food_foods_list.view.*

class MoviesFoodViewHolder(itemView: View,private val delegate:SnackViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    private var count:Int = 0
    private var mSnackVO:SnackVO? = null

    init {
        setUpAddButtonListeners()
    }

    private fun setUpAddButtonListeners() {
        itemView.btnAddMoviesFood.setOnClickListener {
            itemView.btnAddMoviesFood.visibility = View.GONE
            itemView.llFoodAddMinusMoviesFood.visibility = View.VISIBLE
        }

        itemView.btnPlusMoviesFood.setOnClickListener {
            delegate.onTapPlusSnack(mSnackVO?.id ?: 0)
        }

        itemView.btnMinusMoviesFood.setOnClickListener {
            delegate.onTapMinusSnack(mSnackVO?.id ?: 0)
        }
    }

    private fun setUpForSnackButtonVisibility() {
        if(mSnackVO?.quantity!! <= 0) {
            itemView.btnAddMoviesFood.visibility = View.VISIBLE
            itemView.llFoodAddMinusMoviesFood.visibility = View.GONE
            itemView.tvFoodCountMoviesFood.text = "0"
        } else {
            itemView.btnAddMoviesFood.visibility = View.GONE
            itemView.llFoodAddMinusMoviesFood.visibility = View.VISIBLE
            itemView.tvFoodCountMoviesFood.text = mSnackVO?.quantity.toString()
        }
    }

    fun bindSnackData(snack: SnackVO) {
        mSnackVO = snack
        Glide.with(itemView.context)
            .load(snack.image)
            .into(itemView.ivFoodImageMoviesFood)

        itemView.tvFoodNameMoviesFood.text = snack.name
        itemView.tvFoodPriceMoviesFood.text = snack.price.toString()
        setUpForSnackButtonVisibility()
    }
}