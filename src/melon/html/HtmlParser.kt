package melon.html

import melon.abstractions.Parser

class HtmlParser : Parser<HtmlToken, HtmlNode> {
    override fun parse(tokens: List<HtmlToken>): HtmlNode? = TODO()
}