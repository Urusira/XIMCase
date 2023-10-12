package com.chemdot.ChemCase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GameCycle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_cycle)
    }

    fun showConfirmationDialog(view: View)
    {
        MaterialAlertDialogBuilder(this)
            .setTitle("Вы уверены, что хотите выйти?")
            .setMessage("Это сбросит весь текущий прогресс.\nВы действительно хотите выйти?")
            .setPositiveButton("Да") { dialog, whitch ->
                finish()
            }
            .setNeutralButton("Отмена") { dialog, whitch ->
            }
            .show()
    }
}
