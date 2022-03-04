package melon.transformers

import melon.abstractions.Transformer
import melon.markdown.MarkdownNode

class Markdown2StringTransformer : Transformer<MarkdownNode, String> {
    override fun transform(from: MarkdownNode): String = from.toString()
}