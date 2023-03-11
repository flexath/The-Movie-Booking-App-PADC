package com.flexath.themoviebookingapp.ui.adapters.movies

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.vos.test.PaymentVO
import com.flexath.themoviebookingapp.ui.fragments.movies.MoviesPaymentFragmentDirections
import com.flexath.themoviebookingapp.ui.viewholders.movies.MoviesPaymentViewHolder
import kotlinx.android.synthetic.main.view_holder_movies_payment_list.view.*

class MoviesPaymentAdapter : RecyclerView.Adapter<MoviesPaymentViewHolder>() {

    private var mPaymentList:List<PaymentVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesPaymentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movies_payment_list,parent,false)
        return MoviesPaymentViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesPaymentViewHolder, position: Int) {
        holder.bindData(mPaymentList[position])
    }

    override fun getItemCount(): Int {
        return mPaymentList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(paymentList: List<PaymentVO>) {
        mPaymentList = paymentList
        notifyDataSetChanged()
    }
}