package com.example.final_project_android.Modules.AddProperty

import android.os.Bundle
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
import com.example.final_project_android.R

class AddPropertyFragment : Fragment() {
    private var nameTextField: EditText? = null
    private var emailTextField: EditText? = null
    private var messageTextView: TextView? = null
    private var saveButton: Button? = null
    private var cancelButton: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
        nameTextField = view.findViewById(R.id.etRegisterName)
        emailTextField = view.findViewById(R.id.etRegisterEmail)
        messageTextView = view.findViewById(R.id.tvRegisterMessage)
        saveButton = view.findViewById(R.id.btnRegisterSave)
        cancelButton = view.findViewById(R.id.btnRegisterCancel)

        cancelButton?.setOnClickListener {
           Navigation.findNavController(it).popBackStack(R.id.propertiesFragment, false)
        }

        saveButton?.setOnClickListener {
            val name = nameTextField?.text.toString()
            messageTextView?.text = name
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }
}