package melon.transformers

import melon.abstractions.Transformer
import melon.html.HtmlNode
import melon.markdown.MarkdownNode

class Html2MarkdownTransformer : Transformer<HtmlNode, MarkdownNode> {
    override fun transform(from: HtmlNode): MarkdownNode = TODO()
}