@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import femboy.utils.digitBy
import femboy.utils.length
import lesson1.task1.discriminant
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = TODO()

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = TODO()

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> = TODO()

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int = TODO()

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int = TODO()

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> = TODO()

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> = TODO()

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = TODO()

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> = TODO()

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String = TODO()

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int = TODO()

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int = TODO()

fun arabicToRomanWithPattern(
    number: Int,
    str: String,
    fourStr: String,
    fiveStr: String,
    nineStr: String
): String {
    var buffer = ""
    var digit = number

    if (digit !in 1..9) return buffer

    when (digit) {
        4 -> buffer += fourStr
        9 -> buffer += nineStr
        else -> {
            if (digit >= 5) {
                digit -= 5
                buffer += fiveStr
            }

            for (i in 1..digit) {
                buffer += str
            }
        }
    }

    return buffer
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(number: Int): String {
    return arabicToRomanWithPattern(number.digitBy(-4), "M", "M", "M", "M") +
            arabicToRomanWithPattern(number.digitBy(-3), "C", "CD", "D", "CM") +
            arabicToRomanWithPattern(number.digitBy(-2), "X", "XL", "L", "XC") +
            arabicToRomanWithPattern(number.digitBy(-1), "I", "IV", "V", "IX")
}

class NumberWords(
    val w1: String?,
    val w5: String?,
    val w10: String?,
    val w100: String?,
    val w1000: String?
) {
    fun byPosition(position: Int): String? {
        return when (position) {
            1 -> w1
            2 -> w10
            3 -> w100
            4 -> w1000
            else -> null
        }
    }
}

class NumbersWords(
    val num1: NumberWords,
    val num2: NumberWords,
    val num3: NumberWords,
    val num4: NumberWords,
    val num5: NumberWords,
    val num6: NumberWords,
    val num7: NumberWords,
    val num8: NumberWords,
    val num9: NumberWords
) {
    fun byNumber(number: Int): NumberWords? {
        return when (number) {
            1 -> num1
            2 -> num2
            3 -> num3
            4 -> num4
            5 -> num5
            6 -> num6
            7 -> num7
            8 -> num8
            9 -> num9
            else -> null
        }
    }
}

fun addIfNotNull(buffer: MutableList<String>, value: String?) {
    if (value != null) {
        buffer.add(0, value)
    }
}

private val numbersWords = NumbersWords(
    NumberWords(
        "один",
        "одиннадцать",
        "десять",
        "сто",
        "одна"
    ),
    NumberWords(
        "два",
        "двенадцать",
        "двадцать",
        "двести",
        "две"
    ),
    NumberWords(
        "три",
        "тринадцать",
        "тридцать",
        "триста",
        null
    ),
    NumberWords(
        "четыре",
        "четырнадцать",
        "сорок",
        "четыреста",
        null
    ),
    NumberWords(
        "пять",
        "пятнадцать",
        "пятьдесят",
        "пятьсот",
        null
    ),
    NumberWords(
        "шесть",
        "шестнадцать",
        "шестьдесят",
        "шестьсот",
        null
    ),
    NumberWords(
        "семь",
        "семнадцать",
        "семьдесят",
        "семьсот",
        null
    ),
    NumberWords(
        "восемь",
        "восемнадцать",
        "восемьдесят",
        "восемьсот",
        null
    ),
    NumberWords(
        "девять",
        "девятнадцать",
        "девяносто",
        "девятьсот",
        null
    )
)

fun addW1Digit(buffer: MutableList<String>, digit: Int, digitNext: Int) {
    if (digitNext != 1) addIfNotNull(buffer, numbersWords.byNumber(digit)?.w1)
}

fun addW10Digit(buffer: MutableList<String>, digit: Int, digitPre: Int) {
    if (digit == 1 && digitPre != 0) {
        addIfNotNull(buffer, numbersWords.byNumber(digitPre)?.w5)
    } else {
        addIfNotNull(buffer, numbersWords.byNumber(digit)?.w10)
    }
}

fun addW100Digit(buffer: MutableList<String>, digit: Int) {
    addIfNotNull(buffer, numbersWords.byNumber(digit)?.w100)
}

fun addW1000Digit(buffer: MutableList<String>, digit: Int, digitNext: Int) {
    if (digitNext == 1) return addIfNotNull(buffer, "тысяч")
    else {
        when (digit) {
            1 -> addIfNotNull(buffer, "тысяча")
            in 1..4 -> addIfNotNull(buffer, "тысячи")
            else -> addIfNotNull(buffer, "тысяч")
        }

        var w1Num = numbersWords.byNumber(digit)?.w1000

        if (w1Num == null) w1Num = numbersWords.byNumber(digit)?.w1

        addIfNotNull(buffer, w1Num)
    }
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(number: Int): String {
    val buffer = arrayListOf<String>()
    val length = number.length()

    addW1Digit(buffer, number.digitBy(-1), number.digitBy(-2))
    if (length >= 2) addW10Digit(buffer, number.digitBy(-2), number.digitBy(-1))
    if (length >= 3) addW100Digit(buffer, number.digitBy(-3))
    if (length >= 4) addW1000Digit(buffer, number.digitBy(-4), number.digitBy(-5))
    if (length >= 5) addW10Digit(buffer, number.digitBy(-5), number.digitBy(-4))
    if (length >= 6) addW100Digit(buffer, number.digitBy(-6))

    return buffer.joinToString(" ")
}