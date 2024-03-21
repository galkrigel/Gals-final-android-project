package com.example.final_project_android.Modules

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.final_project_android.MainActivity
import com.example.final_project_android.Model.Model
import com.example.final_project_android.Model.Property
import com.example.final_project_android.Modules.Properties.LoginActivity
import com.example.final_project_android.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ProfileFragment : Fragment() {
    private var idTextField: TextView? = null
    private var emailTextField: TextView? = null
    private var editEmailTextField: EditText? = null

    private var saveButton: Button? = null
    private var cancelButton: Button? = null
    private lateinit var btnLogOut: Button

    private var currentUser: FirebaseUser? = null
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        mAuth = FirebaseAuth.getInstance()
        currentUser = mAuth.currentUser
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        setupUI(view);
        return view;
    }

    private fun setupUI(view: View) {
        idTextField = view.findViewById(R.id.tvUserId)
        emailTextField = view.findViewById(R.id.tvUserEmail)
        editEmailTextField = view.findViewById(R.id.etEditUserEmail)
        saveButton = view.findViewById(R.id.btnUserSave)
        cancelButton = view.findViewById(R.id.btnUserCancel)
        btnLogOut = view.findViewById(R.id.btnProfileLogout)

        btnLogOut.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(activity, LoginActivity::class.java)
            activity?.startActivity(intent)

        }

        currentUser?.let {
            idTextField?.text = "User id: ${currentUser!!.uid}"
            emailTextField?.text = "User email: ${currentUser!!.email}"
        }

        cancelButton?.setOnClickListener {
//            Navigation.findNavController(it).popBackStack(R.id.profileFragment, false)
        }

        saveButton?.setOnClickListener {
            val newEmail = editEmailTextField?.text.toString()
            Log.i("old email: ", "${emailTextField?.text}")
            Log.i("new email: ", "${editEmailTextField?.text}")

// Edit user here
//            val property = Property(title, title, country, city, price, area, userID, "")
//            Model.instance.addProperty(property) {
//                Navigation.findNavController(it).popBackStack(R.id.propertiesFragment, false)
//            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }
}