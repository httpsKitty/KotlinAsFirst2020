package melon.markdown

abstract class MarkdownToken(val value: String) {
    override fun toString(): String = "<${this::class.simpleName}> value: '$value'"
}

class MarkdownTextToken(value: String) : MarkdownToken(value)

class MarkdownListToken(value: String, val deep: Int = 0) : MarkdownToken(value) {
    val isStar get() = value == "*"

    val isNumber get() = !isStar

    override fun toString(): String {
        return super.toString() + " deep: $deep"
    }
}

class MarkdownTagToken(value: String) : MarkdownToken(value) {
    val isBold get() = value == "**"

    val isItalic get() = value == "*"

    val isCross get() = value == "~~"
}

class MarkdownNewlineToken : MarkdownToken("") {
    override fun toString(): String = "<${this::class.simpleName}>"
}