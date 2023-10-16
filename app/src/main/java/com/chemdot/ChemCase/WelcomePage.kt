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

        val login = findViewById<Button>(R.id.btn_singIn)   //  В переменную логин помещаем объект "кнопка" с заданным айдишником
        val loginUserName = findViewById<androidx.appcompat.widget.AppCompatEditText>(R.id.loginUName)  // По аналогии ищем элемент AppCompatEditText с заданным айдишником

        /*
        * Здесь идёт прослушка кнопки "вход". Внутри ифа мы смотрим, есть ли что-то в
        * строке "имя пользователя". Если она пуста, выводим "тост" - всплывающее сообщение
        * в нижней части экрана, с просьбой ввести имя пользователя
        * В противном случае юзаем функцию интент, которая позволяет открыть новый лейаут - экран.
        * Функцией finish завершаем текущий экран, чтобы он не висел в памяти телефона.
        * Когда мы финишим экран, он удаляется из памяти и при нажатии на кнопку возврата
        * в интерфейсе самого телефона мы не можем вернуться к этому экрану, так как его тупо нет*/
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
    //  функция для скрытия верхней панели, где выводятся уведомления мобилы, время зарядка и пр.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }
}