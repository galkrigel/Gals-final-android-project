package com.example.final_project_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class RegisterActivity : AppCompatActivity() {
    var nameTextField: EditText? = null
    var emailTextField: EditText? = null
    var messageTextView: TextView? = null
    var saveButton: Button? = null
    var cancelButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setupUI()
    }

    private fun setupUI() {
        nameTextField = findViewById(R.id.etRegisterName)
        emailTextField = findViewById(R.id.etRegisterEmail)
        messageTextView = findViewById(R.id.tvRegisterMessage)
        saveButton = findViewById(R.id.btnRegisterSave)
        cancelButton = findViewById(R.id.btnRegisterCancel)

        cancelButton?.setOnClickListener {
            finish()
        }

        saveButton?.setOnClickListener {
            val name = nameTextField?.text.toString()
            messageTextView?.text = name
        }
    }
}