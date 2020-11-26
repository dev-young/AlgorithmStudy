package kakao.blind2019

//https://programmers.co.kr/learn/courses/30/lessons/42893?language=kotlin
class KakaoB19_5 {

    data class WebPage(val id: Int, val html: String, val searchWord: String) {
        val url: String
        val words = arrayListOf<String>()
        val links = arrayListOf<String>()
        val baseScore: Double
        var matchingScore: Double

        companion object {
            val baseUrlRegex = Regex("<meta property=.* content=\".*\"")
            val linkRegex = Regex("<a href=\".*?\"")
            val wordSplit = Regex("[a-z]+", RegexOption.IGNORE_CASE)
        }

        init {
            val searchWordLower = searchWord.toLowerCase()

            var url = ""
            baseUrlRegex.find(html)?.value?.let {
                url = it.substring(33, it.length - 1)
            }
            this.url = url
            words.addAll(wordSplit.findAll(html).map { it.value.toLowerCase() })
            links.addAll(linkRegex.findAll(html).map { it.value.substring(9, it.value.length - 1) })

            var base = 0.0
            words.forEach { if (it == searchWordLower) base++ }
            baseScore = base
            matchingScore = base
        }
    }

    fun solution(word: String, pages: Array<String>): Int {
        val pageMap = pages.mapIndexed { index, s ->
            WebPage(index, s, word)
        }.let {
            hashMapOf<String, WebPage>().apply {
                it.forEach { set(it.url, it) }
            }
        }

        pageMap.values.forEach {
            val s = if (it.links.isEmpty()) 0.0 else it.baseScore / it.links.size.toDouble()
            it.links.forEach {
                pageMap[it]?.apply {
                    matchingScore += s
                }
            }
        }

        pageMap.values.forEach {
            println("${it.id} -> ${it.matchingScore}  ${it.links}")
        }

        return pageMap.values.sortedWith(Comparator { o1, o2 ->
            o1.matchingScore.compareTo(o2.matchingScore).let {
                if (it == 0)
                    o1.id.compareTo(o2.id)
                else -it
            }
        })[0].id
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = KakaoB19_5()
            val r = s.solution("muzi",
                arrayOf("<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>",
                    "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"))
            println(r)
        }
    }
}
// 걸린 시간: 분