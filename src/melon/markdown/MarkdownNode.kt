package melon.markdown

import melon.abstractions.Node
import melon.utils.NodeTreeUtil
import java.lang.StringBuilder

abstract class MarkdownNode(children: List<MarkdownNode>) : Node<MarkdownNode>(children)

class MarkdownBranchNode(children: List<MarkdownNode> = emptyList()) : MarkdownNode(children) {
    override fun toString(): String {
        val builder = StringBuilder()

        for (child in children) builder.append(child.toString())

        return builder.toString()
    }
}

class MarkdownTextNode(val value: String) : MarkdownNode(emptyList()) {
    override fun toString(): String = value
}

class MarkdownNewlineNode : MarkdownNode(emptyList()) {
    override fun toString(): String = "\n"
}

class MarkdownTagNode(val value: String, children: List<MarkdownNode> = emptyList()) : MarkdownNode(children) {
    override fun toString(): String {
        val builder = StringBuilder()

        builder.append(value)
        for (child in children) builder.append(child.toString())
        builder.append(value)

        return builder.toString()
    }
}

class MarkdownListNode(
    val value: String,
    lineChildren: List<MarkdownNode> = emptyList(),
    listChildren: List<MarkdownNode> = emptyList()
) : MarkdownNode(
    listOf(
        MarkdownBranchNode(lineChildren),
        MarkdownBranchNode(listChildren),
    )
) {
    override fun toString(): String {
        val builder = StringBuilder()

        val deep = NodeTreeUtil.findNodesToProgenitor(this)

        builder.append(" ".repeat(deep), value, " ")
        for (child in children[0].children) builder.append(child.toString())
        builder.append('\n')
        for (child in children[1].children) builder.append(child.toString())

        return builder.toString()
    }
}