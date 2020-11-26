package kakao.blind2019

//https://programmers.co.kr/learn/courses/30/lessons/42893?language=kotlin
class KakaoB19_5_other {

    data class PageInfo(
        val url : String,
        var basicScore : Int = 0,
        val externalLinks : ArrayList<String> = ArrayList(),
        var matchingScore : Double = 0.0
    )
    private val basicUrlRegex = Regex("<meta property=\"og:url\" content=\".*?\"")
    private lateinit var wordMatchRegex : Regex
    private val externalLinkRegex = Regex("<a href=\".*?\"")
    fun solution(word: String, pages: Array<String>): Int {
        val pageInfoList = ArrayList<PageInfo>()
        wordMatchRegex = Regex("(?i)(?<!\\p{L})$word(?!\\p{L})")
        pages.forEach { eachPageString ->
            pageInfoList.add(PageInfo(
                basicUrlRegex.find(eachPageString, 0)!!.value.substring(33).removeSuffix("\""),
                wordMatchRegex.findAll(eachPageString).count(),
                ArrayList(externalLinkRegex.findAll(eachPageString).map { it.value.substring(9).removeSuffix("\"") }.toList())
            ))
        }

        pageInfoList.forEach {
                outerLoopPageInfo ->
            outerLoopPageInfo.matchingScore = outerLoopPageInfo.basicScore + pageInfoList.filter {
                    innerLoopPageInfo ->
                innerLoopPageInfo.externalLinks.contains(outerLoopPageInfo.url)
            }.sumByDouble {
                    filteredInnerLoopPageInfo ->
                filteredInnerLoopPageInfo.basicScore.toDouble() / filteredInnerLoopPageInfo.externalLinks.size
            }
        }
        return pageInfoList.mapIndexed { index, pageInfo -> Pair(index, pageInfo) }.sortedByDescending { it.second.matchingScore }[0].first
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val str = "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>"
            val word = "html"
            val regex = Regex("(?i)(?<!\\p{L})$word(?!\\p{L})")
            val result = regex.findAll(str)
            result.forEach { println(it.value) }
//            val s = KakaoB19_5_other()
//            val r = s.solution("blind",
//                arrayOf("<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>",
//                    "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"))
        }
    }
}
// 걸린 시간: 분