package melon.transformers

import melon.abstractions.Transformer
import melon.html.HtmlNode

class Html2StringTransformer : Transformer<HtmlNode, String> {
    override fun transform(from: HtmlNode): String = from.toString()
}