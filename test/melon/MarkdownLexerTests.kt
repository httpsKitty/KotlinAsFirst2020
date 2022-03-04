package melon

import junit.framework.Assert
import melon.markdown.MarkdownLexer
import melon.markdown.MarkdownNode
import melon.markdown.MarkdownParser
import melon.transformers.Markdown2HtmlTransformer
import melon.transformers.String2MarkdownTransformer
import org.junit.jupiter.api.Test
import java.io.File

class MarkdownLexerTests {
    @Test
    fun test1() {
        val text = File("input/m1.test.md").readText()

        val tokens = MarkdownLexer().lex(text)

        println(tokens.map { it.toString() + '\n' })

        val rootNode = MarkdownParser().parse(tokens)

        println(rootNode)
    }

    @Test
    fun test2() {
        val text = File("input/m2.test.md").readText()

        val tokens = MarkdownLexer().lex(text)

        println(tokens.map { it.toString() + '\n' })

        val rootNode = MarkdownParser().parse(tokens)

        println(rootNode)
    }

    @Test
    fun test3() {
        val text = File("input/m3.test.md").readText()

        val tokens = MarkdownLexer().lex(text)

        println(tokens.map { it.toString() + '\n' })

        val rootNode = MarkdownParser().parse(tokens)

        println(rootNode)
    }

    @Test
    fun test4() {
        val text = File("input/m4.test.md").readText()

        val tokens = MarkdownLexer().lex(text)

        println(tokens.map { it.toString() + '\n' })
    }

    @Test
    fun test5() {
        val text = "94;:*0|iqb4fe*Qbznh:\\n 0,xI2RpAro[v"

        val tokens = MarkdownLexer().lex(text)

        println(tokens.map { it.toString() + '\n' })

        val mNode = String2MarkdownTransformer().transform(text)
        val hNode = Markdown2HtmlTransformer().transform(mNode)

        println(hNode)
    }

    @Test
    fun test6() {
        val text = File("input/m5.test").readText()

        val tokens = MarkdownLexer().lex("")

        println(tokens.map { it.toString() + '\n' })

        val mNode = String2MarkdownTransformer().transform(text)
        val hNode = Markdown2HtmlTransformer().transform(mNode)

        println(hNode)
    }
}