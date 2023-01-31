package com.flexath.themoviebookingapp.ui.fragments.profile

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.activities.MainActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile_home.*


class ProfileHomeFragment : Fragment() {

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
            val dialog = MaterialAlertDialogBuilder(requireContext(),R.style.RoundedAlertDialog)
                .setTitle("Log Out ?")
                .setMessage("Are you sure to log out ?")
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, which -> (activity as MainActivity).finish() }
                .setNegativeButton("No") { dialog, which -> dialog?.dismiss() }
                .create()
            dialog.show()
        }
    }
}