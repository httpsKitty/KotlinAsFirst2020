package melon

import junit.framework.Assert.assertEquals
import melon.html.HtmlNode
import melon.html.MultiHtmlNode
import org.junit.jupiter.api.Test

class HtmlNodeTests {
    @Test
    fun rootDeepGenerateSuccess() {
        val progenitor = MultiHtmlNode(
            "div",
            children = listOf(
                MultiHtmlNode("div"),
                MultiHtmlNode(
                    "div",
                    children = listOf(MultiHtmlNode("div")),
                ),
            ),
        )

        val result = progenitor.toString()

        println(result);

        assertEquals(
            """<div>
  <div>
  </div>
  <div>
    <div>
    </div>
  </div>
</div>""",
            progenitor.toString(),
        )
    }
}