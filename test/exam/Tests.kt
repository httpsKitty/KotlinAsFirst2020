package exam

import femboy.utils.length
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class Tests {
    @Test
    fun test1() {
        assertEquals(sortTeamsByScore("a 1:0 b", listOf("a")), listOf("a"))

        assertEquals(sortTeamsByScore("a 1:0 c", listOf("a", "b")), listOf("a", "b"))

        assertEquals(sortTeamsByScore("a 1:0 b; b 0:0 a; c 4:2 b", listOf("a", "b")), listOf("a", "b"))
        assertEquals(sortTeamsByScore("a 1:0 b; b 0:0 a; c 4:2 b", listOf("a", "c", "b")), listOf("a", "c", "b"))

        assertThrows<IllegalArgumentException> { sortTeamsByScore("a1:0 b", listOf()) }
        assertThrows<IllegalArgumentException> { sortTeamsByScore("a 1: 0 b", listOf()) }
        assertThrows<IllegalArgumentException> { sortTeamsByScore("a 1:f b", listOf()) }
    }
}