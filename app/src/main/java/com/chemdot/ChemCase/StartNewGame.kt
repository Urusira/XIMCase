package com.chemdot.ChemCase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class StartNewGame : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_new_game)

        val newGameMet = findViewById<Button>(R.id.typeMet)
        val newGameNonMet = findViewById<Button>(R.id.typeNonMet)
        newGameMet.setOnClickListener{
            val intent = Intent(this, GameCycle::class.java)
            intent.putExtra("category","${newGameMet.tag}")
            startActivity(intent)
            finish()
        }
        newGameNonMet.setOnClickListener{
            val intent = Intent(this, GameCycle::class.java)
            intent.putExtra("category","${newGameNonMet.tag}")
            startActivity(intent)
            finish()
        }

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }
}