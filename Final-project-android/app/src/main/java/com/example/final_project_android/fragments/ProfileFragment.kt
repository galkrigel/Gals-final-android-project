package com.example.final_project_android.fragments

import android.content.Intent
import android.net.Uri
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
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.final_project_android.Model.Model
import com.example.final_project_android.Model.User
import com.example.final_project_android.activities.LoginActivity
import com.example.final_project_android.R
import com.example.final_project_android.utils.ImagesUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ProfileFragment : Fragment() {
    private var idTextField: TextView? = null
    private var emailTextField: TextView? = null
    private var editNameTextField: EditText? = null
    private var editImgImageView: ImageView? = null
    private var selectedImageUri: Uri? = null


    private var saveButton: Button? = null
    private var cancelButton: Button? = null
    private lateinit var btnLogOut: Button

    private var currentUser: FirebaseUser? = null
    private lateinit var mAuth: FirebaseAuth

    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            editImgImageView?.let { ImagesUtils.loadImage(uri, it) }
            selectedImageUri = uri
        }

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
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        setupUI(view);
        return view;
    }

    private fun setupUI(view: View) {
        idTextField = view.findViewById(R.id.tvUserId)
        emailTextField = view.findViewById(R.id.tvUserEmail)
        editNameTextField = view.findViewById(R.id.etEditUserName)
        editImgImageView = view.findViewById(R.id.ivEditUserImg)
        saveButton = view.findViewById(R.id.btnUserSave)
        cancelButton = view.findViewById(R.id.btnUserCancel)
        btnLogOut = view.findViewById(R.id.btnProfileLogout)


        editImgImageView?.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

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
            val email = currentUser!!.email ?: ""
            val newName = editNameTextField?.text.toString()
            val user = User(email, newName, "")

            Model.instance.editUserDetails(user, selectedImageUri) { isSuccess ->
                if (isSuccess) {
                    Toast.makeText(context, "User edited successfully", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack() // navigate back on finish editing
                } else {
                    Toast.makeText(context, "Error edit user details", Toast.LENGTH_SHORT).show()

                }

            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }
}