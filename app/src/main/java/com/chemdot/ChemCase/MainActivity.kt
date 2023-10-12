package com.chemdot.ChemCase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newGame = findViewById<Button>(R.id.newGame)
        val exit = findViewById<Button>(R.id.exit)

        newGame.setOnClickListener{
            val intent = Intent(this, StartNewGame::class.java)
            startActivity(intent)
        }
        exit.setOnClickListener{
            finish()
        }
    }
}