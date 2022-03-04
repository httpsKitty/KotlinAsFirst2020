package melon.transformers

import melon.abstractions.Transformer
import melon.markdown.MarkdownLexer
import melon.markdown.MarkdownNode
import melon.markdown.MarkdownParser

class String2MarkdownTransformer : Transformer<String, MarkdownNode> {
    override fun transform(from: String): MarkdownNode {
        val tokens = MarkdownLexer().lex(from)

        return MarkdownParser().parse(tokens)!!
    }
}