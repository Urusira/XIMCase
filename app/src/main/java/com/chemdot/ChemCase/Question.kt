package com.chemdot.ChemCase

//  Тут класс данных. Считай это шаблоном вопроса, по нему в файле Constrains мы прописываем вопросы.
//  Каждый пункт, который тут перечислен - поле шаблона, по этому шаблону мы строить экземпляры, заполняя эти самые поля
data class Question(
    val id: Int,
    val type: String,
    val question: String,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswer: Int,
    val explanation: String
)