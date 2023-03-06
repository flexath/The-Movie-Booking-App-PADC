package com.flexath.themoviebookingapp.ui.adapters.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.vos.location.CitiesVO
import com.flexath.themoviebookingapp.ui.delegates.CitiesViewHolderDelegate
import kotlinx.android.synthetic.main.view_holder_location_cities_list.view.*

class CitiesLocationAdapter(
    private val citiesList: List<CitiesVO>,
    private val delegateCities: CitiesViewHolderDelegate
) : RecyclerView.Adapter<CitiesLocationAdapter.CitiesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_location_cities_list, parent, false)
        return CitiesViewHolder(view, delegateCities)
    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        val city = citiesList[position]
        holder.itemView.tvCityName.text = city.name

        holder.itemView.setOnClickListener {
            delegateCities.onTapCities(city.name.toString())
        }
    }

    override fun getItemCount(): Int {
        return citiesList.size
    }

    inner class CitiesViewHolder(itemView: View, delegateCities: CitiesViewHolderDelegate) : RecyclerView.ViewHolder(itemView)
}