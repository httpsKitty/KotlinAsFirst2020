package melon.transformers

import melon.abstractions.Transformer
import melon.html.HtmlNode
import melon.html.MultiHtmlNode
import melon.html.ValueHtmlNode
import melon.markdown.*

class Markdown2HtmlTransformer : Transformer<MarkdownNode, HtmlNode> {
    override fun transform(from: MarkdownNode): HtmlNode {
        val children = mutableListOf<HtmlNode>()

        if (from.children.size > 0) {
            var skip = 0
            for (mdNode in from.children) {
                if (skip > 0) skip--

                if (skip <= 0) {
                    if (mdNode is MarkdownBranchNode) {
                        val pNode = textLineTo(mdNode)

                        if (pNode.children.isNotEmpty()) children.add(pNode)
                    } else if (mdNode is MarkdownListNode) {
                        val index = from.children.indexOf(mdNode) + 1
                        val maxIndex = from.children.size - 1

                        val lists = mutableListOf<MarkdownNode>(mdNode)
                        for (i in index..maxIndex) {
                            if (from.children[i] !is MarkdownListNode) break

                            skip++
                            lists.add(from.children[i])
                        }

                        skip++

                        children.add(MultiHtmlNode("p", listOf(listLineTo(lists)!!)))
                    }
                }
            }
        } else {
            children.add(MultiHtmlNode("p"))
        }

        return MultiHtmlNode(
            "html",
            listOf(
                MultiHtmlNode(
                    "body",
                    children
                )
            )
        )
    }

    private fun textLineTo(textLineNode: MarkdownNode): MultiHtmlNode {
        val node = MultiHtmlNode("p")


        for (i in textLineNode.children) {
            if (i is MarkdownTagNode) {
                val tag = when (i.value) {
                    "**" -> "b"
                    "~~" -> "s"
                    else -> "i"
                }

                val res = textLineTo(i).children.toList().toMutableList()
                for (r in res) r.detach()

                val node2 = MultiHtmlNode(tag, res)

                node.attach(node2)
            } else if (i is MarkdownTextNode) {
                node.attach(ValueHtmlNode(i.value))
            }
        }

        return node
    }

    private fun listLineTo(lists: List<MarkdownNode>): MultiHtmlNode? {
        if (lists.isEmpty()) return null

        val node = MultiHtmlNode(if ((lists.first() as MarkdownListNode).value == "*") "ul" else "ol")

        for (list in lists) {
            val lnText = ValueHtmlNode(textLineTo(list.children[0]).children.joinToString(""))

            val li = MultiHtmlNode("li")
            li.attach(lnText)

            if (list.children[1].children.isNotEmpty()) {
                li.attach(listLineTo(list.children[1].children)!!)
            }

            node.attach(li)
        }

        return node
    }

}