package com.chemdot.ChemCase

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.chemdot.ChemCase.databinding.ActivityGameCycleBinding

class GameCycle : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityGameCycleBinding

    // Переменные. 1 - позиция текущая, 2 - список вопросов, по-умолчанию список пуст. Ну и 3 - текущий выбранный вопрос. Ну то есть накоторый нажал пользователь
    private var CurrentPosition:Int = 1
    private var QuestionsList: ArrayList<Question>? = null
    private var SelectedOptionPosition : Int = 0
    private var blocker = false
    private var CorrAnswersCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameCycleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val cat = intent.getStringExtra("category")

        QuestionsList = Constants.getQuestions("$cat")

        setQuestions()

        binding.optionOne.setOnClickListener(this)
        binding.optionTwo.setOnClickListener(this)
        binding.optionThree.setOnClickListener(this)
        binding.optionFour.setOnClickListener(this)
        binding.nextQuestion.setOnClickListener(this)

        // Уже описал функцию в велком пейдж
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }

    private fun setQuestions(){
        val qquestion = QuestionsList!![CurrentPosition-1]  //  в переменную квесчен помещаем текуший вопрос из списка вопросов
        blocker = false
        defaultOptionsView()        //  Вызываем метод перекраса кнопок в стандартный цвет

        if(CurrentPosition == QuestionsList!!.size) {
            binding.nextQuestion.text = "Завершить"
        } else {
            binding.nextQuestion.text = "Далее"
        }

        //  Получаем текущий прогресс из полоски прогресса
        binding.progressBar.progress = CurrentPosition
        //  В качестве текста правее полоски пишем текущую позицию из максимально возможной, для этой полоски
        binding.progress.text = "$CurrentPosition" + "/" + QuestionsList!!.size
        binding.progressBar.max = QuestionsList!!.size

        binding.questionText.text = qquestion.question //  Помещаем в поле с вопросом сам вопрос, который вычленяли из списка вопросов в самом начале

        //  В разные варианты ответов помещаем эти самые варианты ответов
        binding.optionOne.text = qquestion.optionOne
        binding.optionTwo.text = qquestion.optionTwo
        binding.optionThree.text = qquestion.optionThree
        binding.optionFour.text = qquestion.optionFour
    }

    //  Функция для сброса оформления кнопок. Присваивает им первоначальный внешний вид.
    private fun defaultOptionsView(){
        val options = ArrayList<TextView>() //  Получаем список кнопок
        options.add(0, binding.optionOne)
        options.add(1, binding.optionTwo)
        options.add(2, binding.optionThree)
        options.add(3, binding.optionFour)

        // В цикле проходимся по этим кнопкам
        for (option in options) {
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
            if(blocker == false){
                when (v?.id) {
                    R.id.optionOne -> {
                        selectedOptionView(
                            binding.optionOne,
                            1
                        )    //  Считай тот же свич-кейс. Если нажали на первую кнопку, отправляем её в функцию ниже и говорим, что это первая кнопка
                        binding.nextQuestion.text = "Подтвердить"
                    }

                    R.id.optionTwo -> {
                        selectedOptionView(binding.optionTwo, 2)    //  Дальше по аналогии
                        binding.nextQuestion.text = "Подтвердить"
                    }

                    R.id.optionThree -> {
                        selectedOptionView(binding.optionThree, 3)
                        binding.nextQuestion.text = "Подтвердить"
                    }

                    R.id.optionFour -> {
                        selectedOptionView(binding.optionFour, 4)
                        binding.nextQuestion.text = "Подтвердить"
                    }

                    R.id.nextQuestion -> {                           // Здесь алгоритм кнопки "далее"
                        blocker = true
                        if (SelectedOptionPosition == 0) {
                            CurrentPosition++
                            binding.explane.text = ""

                            when {
                                CurrentPosition <= QuestionsList!!.size -> {// Если текущая позиция меньше суммарного количества вопросов
                                    setQuestions()//Выводим вопросы дальше
                                }

                                else -> {  //В противном случае вызываем экран, где сообщаем пользователю, что он прошёл тест
                                    val intent = Intent(this, DialogendActivity::class.java)
                                    intent.putExtra("CurrAnswers","$CorrAnswersCounter")
                                    intent.putExtra("AnswersCount", "${QuestionsList!!.size}")
                                    startActivity(intent)
                                    finish()
                                }
                            }
                        } else {
                            val question = QuestionsList?.get(CurrentPosition - 1)
                            if (question!!.correctAnswer != SelectedOptionPosition) {
                                answerView(SelectedOptionPosition, R.drawable.uncorrect_option_bg)
                            } else {
                                CorrAnswersCounter++
                            }
                            answerView(question.correctAnswer, R.drawable.correct_option_bg)

                            if (CurrentPosition == QuestionsList!!.size) {
                                binding.nextQuestion.text = "Завершить"
                            } else {
                                binding.nextQuestion.text = "Далее"
                            }
                            binding.explane.text = question.explanation
                            blocker = true
                            SelectedOptionPosition = 0
                        }
                    }
                }
            }
            else {
                when (v?.id) {
                    R.id.nextQuestion -> {                           // Здесь алгоритм кнопки "далее"
                        blocker = true
                        if (SelectedOptionPosition == 0) {
                            CurrentPosition++
                            binding.explane.text = ""

                            when {
                                CurrentPosition <= QuestionsList!!.size -> {// Если текущая позиция меньше суммарного количества вопросов
                                    setQuestions()//Выводим вопросы дальше
                                }

                                else -> {  //В противном случае вызываем экран, где сообщаем пользователю, что он прошёл тест
                                    val intent = Intent(this, DialogendActivity::class.java)
                                    intent.putExtra("CurrAnswers","$CorrAnswersCounter")
                                    intent.putExtra("AnswersCount", "${QuestionsList!!.size}")
                                    startActivity(intent)
                                    finish()
                                }
                            }
                        } else {
                            val question = QuestionsList?.get(CurrentPosition - 1)
                            if (question!!.correctAnswer != SelectedOptionPosition) {
                                answerView(SelectedOptionPosition, R.drawable.uncorrect_option_bg)
                            } else {
                                CorrAnswersCounter++
                            }
                            answerView(question.correctAnswer, R.drawable.correct_option_bg)

                            if (CurrentPosition == QuestionsList!!.size) {
                                binding.nextQuestion.text = "Завершить"
                            } else {
                                binding.nextQuestion.text = "Далее"
                            }
                            binding.explane.text = question.explanation
                            blocker = true
                            SelectedOptionPosition = 0
                        }
                    }
                }
            }
        }

    private fun answerView(answer: Int, drawableView: Int) {
        when(answer){
            1 ->{
                binding.optionOne.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 ->{
                binding.optionTwo.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 ->{
                binding.optionThree.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4 ->{
                binding.optionFour.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }

    //  Функция для обработки нажатия на кнопку
    private fun selectedOptionView(tv: TextView, selectedOptionNumber: Int) {
        defaultOptionsView()    //  Сначала сбрасываем к чертям оформление кнопок
        SelectedOptionPosition = selectedOptionNumber  //  Запоминаем, какую кнопку нажали

        tv.setTextColor(Color.parseColor("#222222"))    //  Задаём цвет шрифта
        tv.setTypeface(tv.typeface, Typeface.BOLD)                  // Задаём стандартный шрифт
        tv.background = ContextCompat.getDrawable(      //  Присваиваем им оформление sel_option_bg - нажатой кнопки
            this,
            R.drawable.sel_option_bg
        )
    }
}
