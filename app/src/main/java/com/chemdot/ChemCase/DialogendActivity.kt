package com.chemdot.ChemCase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.common.server.converter.StringToIntConverter

class DialogendActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialogend)

        val gameexit = findViewById<Button>(R.id.exitTOquestions)
        val correctAnswersCounter = findViewById<TextView>(R.id.correctAnswersCounter)
        val AnswersCount = findViewById<TextView>(R.id.AnswersCount)
        val Congrats = findViewById<TextView>(R.id.congratulations)

        val CorrectAnswers = intent.getStringExtra("CurrAnswers")
        val AllAnswersCount = intent.getStringExtra("AnswersCount")

        correctAnswersCounter.text = "$CorrectAnswers"
        AnswersCount.text = " / $AllAnswersCount"

        if (CorrectAnswers!!.toInt() > (AllAnswersCount!!.toInt()/2)) {
            Congrats.text = "Вы прошли тест!"
        } else {
            Congrats.text = "Вы провалили тест"
        }

        gameexit.setOnClickListener{
            finish()
        }
    }
}