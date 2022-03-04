package melon.utils

import melon.abstractions.Node
import melon.html.HtmlNode

class NodeTreeUtil {
    companion object {
        fun <T : Node<T>> findNodesToProgenitor(node: T): Int {
            if (node.isRoot) return 0

            var lastFather = node.father!!
            var count = 1

            do {
                if (lastFather.father == null) return count

                lastFather = lastFather.father!!
                count++
            } while (true)
        }
    }
}