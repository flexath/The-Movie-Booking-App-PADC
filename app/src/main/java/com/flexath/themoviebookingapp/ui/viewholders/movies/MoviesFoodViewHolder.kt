package com.flexath.themoviebookingapp.ui.viewholders.movies

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.flexath.themoviebookingapp.data.vos.test.SnackVO
import kotlinx.android.synthetic.main.view_holder_movies_food_foods_list.view.*

class MoviesFoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var count:Int = 0
    private var mSnackVO:SnackVO? = null

    init {
        setUpAddButtonListener()
    }

    private fun setUpAddButtonListener() {
        itemView.btnAddMoviesFood.setOnClickListener {
            itemView.btnAddMoviesFood.visibility = View.GONE
            itemView.llFoodAddMinusMoviesFood.visibility = View.VISIBLE
        }

        itemView.btnPlusMoviesFood.setOnClickListener {
            count++
            itemView.tvFoodCountMoviesFood.text = count.toString()
        }

        itemView.btnMinusMoviesFood.setOnClickListener {
            count--
            itemView.tvFoodCountMoviesFood.text = count.toString()
        }

        itemView.setOnClickListener {
            itemView.btnAddMoviesFood.visibility = View.VISIBLE
            itemView.llFoodAddMinusMoviesFood.visibility = View.GONE
        }
    }

    fun bindSnackData(snack: SnackVO) {
        mSnackVO = snack
        Glide.with(itemView.context)
            .load(snack.image)
            .into(itemView.ivFoodImageMoviesFood)

        itemView.tvFoodNameMoviesFood.text = snack.name
        itemView.tvFoodPriceMoviesFood.text = snack.price.toString()

    }
}