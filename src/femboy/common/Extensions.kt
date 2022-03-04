package femboy.utils

import java.awt.List
import kotlin.math.abs
import kotlin.math.log10
import kotlin.math.pow

fun Int.length(): Int {
    val num = abs(this)

    if (num == 0) return 1

    return (log10(num.toDouble()) + 1).toInt()
}

fun Int.digitBy(index: Int): Int {
    var i = index
    val length = this.length()

    if (length + i < 0 || i >= length) return -1

    if (i < 0) i += length

    return (this / 10.0.pow(length - i - 1) % 10.0).toInt()
}