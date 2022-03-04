@file:Suppress("UNUSED_PARAMETER")

package lesson1.task1

import java.awt.Point
import java.awt.geom.Point2D
import java.util.*
import kotlin.math.*

// Урок 1: простые функции
// Максимальное количество баллов = 5
// Рекомендуемое количество баллов = 4

/**
 * Пример
 *
 * Вычисление квадрата целого числа
 */
fun sqr(x: Int) = x * x

/**
 * Пример
 *
 * Вычисление квадрата вещественного числа
 */
fun sqr(x: Double) = x * x

/**
 * Пример
 *
 * Вычисление дискриминанта квадратного уравнения
 */
fun discriminant(a: Double, b: Double, c: Double) = sqr(b) - 4 * a * c

/**
 * Пример
 *
 * Поиск одного из корней квадратного уравнения
 */
fun quadraticEquationRoot(a: Double, b: Double, c: Double) =
    (-b + sqrt(discriminant(a, b, c))) / (2 * a)

/**
 * Пример
 *
 * Поиск произведения корней квадратного уравнения
 */
fun quadraticRootProduct(a: Double, b: Double, c: Double): Double {
    val sd = sqrt(discriminant(a, b, c))
    val x1 = (-b + sd) / (2 * a)
    val x2 = (-b - sd) / (2 * a)
    return x1 * x2 // Результат
}

/**
 * Пример главной функции
 */
fun main() {
    val x1x2 = quadraticRootProduct(1.0, 13.0, 42.0)
    println("Root product: $x1x2")
}

/**
 * Тривиальная (3 балла).
 *
 * Задача имеет повышенную стоимость как первая в списке.
 *
 * Пользователь задает время в часах, минутах и секундах, например, 8:20:35.
 * Рассчитать время в секундах, прошедшее с начала суток (30035 в данном случае).
 */
fun seconds(hours: Int, minutes: Int, seconds: Int): Int {
    val minutesSeconds = 60
    val hoursSeconds = minutesSeconds * 60

    val hoursAsSeconds = hours * hoursSeconds
    val minutesAsSeconds = minutes * minutesSeconds

    return hoursAsSeconds + minutesAsSeconds + seconds
}

/**
 * Тривиальная (1 балл)
 *
 * Пользователь задает длину отрезка в саженях, аршинах и вершках (например, 8 саженей 2 аршина 11 вершков).
 * Определить длину того же отрезка в метрах (в данном случае 18.98).
 * 1 сажень = 3 аршина = 48 вершков, 1 вершок = 4.445 см.
 */
fun lengthInMeters(sagenes: Int, arshins: Int, vershoks: Int): Double {
    val vershokMt = 4.445 / 100
    val arshinVershok = 48 / 3
    val sagenVershok = 48
    
    val arshinsAsVershoks = arshins * arshinVershok
    val sagenesAsVershoks = sagenes * sagenVershok

    val totalVershoks = vershoks + arshinsAsVershoks + sagenesAsVershoks

    val totalVershoksAsMt = totalVershoks * vershokMt
    
    return totalVershoksAsMt
}

/**
 * Тривиальная (1 балл)
 *
 * Пользователь задает угол в градусах, минутах и секундах (например, 36 градусов 14 минут 35 секунд).
 * Вывести значение того же угла в радианах (например, 0.63256).
 */
fun angleInRadian(deg: Int, min: Int, sec: Int): Double {
    val degreesAsRad = Math.toRadians(deg.toDouble())
    val minutesAsRad = Math.toRadians(min.toDouble()) / 60
    val secondsAsRad = Math.toRadians(sec.toDouble()) / 3600

    return degreesAsRad + minutesAsRad + secondsAsRad
}

/**
 * Тривиальная (1 балл)
 *
 * Найти длину отрезка, соединяющего точки на плоскости с координатами (x1, y1) и (x2, y2).
 * Например, расстояние между (3, 0) и (0, 4) равно 5
 */
fun trackLength(x1: Double, y1: Double, x2: Double, y2: Double) =
    sqrt((x1 - x2).pow(2) + (y1 - y2).pow(2))

/**
 * Простая (2 балла)
 *
 * Пользователь задает целое число, большее 100 (например, 3801).
 * Определить третью цифру справа в этом числе (в данном случае 8).
 */
fun thirdDigit(number: Int): Int {
    // NOTE: Больше 100 Но почему в тестах тестируется 100 И текст не пройден Lmao
    // Fixed: By (https://github.com/Kotlin-Polytech/KotlinAsFirst/pull/107)
    assert(number >= 100)

    val strNum = number.toString()

    val thirdRChar = strNum.reversed()[2]

    // TODO: Rd docs for char to string
    return thirdRChar.toString().toInt()
}

/**
 * Простая (2 балла)
 *
 * Поезд вышел со станции отправления в h1 часов m1 минут (например в 9:25) и
 * прибыл на станцию назначения в h2 часов m2 минут того же дня (например в 13:01).
 * Определите время поезда в пути в минутах (в данном случае 216).
 */
fun travelMinutes(hoursDepart: Int, minutesDepart: Int, hoursArrive: Int, minutesArrive: Int): Int {
    val startTimeAsMinutes = hoursDepart * 60 + minutesDepart
    val endTimeAsMinutes = hoursArrive * 60 + minutesArrive

    return endTimeAsMinutes - startTimeAsMinutes
}

/**
 * Простая (2 балла)
 *
 * Человек положил в банк сумму в s рублей под p% годовых (проценты начисляются в конце года).
 * Сколько денег будет на счету через 3 года (с учётом сложных процентов)?
 * Например, 100 рублей под 10% годовых превратятся в 133.1 рубля
 */
fun accountInThreeYears(initial: Int, percent: Int): Double {
    val percentsPerYear = percent / 100.0
    var total = initial.toDouble()

    for (i in 0..2) {
        total += total * percentsPerYear
    }

    return total
}

/**
 * Простая (2 балла)
 *
 * Пользователь задает целое трехзначное число (например, 478).
 * Необходимо вывести число, полученное из заданного перестановкой цифр в обратном порядке (например, 874).
 */
fun numberRevert(number: Int): Int {
    val strNum = number.toString()
    assert(strNum.length == 3)

    return strNum.reversed().toInt()
}
