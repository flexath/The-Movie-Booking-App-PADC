package com.flexath.themoviebookingapp.ui.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.model.CinemaModel
import com.flexath.themoviebookingapp.data.model.CinemaModelImpl
import com.flexath.themoviebookingapp.ui.activities.MainActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_profile_home.*


class ProfileHomeFragment : Fragment() {

    private val mCinemaModel:CinemaModel = CinemaModelImpl

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpListeners()
    }

    private fun setUpListeners() {
        btnLogOutProfileHome.setOnClickListener {

            val dialog = MaterialAlertDialogBuilder(requireContext())
                .setTitle("Log Out ?")
                .setMessage("Are you sure to log out ?")
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, which ->
                    mCinemaModel.logout(
                        "Bearer ${mCinemaModel.getOtp(201)?.token}",
                        onSuccess = {
                            Toast.makeText(requireActivity(),"Logout call succeeded",Toast.LENGTH_SHORT).show()
                            (activity as MainActivity).finish()
                        },
                        onFailure = {
                            Toast.makeText(requireActivity(),"Logout call fails",Toast.LENGTH_SHORT).show()
                        }
                    )
                }
                .setNegativeButton("No") { dialog, which -> dialog?.dismiss() }
                .create()
            dialog.show()
        }
    }
}