package melon.html

enum class HtmlTokenType {
    TagOpen,
    Text,
    TagClose
}

class HtmlToken(val type: HtmlTokenType, val value: String) {
    override fun toString(): String = "$type::$value"
}