package melon.transformers

import melon.abstractions.Transformer
import melon.html.HtmlNode
import melon.html.HtmlParser

class String2HtmlTransformer : Transformer<String, HtmlNode> {
    override fun transform(from: String): HtmlNode = TODO()
}