package com.flexath.themoviebookingapp.ui.fragments.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.adapters.movies.BottomSheetDialogMoviesFoodAdapter
import com.flexath.themoviebookingapp.ui.adapters.movies.MoviesFoodViewPagerAdapter
import com.flexath.themoviebookingapp.ui.generaldata.FoodFactory
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_movies_food.*
import kotlinx.android.synthetic.main.layout_app_bar_movies_food.*
import kotlinx.android.synthetic.main.layout_bottom_sheet_dialog_movies_food.*
import kotlinx.android.synthetic.main.layout_button_movies_food.*

class MoviesFoodFragment : Fragment() {

    private lateinit var mMoviesFoodAdapter:MoviesFoodViewPagerAdapter
    private lateinit var mBottomSheetDialogAdapter:BottomSheetDialogMoviesFoodAdapter
    private val foodList = FoodFactory()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpFoodTabLayout()                // For Food Types Tab
        setUpListeners()
    }

    private fun setUpListeners() {
        // Order Button for Check Out Screen
        btnFoodOrderPurchaseMoviesFood.setOnClickListener {
            val action = MoviesFoodFragmentDirections.actionMoviesFoodToMoviesTicketCheckout()
            action.argCheckoutOrCancel = "Checkout"
            it.findNavController().navigate(action)
        }

        btnSkipButtonMoviesFood.setOnClickListener {
            val action = MoviesFoodFragmentDirections.actionMoviesFoodToMoviesTicketCheckout()
            action.argCheckoutOrCancel = "Checkout"
            it.findNavController().navigate(action)
        }

        setUpFoodBottomSheetDialog()
    }

    private fun setUpFoodBottomSheetDialog() {
        btnDownArrowMoviesFood.setImageResource(R.drawable.ic_down_arrow_black)
        btnDownArrowMoviesFood.setOnClickListener {

            val bottomDialog = BottomSheetDialog(requireActivity())
            val dialogView = layoutInflater.inflate(R.layout.layout_bottom_sheet_dialog_movies_food,null,false)
            bottomDialog.setContentView(dialogView)
            bottomDialog.setCancelable(true)

            bottomDialog.btnDownArrowMoviesFood.setImageResource(R.drawable.arrow_up_black)

            // Bottom Sheet RecyclerView Setup
            mBottomSheetDialogAdapter = BottomSheetDialogMoviesFoodAdapter()
            bottomDialog.rvFoodBottomSheetDialogMoviesFood.adapter = mBottomSheetDialogAdapter
            bottomDialog.rvFoodBottomSheetDialogMoviesFood.layoutManager = LinearLayoutManager(requireContext())
            bottomDialog.rvFoodBottomSheetDialogMoviesFood.setHasFixedSize(true)
            mBottomSheetDialogAdapter.notifyDataSetChanged()

            bottomDialog.show()

            bottomDialog.btnFoodOrderPurchaseBottomDialogMoviesFood.setOnClickListener {
                val action = MoviesFoodFragmentDirections.actionMoviesFoodToMoviesTicketCheckout()
                action.argCheckoutOrCancel = "Checkout"
                findNavController().navigate(action)
                bottomDialog.dismiss()
            }

            bottomDialog.btnDownArrowMoviesFood.setOnClickListener {
                bottomDialog.dismiss()
            }

        }
    }

    private fun setUpFoodTabLayout() {
        mMoviesFoodAdapter = MoviesFoodViewPagerAdapter(this,foodList)
        viewPagerTabLayoutMoviesFood.adapter = mMoviesFoodAdapter

        TabLayoutMediator(tabLayoutMoviesFood,viewPagerTabLayoutMoviesFood) { tab,position ->
            for(i in foodList.foodTitleList.indices) {
                if(position == i){
                    tab.text = foodList.foodTitleList[i]
                }
            }
        }.attach()
    }
}