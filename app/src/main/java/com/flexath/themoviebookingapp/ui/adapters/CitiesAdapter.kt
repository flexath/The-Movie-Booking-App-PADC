package com.flexath.themoviebookingapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.delegates.CitiesViewHolderDelegate
import kotlinx.android.synthetic.main.view_holder_cities_list.view.*

class CitiesAdapter(
    private val citiesList: ArrayList<String>,
    private val delegateCities: CitiesViewHolderDelegate
) : RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_cities_list, parent, false)
        return CitiesViewHolder(view, delegateCities)
    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        val city = citiesList[position]
        holder.itemView.tvCityName.text = city
    }

    override fun getItemCount(): Int {
        return citiesList.size
    }

    inner class CitiesViewHolder(itemView: View, delegateCities: CitiesViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                delegateCities.onTapCities(adapterPosition)
            }
        }
    }
}