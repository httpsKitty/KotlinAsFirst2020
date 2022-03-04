package melon.html

import melon.abstractions.Node
import melon.utils.NodeTreeUtil
import java.lang.StringBuilder

abstract class HtmlNode(children: List<HtmlNode> = emptyList()) : Node<HtmlNode>(children)

class ValueHtmlNode(val value: String) : HtmlNode() {
    override fun toString(): String = value
}

class SingleHtmlNode(val tag: String, child: HtmlNode) : HtmlNode(listOf(child)) {
    override fun toString(): String {
        val space = "    "
        val spacer = space.repeat(NodeTreeUtil.findNodesToProgenitor(this))

        val builder = StringBuilder()

        builder.append(spacer, "<", tag, ">", children[0], "</", tag, ">")

        return builder.toString()
    }
}

class MultiHtmlNode(val tag: String, children: List<HtmlNode> = emptyList(), val newLine: Boolean = true): HtmlNode(children) {
    override fun toString(): String {
        val space = "    "
        val spacer = space.repeat(NodeTreeUtil.findNodesToProgenitor(this))

        val builder = StringBuilder()

        builder.append(spacer, "<", tag, ">")
        if (newLine) builder.append('\n')

        for (child in children) builder.append(child.toString(), '\n')

        builder.append(spacer, "</", tag, ">")

        return builder.toString()
    }
}