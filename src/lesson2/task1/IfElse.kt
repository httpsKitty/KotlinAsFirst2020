@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import femboy.chess.BishopPiece
import femboy.chess.KingPiece
import femboy.chess.RookPiece
import femboy.utils.*
import lesson1.task1.discriminant
import java.awt.Point
import java.lang.Exception
import java.rmi.server.ExportException
import kotlin.math.*

// Урок 2: ветвления (здесь), логический тип (см. 2.2).
// Максимальное количество баллов = 6
// Рекомендуемое количество баллов = 5
// Вместе с предыдущими уроками = 9/12

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая (2 балла)
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    assert(age in 0..200)

    val lastNum = age.digitBy(-1)
    val preLastNum = if (age.length() > 1) age.digitBy(-2) else null

    return when {
        preLastNum == 1 -> "$age лет"
        lastNum == 1 -> "$age год"
        lastNum in 2..4 -> "$age года"
        else -> "$age лет"
    }
}

class Line(val time: Double, val range: Double) {
    init {
        assert(time >= 0)
        assert(range >= 0)
    }
}

/**
 * Простая (2 балла)
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val lines = listOf(
        Line(t1, v1 * t1),
        Line(t2, v2 * t2),
        Line(t3, v3 * t3)
    )

    val maxRange = lines.sumOf { it.range }
    val maxDiv2Range = maxRange / 2

    var totalTime = 0.0
    var totalRange = 0.0

    for (line in lines) {
        if (totalRange + line.range > maxDiv2Range) {
            totalTime += line.time * ((line.range - ((totalRange + line.range) - maxDiv2Range)) / line.range)

            break
        } else {
            totalTime += line.time
            totalRange += line.range
        }
    }

    return totalTime
}

/**
 * Простая (2 балла)
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    val king = KingPiece(kingX, kingY)

    val rook1 = RookPiece(rookX1, rookY1)
    val rook2 = RookPiece(rookX2, rookY2)

    val canRook1HitKing = rook1.canHit(king)
    val canRook2HitKing = rook2.canHit(king)

    return when {
        canRook1HitKing && canRook2HitKing -> 3
        canRook1HitKing -> 1
        canRook2HitKing -> 2
        else -> 0
    }
}

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    val king = KingPiece(kingX, kingY)

    val rook = RookPiece(rookX, rookY)
    val bishop = BishopPiece(bishopX, bishopY)

    val canRookHitKing = rook.canHit(king)
    val canBishopHitKing = bishop.canHit(king)

    return when {
        canRookHitKing && canBishopHitKing -> 3
        canRookHitKing -> 1
        canBishopHitKing -> 2
        else -> 0
    }
}

fun getAngleDegreeFromTriangleSides(a: Double, b: Double, c: Double): Int =
    Math.toDegrees(acos((a.pow(2) + b.pow(2) - c.pow(2)) / (2 * a * b))).toInt()

/**
 * Простая (2 балла)
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int = TODO()

/**
 * Средняя (3 балла)
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    val leftA = if (a > c) c else a
    val leftB = if (a > c) d else b
    val rightA = if (a > c) a else c
    val rightB = if (a > c) b else d

    return when {
        leftB > rightB -> rightB - rightA
        leftB - rightA >= 0 -> leftB - rightA
        else -> -1
    }
}
