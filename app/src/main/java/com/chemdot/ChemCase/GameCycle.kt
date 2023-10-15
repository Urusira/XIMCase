package com.chemdot.ChemCase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class GameCycle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_cycle)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }
    override fun onBackPressed() {
        AlertDialog.Builder(this).apply {
            setTitle("Вы уверены?")
            setMessage("Весь прогресс будет утерян. Вы действительно хотите вернуться в главное меню?")

            setPositiveButton("Да") { _, _ ->
                super.onBackPressed()
                finish()
            }

            setNeutralButton("Отмена"){_, _ ->
            }
        }.create().show()
    }
}
