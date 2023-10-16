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
    // Уже описал функцию в велком пейдж
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }
    // Эта функция позволяет фиксировать те моменты, когда мы нажимаем на кнопку возврата на самой мобиле
    override fun onBackPressed() {
        // Используем билдер алертов
        AlertDialog.Builder(this).apply {
            setTitle("Вы уверены?") // Заголовок всплывающего алерта
            setMessage("Весь прогресс будет утерян. Вы действительно хотите вернуться в главное меню?") // Сообщение алерта
            // Позитив баттон - кнопка соглашения, при её нажатии производится определённое действие
            setPositiveButton("Да") { _, _ ->
                super.onBackPressed()   // В данном случае мы пропускаем пользователя дальше, вызывая за него нажатие кнопки возврата
                finish()    // Удаляем страницу из памяти
            }
            // Нейтральная кнопка, тут мы ничего не делаем, при нажатии на неё тупо скрывается алерт
            setNeutralButton("Отмена"){_, _ ->
            }
        }.create().show()   // создаём алерт и выводим на экран
    }
}
