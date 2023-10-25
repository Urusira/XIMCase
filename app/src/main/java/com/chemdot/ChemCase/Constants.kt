package com.chemdot.ChemCase

import android.util.Log
import kotlin.random.Random

//  Позволяет сформировать новый объект Constants, в него мы засунем вопросы
object Constants {

    //  Функция получения вопроса
    fun getQuestions(typeQ: String): ArrayList<Question>{
        val maxQuestions = 10                       //  Максимум выводимых вопросов
        val questionsList = ArrayList<Question>()   //  Объявляем список вопросов
        val getQuests = getQuests()             //  Обращаюсь к функции GetQuests и в одноимённую переменную помещаю все вопросы из функции
        val nonmutablefilteredQuests = (  getQuests.filter { it.type == "$typeQ" })   /*  Фильтрую вопросы по типу. typeQ - переменная, которую
                                                                            мы передаём текущей функции при её вызове*/
        val filteredQuests = ArrayList<Question>()

        nonmutablefilteredQuests.forEach {
            filteredQuests.add(it)
        }

        filteredQuests.shuffle()

        //  Здесь мы перебираем все отсортированные вопросы и добавляем их в список
        filteredQuests.forEach {
            questionsList.add(it)

            //  Если мы достигли предела количества вопросов или вышли за него, то немедленно возвращаем перемешанный список
            if (questionsList.size >= maxQuestions) {
                return questionsList    //  Возвращаем рандомизированный список вопросов нужной категории обратно. Туда, откуда его и призвали
            }
        }
        return questionsList    //  Возвращаем рандомизированный список вопросов нужной категории обратно. Туда, откуда его и призвали
    }
    fun getQuests(): ArrayList<Question>{
        var idishnik = 0
        val questions = ArrayList<Question>()   //  Формируем местный список вопросов, куда добавляем наши вопросы

        //  Формируем сам вопрос - айди, категория, текст вопроса, варианты ответа с 1 по 4, номер верного ответа, пояснение к нему
        idishnik++
        questions.add(Question(
            idishnik,                                                           //  Айдишник вопроса
            "metal",                                                        //  Категория вопроса
            "Во  многие современные стиральные порошки добавляют безводный сульфат " +
                    "натрия для сохранения сыпучести. За счет какого процесса эта соль " +
                    "предотвращает слеживаемость порошков?",                                 //   Текст вопроса
            "Совместим с солями извести",                                          //   Первый вариант ответа
            "При соприкосновении с водой образует комплекс",                                               //   Второй вариант ответа
            "Образует комплекс с силикатом натрия и поглощает воду",                                            //   Третий вариант ответа
            "Образует кристаллогидрат и связывает молекулы воды",                                             //   Четвёртый вариант ответа
            4,                                                   //   Номер верного варианта ответа
            "Сульфат натрия легко образует очень прочный кристаллогидрат состава Na2SO4.10H2O, " +
                    "связывая 10 молекул воды. Поэтому, безводный Na2SO4 хорошо предотвращает влагу, " +
                    "предотвращая слёживаемость порошков"       //   Пояснение к верному ответу
        ))
        //  Возвращаем этот массив вопросов
        idishnik++
        questions.add(Question(
            idishnik,                                                           //  Айдишник вопроса
            "metal",                                                        //  Категория вопроса
            "Насморк или радикулит можно вылечить при помощи прикладывания к больному " +
                    "месту тканевого мешочка с раскалённой  поваренной  солью. Какие свойства " +
                    "поваренной соли использованы в этом рецепте?",                                 //   Текст вопроса
            "Увеличение площади при нагревании",                                          //   Первый вариант ответа
            "Образование сложных соединений при нагревании",                                               //   Второй вариант ответа
            "Растворимость в воде и средняя теплопроводность",                                            //   Третий вариант ответа
            "Высокая теплоёмкость",                                             //   Четвёртый вариант ответа
            4,                                                   //   Номер верного варианта ответа
            "В данном случае играют роль не химические, а физические свойства " +
                    "хлорида натрия: его довольно высокая теплоемкость"       //   Пояснение к верному ответу
        ))
        idishnik++
        questions.add(Question(
            idishnik,                                                           //  Айдишник вопроса
            "non_metal",                                                        //  Категория вопроса
            "Какими веществами с окислительными или " +
                    "восстановительными свойствами  можно быстро удалить йод с ткани?",                                 //   Текст вопроса
            "Этиловый спирт",                                          //   Первый вариант ответа
            "Марганцовка",                                               //   Второй вариант ответа
            "Перекись водорода",                                            //   Третий вариант ответа
            "Растворы гипохлоритов",                                             //   Четвёртый вариант ответа
            4,                                                   //   Номер верного варианта ответа
            "В качестве хлорсодержащего отбеливающего" +
                    " средства чаще всего используют водные растворы гипохлоритов – " +
                    "солей хлорноватистой кислоты, которые пропускают пропусканием хлора " +
                    "через раствор щелочи"       //   Пояснение к верному ответу
        ))
        idishnik++
        questions.add(Question(
            idishnik,                                                           //  Айдишник вопроса
            "non_metal",                                                        //  Категория вопроса
            "Какое количество монофторфосфата натрия Na2PO3F содержится в тюбике " +
                    "зубной пасты весом 75 граммов, если на упаковке указано: «Содержание " +
                    "активного фтора 0,15%»? Стоматологи рекомендуют ежегодно потреблять в " +
                    "виде зубной пасты примерно 1,5 грамма активного фтора. Сколько тюбиков " +
                    "зубной пасты нужно использовать в течение года, чтобы обеспечить эту норму?",                                 //   Текст вопроса
            "15,4 тюбиков",                                          //   Первый вариант ответа
            "12,3 тюбиков",                                               //   Второй вариант ответа
            "13,6 тюбиков",                                            //   Третий вариант ответа
            "13,0 тюбиков",                                             //   Четвёртый вариант ответа
            3,                                                   //   Номер верного варианта ответа
            "В 75 граммах пасты содержится (75 . 0,15) : 100 = 0,11 г" +
                    " активного фтора. По норме необходимо израсходовать 1,5:0,11 = 13,6" +
                    " тюбиков зубной пасты за год, т.е. примерно по одному тюбику в месяц"       //   Пояснение к верному ответу
        ))
        idishnik++
        questions.add(Question(
            idishnik,                                                           //  Айдишник вопроса
            "",                                                        //  Категория вопроса
            "Нитрафен – пестицид широкого спектра действия – получают " +
                    "нитрованием каменноугольной фенолов. Можно ли  написать химическую " +
                    "формулу нитрафена?",                                 //   Текст вопроса
            "Невозможно",                                          //   Первый вариант ответа
            "Вполне вероятно",                                               //   Второй вариант ответа
            "Если подумать, то да",                                            //   Третий вариант ответа
            "Можно",                                             //   Четвёртый вариант ответа
            1,                                                   //   Номер верного варианта ответа
            "Нитрафен  не является чистым веществом, а представляет" +
                    " собой смесь нескольких веществ одной химической природы." +
                    " Каменноугольные фенолы – смесь моно-, ди- и трифенолов, а" +
                    " также крезола, при их нитровании также образуется смесь продуктов," +
                    " в молекулах которых содержится различное число нитрогрупп"       //   Пояснение к верному ответу
        ))
        idishnik++
        questions.add(Question(
            idishnik,                                                           //  Айдишник вопроса
            "",                                                        //  Категория вопроса
            "Можно ли удалить пятна  подсолнечного масла " +
                    "и йода физическим способом не прибегая к помощи химии ?",                                 //   Текст вопроса
            "Не знаю",                                          //   Первый вариант ответа
            "Да, можно",                                               //   Второй вариант ответа
            "Частично",                                            //   Третий вариант ответа
            "Нет, нельзя",                                             //   Четвёртый вариант ответа
            2,                                                   //   Номер верного варианта ответа
            "Прежде всего, следует попытаться оба пятна удалить органическим растворителем " +
                    "– бензином, керосином. В данном случае будет происходить физический процесс растворения. " +
                    "И йод, и подсолнечное масло хорошо растворяются в органических растворителях.\n" +
                    "Пятно йода проще всего удалить за счет способности этого вещества легко возгоняться." +
                    " Ткань следует слегка подогреть, например утюгом или на батарее отопления, - это ускорит процесс возгонки\n"       //   Пояснение к верному ответу
        ))
        idishnik++
        questions.add(Question(
            idishnik,                                                           //  Айдишник вопроса
            "",                                                        //  Категория вопроса
            "Почему для зубов особенно опасны остатки пищи, " +
                    "которая содержит много углеводов?",                                 //   Текст вопроса
            "Имеют простую структуру, поэтому плохо растворяются в воде",                                          //   Первый вариант ответа
            "Имеют высокую температуру плавления",                                               //   Второй вариант ответа
            "Являются гидрофобами",                                            //   Третий вариант ответа
            "Подвергаются молочнокислому брожению",                                             //   Четвёртый вариант ответа
            4,                                                   //   Номер верного варианта ответа
            "Углеводы подвергаются молочнокислому брожению с образованием" +
                    " молочной кислоты, разрушающей зубную эмаль"       //   Пояснение к верному ответу
        ))

        return questions
    }
}