package com.chemdot.ChemCase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class StartNewGame : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_new_game)

        val backHome = findViewById<ImageButton>(R.id.homeBut)
        val newNonOrgGame = findViewById<Button>(R.id.newNonOrgGame)

        backHome.setOnClickListener{
            finish()
        }

        newNonOrgGame.setOnClickListener{
            val intent = Intent(this, GameCycle::class.java)
            startActivity(intent)
            finish()
        }
    }
}