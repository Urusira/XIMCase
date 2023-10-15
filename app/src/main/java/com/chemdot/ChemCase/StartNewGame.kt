package com.chemdot.ChemCase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton

class StartNewGame : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_new_game)

        val newNonOrgGame = findViewById<Button>(R.id.newNonOrgGame)

        newNonOrgGame.setOnClickListener{
            val intent = Intent(this, GameCycle::class.java)
            startActivity(intent)
            finish()
        }

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }
}