package com.example.final_project_android.Modules.AddProperty

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
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.Navigation
import com.example.final_project_android.Model.Model
import com.example.final_project_android.Model.Property
import com.example.final_project_android.R
import com.example.final_project_android.utils.ImagesUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AddPropertyFragment : Fragment() {
    private var titleTextField: EditText? = null
    private var idTextField: EditText? = null
    private var priceTextField: EditText? = null
    private var areaTextField: EditText? = null
    private var countryTextField: EditText? = null
    private var cityTextField: EditText? = null
    private var imgImageView: ImageView? = null
    private var selectedImageUri: Uri? = null

    private var saveButton: Button? = null
    private var cancelButton: Button? = null

    private var currentUser: FirebaseUser? = null
    private lateinit var mAuth: FirebaseAuth
    private lateinit var userID: String

    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            imgImageView?.let { ImagesUtils.loadImage(uri, it) }
            selectedImageUri = uri
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        mAuth = FirebaseAuth.getInstance()
        currentUser = mAuth.currentUser
        currentUser?.let {
            userID = currentUser!!.uid
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_property, container, false)
        setupUI(view);
        return view;
    }

    private fun setupUI(view: View) {
        titleTextField = view.findViewById(R.id.etPropertyTitle)
        priceTextField = view.findViewById(R.id.etPropertyPrice)
        areaTextField = view.findViewById(R.id.etPropertyArea)
        countryTextField = view.findViewById(R.id.etPropertyCountry)
        cityTextField = view.findViewById(R.id.etPropertyCity)

        saveButton = view.findViewById(R.id.btnRegisterSave)
        cancelButton = view.findViewById(R.id.btnRegisterCancel)


        imgImageView = view.findViewById(R.id.ivPropertyImage);
        imgImageView?.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }


        cancelButton?.setOnClickListener {
            Navigation.findNavController(it).popBackStack(R.id.propertiesFragment, false)
        }

        saveButton?.setOnClickListener {
            val title = titleTextField?.text.toString()
            val country = countryTextField?.text.toString()
            val city = cityTextField?.text.toString()
            val area = areaTextField?.text.toString()
            val price = priceTextField?.text.toString()
            val id = idTextField?.text.toString()


            val property = Property(title, title, country, city, price, area, userID, "")
//            Model.instance.addProperty(property) {
//                Navigation.findNavController(it).popBackStack(R.id.propertiesFragment, false)
//            }

            if (selectedImageUri != null) {
                Model.instance.addProperty(property, selectedImageUri!!) {
                    Navigation.findNavController(it).popBackStack(R.id.propertiesFragment, false)
                }
            } else {
                Model.instance.addProperty(property, null) {
                    Navigation.findNavController(it).popBackStack(R.id.propertiesFragment, false)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }
}