package com.flexath.themoviebookingapp.ui.fragments.tickets

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.model.CinemaModel
import com.flexath.themoviebookingapp.data.model.CinemaModelImpl
import com.flexath.themoviebookingapp.ui.adapters.ticket.TicketDetailsTicketHomeAdapter
import kotlinx.android.synthetic.main.fragment_tickets_home.*


class TicketsHomeFragment : Fragment() {

    private lateinit var mTicketAdapter: TicketDetailsTicketHomeAdapter
    private val mCinemaModel:CinemaModel = CinemaModelImpl

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tickets_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpTicketRecyclerView()
        getTickets()
    }

    private fun getTickets() {
        mTicketAdapter.setNewData(mCinemaModel.getAllTickets() ?: listOf())
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpTicketRecyclerView() {
        mTicketAdapter = TicketDetailsTicketHomeAdapter()
        rvTicketHome.adapter = mTicketAdapter
        rvTicketHome.layoutManager = LinearLayoutManager(requireActivity())
        rvTicketHome.setHasFixedSize(true)
        mTicketAdapter.notifyDataSetChanged()
    }
}