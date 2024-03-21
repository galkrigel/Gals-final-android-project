package com.example.final_project_android.Modules.Properties

import com.example.final_project_android.MainActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import com.example.final_project_android.R


class LoginActivity : AppCompatActivity() {

    private lateinit var etLoginEmail: TextInputEditText
    private lateinit var etLoginPassword: TextInputEditText
    private lateinit var tvRegisterHere: TextView
    private lateinit var btnLogin: Button


    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("TAG", "login has called!")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etLoginEmail = findViewById(R.id.etLoginEmail)
        etLoginPassword = findViewById(R.id.etLoginPass)
        tvRegisterHere = findViewById(R.id.tvRegisterHere)
        btnLogin = findViewById(R.id.btnLogin)

        mAuth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            loginUser()
        }
        tvRegisterHere.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
        Log.i("TAG", "login has finished!")

    }

    private fun loginUser() {
        val email = etLoginEmail.text.toString()
        val password = etLoginPassword.text.toString()

        if (email.isEmpty()) {
            etLoginEmail.error = "Email cannot be empty"
            etLoginEmail.requestFocus()
        } else if (password.isEmpty()) {
            etLoginPassword.error = "Password cannot be empty"
            etLoginPassword.requestFocus()
        } else {
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@LoginActivity, "User logged in successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    } else {
                        Toast.makeText(this@LoginActivity, "Log in Error: " + task.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}