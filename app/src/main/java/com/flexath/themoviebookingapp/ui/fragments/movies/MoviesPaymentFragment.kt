package com.flexath.themoviebookingapp.ui.fragments.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.flexath.themoviebookingapp.R
import kotlinx.android.synthetic.main.fragment_movies_payment.*

class MoviesPaymentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpListeners()
    }

    private fun setUpListeners() {
        btnUpiMethodProfilePayment.setOnClickListener {
            navigateNextFragment(it)
        }

        btnGiftVoucherMethodProfilePayment.setOnClickListener {
            navigateNextFragment(it)
        }

        btnQuickPayMethodProfilePayment.setOnClickListener {
            navigateNextFragment(it)
        }

        btnCreditMethodProfilePayment.setOnClickListener {
            navigateNextFragment(it)
        }

        btnRedeemMethodProfilePayment.setOnClickListener {
            navigateNextFragment(it)
        }

        btnMobileWalletMethodProfilePayment.setOnClickListener {
            navigateNextFragment(it)
        }

        btnNetBankingMethodProfilePayment.setOnClickListener {
            navigateNextFragment(it)
        }
    }

    private fun navigateNextFragment(view: View) {
        val action = MoviesPaymentFragmentDirections.actionProfilePaymentToMoviesTicketConfirmation()
        view.findNavController().navigate(action)
    }
}