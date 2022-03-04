package femboy

import femboy.chess.KingPiece
import femboy.utils.digitBy
import femboy.utils.length
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import kotlin.math.PI

class Tests {
    @Test
    fun intLength() {
        assertEquals(1, 1.length())
        assertEquals(2, 11.length())
        assertEquals(1, 0.length())
    }

    @Test
    fun intDigitBy() {
        assertEquals(-1, 9.digitBy(1))
        assertEquals(-1, 9.digitBy(-2))
        assertEquals(-1, 9.digitBy(2))
        assertEquals(-1, 9.digitBy(-3))
        assertEquals(-1, 999.digitBy(3))
        assertEquals(-1, 999.digitBy(-4))
        assertEquals(-1, 999.digitBy(5))
        assertEquals(-1, 999.digitBy(-6))
        assertEquals(9, 9.digitBy(0))
        assertEquals(9, 9.digitBy(-1))
        assertEquals(2, 234.digitBy(0))
        assertEquals(3, 234.digitBy(1))
        assertEquals(4, 234.digitBy(2))
        assertEquals(2, 234.digitBy(-3))
        assertEquals(3, 234.digitBy(-2))
        assertEquals(4, 234.digitBy(-1))
    }

    @Test
    fun kingTests() {
        val king = KingPiece(0, 0);

        assertFalse(king.canHit(KingPiece(2, 2)))
        assertFalse(king.canHit(KingPiece(-2, -2)))
        assertFalse(king.canHit(KingPiece(2, 0)))
        assertFalse(king.canHit(KingPiece(0, 2)))
        assertTrue(king.canHit(KingPiece(1, 1)))
        assertTrue(king.canHit(KingPiece(-1, -1)))
        assertTrue(king.canHit(KingPiece(-1, 1)))
        assertTrue(king.canHit(KingPiece(1, -1)))
        assertTrue(king.canHit(KingPiece(1, 0)))
        assertTrue(king.canHit(KingPiece(0, 1)))
        assertTrue(king.canHit(KingPiece(0, 0)))
    }

    @Test
    fun testSubstring() {
        val regex = """^(\d{1,2})[.](\d{1,2})[.](\d{1,2})$""".toRegex()

        assertTrue(regex.matches("22.33.21"))
        assertFalse(regex.matches("22.33.223"))
        assertFalse(regex.matches("22.33"))
    }
}