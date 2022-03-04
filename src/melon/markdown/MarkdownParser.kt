package melon.markdown

import melon.abstractions.Parser

class MarkdownParser : Parser<MarkdownToken, MarkdownNode> {
    override fun parse(tokens: List<MarkdownToken>): MarkdownNode? {
        val lines = tokensToLinesTokens(tokens).map { it.toList() }.toList()

        val branchNode = MarkdownBranchNode()

        var skip = 0
        for (line in lines) {
            if (skip > 0) skip--

            if (skip <= 0) {
                branchNode.attach(
                    when (line.first()) {
                        is MarkdownListToken -> {
                            val result = listTokensToNode(line, lines)

                            skip += result.first

                            result.second
                        }
                        else -> lineTokensToNode(line, lines)
                    }
                )
            }
        }

        return branchNode
    }

    private fun textTokenToNode(
        token: MarkdownToken,
        line: List<MarkdownToken>,
        lines: List<List<MarkdownToken>>
    ): MarkdownNode {
        return MarkdownTextNode(token.value)
    }

    private fun tagTokenToNode(
        token: MarkdownTagToken,
        line: List<MarkdownToken>,
        lines: List<List<MarkdownToken>>
    ): Pair<Int, MarkdownTagNode> {
        val aindex = line.indexOf(token)
        var subline = line.subList(aindex + 1, line.lastIndex + 1)
        val closeToken = subline.firstOrNull { it is MarkdownTagToken && it.value == token.value }
            ?: throw NoSuchElementException(
                "Не удается найти закрывающий тег в строке! открывающий токен: ${
                    token.value + "@" + token.hashCode().toString()
                }. строка: ${line.map { it.value + "@" + it.hashCode() }}"
            )
        subline = subline.subList(0, subline.indexOf(closeToken))

        val tagNode = MarkdownTagNode(token.value)

        for (node in lineToNodes(subline, lines)) {
            tagNode.attach(node);
        }

        return Pair(subline.size + 2, tagNode)
    }

    private fun lineTokensToNode(
        line: List<MarkdownToken>,
        lines: List<List<MarkdownToken>>
    ): MarkdownNode {
        return MarkdownBranchNode(lineToNodes(line, lines))
    }

    private fun listTokensToNode(
        line: List<MarkdownToken>,
        lines: List<List<MarkdownToken>>
    ): Pair<Int, MarkdownListNode> {
        var skip = 1
        val children = getChildren(line, lines).map {
            val node = listTokensToNode(it, lines)
            skip += node.first

            node.second
        }

        return Pair(skip, MarkdownListNode(line.first().value, lineToNodes(line.drop(1), lines), children))
    }

    private fun getChildren(line: List<MarkdownToken>, lines: List<List<MarkdownToken>>): List<List<MarkdownToken>> {
        val list = mutableListOf<List<MarkdownToken>>()

        val startIndex = lines.indexOf(line) + 1
        val maxIndex = lines.size - 1

        val deep = (line.first() as MarkdownListToken).deep

        for (i in startIndex..maxIndex) {
            val ln = lines[i]

            if (ln.first() !is MarkdownListToken) break

            val lnDeep = (ln.first() as MarkdownListToken).deep

            if (lnDeep <= deep) break
            if (deep + 1 == lnDeep) list.add(ln)
        }

        return list
    }

    private fun lineToNodes(
        line: List<MarkdownToken>,
        lines: List<List<MarkdownToken>>
    ): List<MarkdownNode> {
        val list = mutableListOf<MarkdownNode>()

        var skip = 0
        for (token in line) {
            if (skip > 0) skip--

            if (skip <= 0) {
                if (token is MarkdownTagToken) {
                    val result = tagTokenToNode(token, line, lines)
                    skip += result.first
                    list.add(result.second)
                } else {
                    list.add(textTokenToNode(token, line, lines))
                }
            }
        }

        return list
    }

    private fun tokensToLinesTokens(tokens: List<MarkdownToken>): List<List<MarkdownToken>> {
        val lines = mutableListOf<List<MarkdownToken>>()

        var line = mutableListOf<MarkdownToken>();

        for (token in tokens) {
            if (token is MarkdownNewlineToken) {
                lines.add(line)
                line = mutableListOf();
            } else {
                line.add(token)
            }
        }

        if (line.isNotEmpty()) lines.add(line)

        return lines
    }

}