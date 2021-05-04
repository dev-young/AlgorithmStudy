package kakao.blind2019

//https://programmers.co.kr/learn/courses/30/lessons/42893
class KakaoB19_6 {

    data class Page(val idx: Int, val baseScore: Double, val links: List<String>) {
        var totalScore = baseScore
        fun getLinkScore() = if (links.isEmpty()) 0.0 else (baseScore / links.size)
    }

    fun solution(word: String, pages: Array<String>): Int {
        var answer = 0
        val pageMap = hashMapOf<String, Page>()
        val regexUrl = """<meta property="og:url" content="[\S]*"""".toRegex()
        val regexLink = """<a href="\S*">""".toRegex()
        val wordRegex = """[a-zA-Z]+""".toRegex()
        val lowWord = word.toLowerCase()
        pages.forEachIndexed { i, script ->
            val url = regexUrl.find(script)!!.value.let { it.substring(33, it.length - 1) }
            val links = regexLink.findAll(script).map { it.value.let { it.substring(9, it.length - 2) } }.toList()
            val score = wordRegex.findAll(script).count { it.value.toLowerCase() == lowWord}.toDouble()
            pageMap[url] = Page(i, score, links)
        }

        for (page in pageMap.values) {
            val ls = page.getLinkScore()
            page.links.forEach {
                pageMap[it]?.let {
                    it.totalScore +=  ls
                }
            }
        }

        var maxScore = 0.0
        for (page in pageMap.values) {

            if (maxScore < page.totalScore) {
                answer = page.idx
                maxScore = page.totalScore
            } else if (maxScore == page.totalScore) {
                if (page.idx < answer)
                    answer = page.idx
            }
        }

        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = KakaoB19_6()
//            val r = s.solution("Muzi",
//                    arrayOf("<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>",
//                            "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"))
            val r = s.solution("blind",
                    arrayOf("<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
                            "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
                            "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"))
            println(r)

        }
    }
}
// 걸린 시간: 101분