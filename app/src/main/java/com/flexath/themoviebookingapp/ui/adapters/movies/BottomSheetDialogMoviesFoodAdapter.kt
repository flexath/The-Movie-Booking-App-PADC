package com.flexath.themoviebookingapp.ui.adapters.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.viewholders.movies.BottomSheetDialogMoviesFoodViewHolder

class BottomSheetDialogMoviesFoodAdapter : RecyclerView.Adapter<BottomSheetDialogMoviesFoodViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSheetDialogMoviesFoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movies_food_dialog_food_list,parent,false)
        return BottomSheetDialogMoviesFoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: BottomSheetDialogMoviesFoodViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 3
    }
}