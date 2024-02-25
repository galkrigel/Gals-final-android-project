package com.example.final_project_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val registerButton = findViewById<Button>(R.id.BtnMainRegister)

        registerButton.setOnClickListener(::onRegisterBtnClicked)
    }


    fun onRegisterBtnClicked(view: View){
val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

}