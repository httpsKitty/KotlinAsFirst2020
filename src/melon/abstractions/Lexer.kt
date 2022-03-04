package melon.abstractions

/**
 * Лексер раскладывает строку на токены.
 *
 * T - Токены.
 */
interface Lexer<T> {
    fun lex(text: String): List<T>
}