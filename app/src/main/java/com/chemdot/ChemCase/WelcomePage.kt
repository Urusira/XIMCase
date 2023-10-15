package com.chemdot.ChemCase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class WelcomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)

        val login = findViewById<Button>(R.id.btn_singIn)
        val loginUserName = findViewById<androidx.appcompat.widget.AppCompatEditText>(R.id.loginUName)

        login.setOnClickListener{
            if(loginUserName.text.toString().isEmpty())
            {
                Toast.makeText(this, "Введите имя пользователя", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }
}