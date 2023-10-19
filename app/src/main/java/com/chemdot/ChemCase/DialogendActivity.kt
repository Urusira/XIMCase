package com.chemdot.ChemCase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DialogendActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialogend)

        val gameexit = findViewById<Button>(R.id.exitTOquestions)
                gameexit.setOnClickListener{
                    finish()
                }
    }
}