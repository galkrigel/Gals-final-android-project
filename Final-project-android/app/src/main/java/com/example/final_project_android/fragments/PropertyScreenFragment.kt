package com.example.final_project_android.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.final_project_android.Model.Model
import com.example.final_project_android.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class PropertyScreenFragment : Fragment() {
    private var textViewTitle: TextView? = null
    private var textViewCountry: TextView? = null
    private var textViewCity: TextView? = null
    private var textViewPrice: TextView? = null
    private var textViewArea: TextView? = null

    private var btnEdit: Button? = null
    private var btnDelete: Button? = null

    private var currentUser: FirebaseUser? = null
    private lateinit var mAuth: FirebaseAuth
    private lateinit var userID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        val view = inflater.inflate(R.layout.fragment_property_screen, container, false)

        val propertyId = arguments?.let { PropertyScreenFragmentArgs.fromBundle(it).propertyId }
        val propertyTitle = arguments?.let { PropertyScreenFragmentArgs.fromBundle(it).title }
        val propertyCountry = arguments?.let { PropertyScreenFragmentArgs.fromBundle(it).country }
        val propertyCity = arguments?.let { PropertyScreenFragmentArgs.fromBundle(it).city }
        val propertyPrice = arguments?.let { PropertyScreenFragmentArgs.fromBundle(it).price }
        val propertyArea = arguments?.let { PropertyScreenFragmentArgs.fromBundle(it).area }
        val ownerId = arguments?.let { PropertyScreenFragmentArgs.fromBundle(it).ownerId }

        textViewTitle = view.findViewById(R.id.tvPropertyScreenTitle)
        textViewCountry = view.findViewById(R.id.tvPropertyScreenCountry)
        textViewCity = view.findViewById(R.id.tvPropertyScreenCity)
        textViewPrice = view.findViewById(R.id.tvPropertyScreenPrice)
        textViewArea = view.findViewById(R.id.tvPropertyScreenArea)
        btnEdit = view.findViewById(R.id.btnPropertyScreenEdit)
        btnDelete = view.findViewById(R.id.btnPropertyScreenDelete)

        textViewTitle?.text = propertyTitle
        textViewCountry?.text = propertyCountry
        textViewCity?.text = propertyCity
        textViewPrice?.text = propertyPrice
        textViewArea?.text = propertyArea

        if (ownerId == currentUser?.uid) {
            btnDelete?.visibility = View.VISIBLE
            btnEdit?.visibility = View.VISIBLE

            btnDelete?.setOnClickListener {
                Model.instance.deleteProperty(propertyId ?: "") { isSuccess ->
                    if (isSuccess) {
                        // Going back to the properties page
                        findNavController().popBackStack()
                        Toast.makeText(
                            view.getContext(),
                            "Property deleted successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {
                        Toast.makeText(
                            view.getContext(),
                            "Error delete the property",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
            }

            btnEdit?.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("propertyId", propertyId)
                Navigation.findNavController(it).navigate(R.id.addPropertyFragment, bundle)
            }

        }


        val backButton: Button = view.findViewById(R.id.btnPropertyScreenFragmentBack)
        backButton.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }

        return view
    }

}