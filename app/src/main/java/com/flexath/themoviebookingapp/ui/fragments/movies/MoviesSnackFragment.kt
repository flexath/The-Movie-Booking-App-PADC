package com.flexath.themoviebookingapp.ui.fragments.movies

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.model.CinemaModel
import com.flexath.themoviebookingapp.data.model.CinemaModelImpl
import com.flexath.themoviebookingapp.data.vos.movie.SnackCategoryVO
import com.flexath.themoviebookingapp.data.vos.movie.SnackVO
import com.flexath.themoviebookingapp.ui.adapters.movies.BottomSheetDialogMoviesFoodAdapter
import com.flexath.themoviebookingapp.ui.adapters.movies.MoviesSnackAdapter
import com.flexath.themoviebookingapp.ui.delegates.SnackViewHolderDelegate
import com.flexath.themoviebookingapp.ui.utils.CinemaData
import com.flexath.themoviebookingapp.ui.utils.SeatData
import com.flexath.themoviebookingapp.ui.utils.Ticket
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movies_food.*
import kotlinx.android.synthetic.main.layout_app_bar_movies_food.*
import kotlinx.android.synthetic.main.layout_bottom_sheet_dialog_movies_food.*
import kotlinx.android.synthetic.main.layout_button_movies_food.*


class MoviesSnackFragment : Fragment(), SnackViewHolderDelegate {

    private lateinit var mBottomSheetDialogAdapter: BottomSheetDialogMoviesFoodAdapter
    private lateinit var mSnackAdapter: MoviesSnackAdapter
    private val mCinemaModel: CinemaModel = CinemaModelImpl

    private var mSnackCategoryTitleList: MutableList<String?>? = null
    private var mSnackCategoryList: MutableList<SnackCategoryVO>? = null
    private var mSnackList: MutableLiveData<List<SnackVO>>? = MutableLiveData<List<SnackVO>>()

    private val args: MoviesSnackFragmentArgs by navArgs()

    // For Ticket
    private var mMovieName: String? = null
    private var mCinemaInfo: CinemaData? = null
    private var mSeatInfo: SeatData? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).bottomNvgViewHome.visibility = View.INVISIBLE

        Log.i("CinemaSnack", args.argMovieName.toString())
        Log.i("CinemaSnack", args.argCinemaInfo.toString())
        Log.i("CinemaSnack", args.argSeatInfo.toString())

        mMovieName = args.argMovieName
        mCinemaInfo = args.argCinemaInfo
        mSeatInfo = args.argSeatInfo

        mSnackCategoryTitleList = mutableListOf("All")
        mSnackCategoryList = mutableListOf()

        setUpListeners()
        setUpFoodRecyclerView()

        setUpFoodTabLayoutListener()
        requestData()
    }

    private fun requestData() {
        mCinemaModel.getSnackCategory(
            "Bearer ${mCinemaModel.getOtp(201)?.token}",
            onSuccess = {
                mSnackCategoryList = it as MutableList<SnackCategoryVO>
                setUpSnackTabLayoutTitle(it)
            },
            onFailure = {
                Toast.makeText(requireActivity(), "Snack Category Call fails", Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun setUpSnackTabLayoutTitle(snackCategoryList: List<SnackCategoryVO>) {
        for(categoryList in snackCategoryList) {
            mSnackCategoryTitleList?.add(categoryList.title)
        }
        mSnackCategoryTitleList?.forEach { snackCategoryTitle ->
            tabLayoutMoviesSnack?.newTab()?.apply {
                text = snackCategoryTitle
                tabLayoutMoviesSnack.addTab(this)
            }
        }
    }

    private fun requestSnackListData(categoryId: String) {
        mCinemaModel.getSnackByCategory(
            "Bearer ${mCinemaModel.getOtp(201)?.token}",
            categoryId,
            onSuccess = {
                mSnackList?.value = it
                mSnackAdapter.bindSnackListData(it)

                setUpSnackPriceAndQuantity()
            },
            onFailure = {
                Toast.makeText(requireActivity(), "Snack Call fails", Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun setUpFoodTabLayoutListener() {

        tabLayoutMoviesSnack.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                if (tab?.position == 0) {
                    requestSnackListData("")
                } else {
                    mSnackCategoryList?.get(tab?.position?.minus(1) ?: 0)?.id?.let { categoryId ->
                        requestSnackListData(categoryId.toString())
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun createTicket(): Ticket {
        return Ticket(mMovieName, mCinemaInfo, mSeatInfo, getSnackTotalPrice(), mSnackList?.value)
    }

    private fun getSnackTotalPrice() :Long {
        var snackTotalPrice = 0L
        mSnackList?.observe(viewLifecycleOwner) { snackList ->
            snackList.forEach {
                snackTotalPrice += it.price?.times(it.quantity) ?: 0
            }
        }
        return snackTotalPrice
    }

    private fun setUpListeners() {
        // Order Button for Check Out Screen
        btnFoodOrderPurchaseMoviesFood.setOnClickListener {
            val action = MoviesSnackFragmentDirections.actionMoviesFoodToMoviesTicketCheckout()
            action.argCheckoutOrCancel = "Checkout"
            action.argTicket = createTicket()
            it.findNavController().navigate(action)
        }

        btnSkipButtonMoviesFood.setOnClickListener {
            val action = MoviesSnackFragmentDirections.actionMoviesFoodToMoviesTicketCheckout()
            action.argCheckoutOrCancel = "Checkout"
            action.argTicket = createTicket()
            it.findNavController().navigate(action)
        }

        setUpFoodBottomSheetDialog()
    }

    @SuppressLint("NotifyDataSetChanged", "InflateParams")
    private fun setUpFoodBottomSheetDialog() {
        btnDownArrowMoviesFood.setImageResource(R.drawable.ic_down_arrow_black)
        btnDownArrowMoviesFood.setOnClickListener {

            val bottomDialog = BottomSheetDialog(requireActivity())
            val dialogView =
                layoutInflater.inflate(R.layout.layout_bottom_sheet_dialog_movies_food, null, false)
            bottomDialog.setContentView(dialogView)
            bottomDialog.setCancelable(true)

            bottomDialog.btnDownArrowMoviesFood.setImageResource(R.drawable.arrow_up_black)

            // Bottom Sheet RecyclerView Setup
            mBottomSheetDialogAdapter = BottomSheetDialogMoviesFoodAdapter()
            bottomDialog.rvFoodBottomSheetDialogMoviesFood.adapter = mBottomSheetDialogAdapter
            bottomDialog.rvFoodBottomSheetDialogMoviesFood.layoutManager =
                LinearLayoutManager(requireContext())
            bottomDialog.rvFoodBottomSheetDialogMoviesFood.setHasFixedSize(true)
            mBottomSheetDialogAdapter.notifyDataSetChanged()

            bottomDialog.show()

            bottomDialog.btnFoodOrderPurchaseBottomDialogMoviesFood.setOnClickListener {
                val action = MoviesSnackFragmentDirections.actionMoviesFoodToMoviesTicketCheckout()
                action.argCheckoutOrCancel = "Checkout"
                action.argTicket = createTicket()
                findNavController().navigate(action)
                bottomDialog.dismiss()
            }

            bottomDialog.btnDownArrowMoviesFood.setOnClickListener {
                bottomDialog.dismiss()
            }
        }
    }

    private fun setUpFoodRecyclerView() {
        mSnackAdapter = MoviesSnackAdapter(this)
        rSnackMoviesSnack.adapter = mSnackAdapter
        rSnackMoviesSnack.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun setUpSnackPriceAndQuantity() {
        tvSnackTotalPriceOnBtn.text = getSnackTotalPrice().toString()

        var snackTotalQuantity = 0
        mSnackList?.value?.forEach {
            snackTotalQuantity += it.quantity
        }
        tvPurchasedFoodCountMoviesFood.text = snackTotalQuantity.toString()
    }

    override fun onResume() {
        super.onResume()
        setUpSnackPriceAndQuantity()
    }

    override fun onTapPlusSnack(snackId: Int) {
        mSnackList?.observe(this) { snackList ->
            snackList.forEach {
                if (it.id == snackId) {
                    it.quantity++
                }
            }
            mSnackAdapter.bindSnackListData(snackList)
        }
        setUpSnackPriceAndQuantity()
        Log.i("SnackList",mSnackList.toString())
    }

    override fun onTapMinusSnack(snackId: Int) {
        mSnackList?.observe(this) { snackList ->
            snackList.forEach {
                if (it.id == snackId) {
                    it.quantity--
                    if (it.quantity <= 0) {
                        it.quantity = 0
                    }
                }
            }
            mSnackAdapter.bindSnackListData(snackList)
        }
        setUpSnackPriceAndQuantity()
        Log.i("SnackList",mSnackList.toString())
    }

}