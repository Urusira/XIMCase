package com.chemdot.ChemCase

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.chemdot.ChemCase.databinding.ActivityGameCycleBinding

class GameCycle : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityGameCycleBinding

    // Переменные. 1 - позиция текущая, 2 - список вопросов, по-умолчанию список пуст. Ну и 3 - текущий выбранный вопрос. Ну то есть накоторый нажал пользователь
    private var CurrentPosition:Int = 1
    private var QuestionsList: ArrayList<Question>? = null
    private var SelectedOptionPosition : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameCycleBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_game_cycle)

        QuestionsList = Constants.getQuestions()


        setQuestions()
        Log.i("ёпты бля`", "${QuestionsList!!}")

        val optionOne = findViewById<TextView>(R.id.optionOne)   //  В переменную логин помещаем объект "кнопка" с заданным айдишником
        val optionTwo = findViewById<TextView>(R.id.optionTwo)   //  В переменную логин помещаем объект "кнопка" с заданным айдишником
        val optionThree = findViewById<TextView>(R.id.optionThree)   //  В переменную логин помещаем объект "кнопка" с заданным айдишником
        val optionFour = findViewById<TextView>(R.id.optionFour)   //  В переменную логин помещаем объект "кнопка" с заданным айдишником

        optionOne.setOnClickListener(this)
        optionTwo.setOnClickListener(this)
        optionThree.setOnClickListener(this)
        optionFour.setOnClickListener(this)

        // Уже описал функцию в велком пейдж
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }

    private fun setQuestions(){
        Log.i("ёпты бля`", "ДА СУКА ХВАТИТ СЕТАТЬ ВОПРОСЫ БЛЯДОТА ЁПТ")
        CurrentPosition = 1 //  Инициируем переменную "текущая позиция"
        val qquestion = QuestionsList!![CurrentPosition-1]  //  в переменную квесчен помещаем текуший вопрос из списка вопросов

        defaultOptionsView()        //  Вызываем метод перекраса кнопок в стандартный цвет

        //  Получаем текущий прогресс из полоски прогресса
        binding.progressBar.progress = CurrentPosition
        //  В качестве текста правее полоски пишем текущую позицию из максимально возможной, для этой полоски
        binding.progress.text = "$CurrentPosition" + "/" + binding.progressBar.max

        binding.questionText.text = qquestion.question //  Помещаем в поле с вопросом сам вопрос, который вычленяли из списка вопросов в самом начале

        //  В разные варианты ответов помещаем эти самые варианты ответов
        binding.optionOne.text = qquestion.optionOne
        binding.optionTwo.text = qquestion.optionTwo
        binding.optionThree.text = qquestion.optionThree
        binding.optionFour.text = qquestion.optionFour
    }

    //  Функция для сброса оформления кнопок. Присваивает им первоначальный внешний вид.
    private fun defaultOptionsView(){
        Log.i("ёпты бля`", "ХЕНДЕХОХ ЁПТЫ БЛЯ")
        val options = ArrayList<TextView>() //  Получаем список кнопок
        options.add(0, binding.optionOne)
        options.add(1, binding.optionTwo)
        options.add(2, binding.optionThree)
        options.add(3, binding.optionFour)
        Log.i("ёпты бля`", "${options.size}")

        // В цикле проходимся по этим кнопкам
        for (option in options) {
            Log.i("ёпты бля`", "ААААА ПЕРЕКРАААС В СТАНДААРТ ААА")
            option.setTextColor(Color.parseColor("#444444"))    //  Задаём цвет шрифта
            option.typeface = Typeface.DEFAULT                  // Задаём стандартный шрифт
            option.background = ContextCompat.getDrawable(      //  Присваиваем им оформление option_bg
                this,
                R.drawable.option_bg
            )
        }
    }

    // Эта функция позволяет фиксировать те моменты, когда мы нажимаем на кнопку возврата на самой мобиле
    override fun onBackPressed() {
        Log.i("ёпты бля`", "Так блэт. Ты куда собрался? У нас криминал, ты когда по коням дашь?")

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

    //  Оверрайд - перезапись, позволяет перезаписать уже существующую в программе функцию, например тут
    //  мы дополняем функцию ОнКликЛистенер, она не позволяет первоначально отслеживать нажатия на текст
    //  И не знает, что с этим делать. Тут мы говорим программе, как работать с этими объектами
    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.optionOne ->{
                Log.i("ёпты бля`", "ПЕРВАЯ КНОПКА НАЖАТА")
                selectedOptionView(binding.optionOne, 1)    //  Считай тот же свич-кейс. Если нажали на первую кнопку, отправляем её в функцию ниже и говорим, что это первая кнопка
            }
            R.id.optionTwo ->{
                Log.i("ёпты бля`", "ВТОРАЯ КНОПКА ПОШЛА")
                selectedOptionView(binding.optionTwo, 2)    //  Дальше по аналогии
            }
            R.id.optionThree ->{
                Log.i("ёпты бля`", "ВНИМАНИЕ ВСЕМ ПОСТАМ НАЖАТА ТРЕТЬЯ КНОПКА")
                selectedOptionView(binding.optionThree, 3)
            }
            R.id.optionFour ->{
                Log.i("ёпты бля`", "ААААААААААААААААААА")
                selectedOptionView(binding.optionFour, 4)
            }
        }
    }

    //  Функция для обработки нажатия на кнопку
    private fun selectedOptionView(tv: TextView, selectedOptionNumber: Int) {
        Log.i("ёпты бля`", "АААААААА ПЕЕРЕКРАААААААААААС СУУУКА ДАЖЕ НЕ В ДЕФОЛТ ДА БЛЯЯЯ")
        defaultOptionsView()    //  Сначала сбрасываем к чертям оформление кнопок
        SelectedOptionPosition = selectedOptionNumber  //  Запоминаем, какую кнопку нажали

        tv.setTextColor(Color.parseColor("#222222"))    //  Задаём цвет шрифта
        tv.setTypeface(tv.typeface, Typeface.BOLD)                  // Задаём стандартный шрифт
        tv.background = ContextCompat.getDrawable(      //  Присваиваем им оформление sel_option_bg - нажатой кнопки
            this,
            R.drawable.sel_option_bg
        )
        Log.i("ёпты бля`", "Сука, опять перекрас...")
    }
}
