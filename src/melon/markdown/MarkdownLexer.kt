package melon.markdown

import melon.abstractions.Lexer

class MarkdownLexer : Lexer<MarkdownToken> {
    override fun lex(text: String): List<MarkdownToken> {
        var outputTokens = mutableListOf<MarkdownToken>()

        // split to lines
        val lines = text
            .replace("***", "** *")
            .replace("*~", "* ~")
            .replace("~*", "~ *")
            .split("\n")

        for (line in lines) {
            var formattedLine = line

            if (line.isNotEmpty()) {
                // lex Markdown list
                val listInfo = tryGetListInfo(formattedLine)
                if (listInfo != null) {
                    outputTokens.add(MarkdownListToken(listInfo.second, listInfo.first / 4))
                    formattedLine = formattedLine.trim().drop(listInfo.second.length + 1).trimStart()
                }

                // lex markdown tags
                var buffer = ""
                formattedLine = formattedLine.trim()
                for ((index, char) in formattedLine.withIndex()) {
                    val last = formattedLine.getOrNull(index - 1)
                    val current = formattedLine[index]
                    val next = formattedLine.getOrNull(index + 1)

                    if (isTag(current) && !isTag(next)) {
                        outputTokens.add(MarkdownTextToken(buffer))
                        buffer = ""

                        var tag = current.toString()
                        if (last != null && isTag(last) && last == current) tag = tag.repeat(2)

                        outputTokens.add(MarkdownTagToken(tag))
                    } else {
                        if (!isTag(char)) buffer += char
                    }
                }
                if (buffer != "") {
                    outputTokens.add(MarkdownTextToken(buffer))
                    buffer = ""
                }
            }

            // lex markdown newlines
            outputTokens.add(MarkdownNewlineToken())
        }

        outputTokens = outputTokens.filterIndexed { index, markdownToken ->
            filterNewlineTokens(
                markdownToken,
                if (index < outputTokens.size - 1) outputTokens[index + 1] else null
            )
        }.toMutableList()

        return outputTokens.filterIndexed { index, markdownToken ->
            filterNewlineTokensDublicates(
                markdownToken,
                if (index < outputTokens.size - 1) outputTokens[index + 1] else null
            )
        }
    }

    private fun filterNewlineTokensDublicates(
        token: MarkdownToken,
        nextToken: MarkdownToken?
    ): Boolean = !(token is MarkdownNewlineToken && nextToken is MarkdownNewlineToken)

    private var lastIsDoubleLine = false
    private fun filterNewlineTokens(
        token: MarkdownToken,
        nextToken: MarkdownToken?
    ): Boolean {
        if (lastIsDoubleLine) {
            lastIsDoubleLine = false
            return true
        }

        if (token is MarkdownNewlineToken) {
            if (nextToken is MarkdownNewlineToken) {
                lastIsDoubleLine = true
                return false
            }

            if (nextToken !is MarkdownListToken) return false
        }

        return true
    }

    private fun isTag(char: Char?): Boolean = char == '*' || char == '~'

    private fun isList(text: String?): Boolean {
        if (text == "*" || text == "." || text == "-") return true
        if ((text?.firstOrNull()
                ?.isDigit() == true || text?.firstOrNull() == '.') && text.lastOrNull() == '.'
        ) return true
        return false
    }

    private fun tryGetTagStartWith(string: String): String? {
        val c1 = string.getOrNull(0)
        val c2 = string.getOrNull(1)

        if (isTag(c1) && !isTag(c2)) return "$c1"
        else if (c1 == c2 && isTag(c1) && isTag(c2)) return "$c1$c2"

        return null
    }

    private fun tryGetListInfo(line: String): Pair<Int, String>? {
        val trimLine = line.trimStart()

        val lineStartSpaces = line.length - trimLine.length

        val lineSpaceTokens = trimLine.split(' ')

        val firstToken = lineSpaceTokens.firstOrNull() ?: return null

        if (isList(firstToken.trim())) {
            return Pair(lineStartSpaces, firstToken)
        }

        return null
    }
}