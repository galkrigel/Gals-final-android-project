package com.example.final_project_android.Modules.AddStudent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.final_project_android.R

class AddUserFragment : Fragment() {
    private var nameTextField: EditText? = null
    private var emailTextField: EditText? = null
    private var messageTextView: TextView? = null
    private var saveButton: Button? = null
    private var cancelButton: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add_user, container, false)
    setupUI(view);
        return view;
    }

    private fun setupUI(view: View) {
        nameTextField = view.findViewById(R.id.etAddStudentName)
        emailTextField =view.findViewById(R.id.etAddStudentID)
        messageTextView = view.findViewById(R.id.tvAddStudentSaved)
        saveButton = view.findViewById(R.id.btnAddStudentSave)
        cancelButton = view.findViewById(R.id.btnAddStudentCancel)

        cancelButton?.setOnClickListener {
          //  finish()
        }

        saveButton?.setOnClickListener {
            val name = nameTextField?.text.toString()
            messageTextView?.text = name
        }
    }
}