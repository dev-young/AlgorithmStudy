package algorithm.korbit

class Solution2 {
    fun solution(card: Array<String>, word: Array<String>): Array<String> {
        val answer = arrayListOf<String>()
        val charCnt = hashMapOf<Char, Int>()
        val charLine = hashMapOf<Char, Int>()   //해당 글자의 라인 번호
        card.forEachIndexed { line, s ->
            s.forEach {
                charCnt[it] = 1 + (charCnt[it] ?: 0)
                charLine[it] = line
            }
        }

        out@ for (s in word) {
            val lineCnt = intArrayOf(0, 0, 0)
            val wordCharCnt = hashMapOf<Char, Int>()
            for (c in s) {
                if (charLine.containsKey(c)) {
                    lineCnt[charLine[c]!!]++
                    wordCharCnt[c] = 1 + (wordCharCnt[c] ?: 0)
                } else {
                    continue@out
                }
            }
            if (!lineCnt.contains(0)) {
                for ((c, cnt) in wordCharCnt) {
                    if (charCnt[c]!! < cnt) continue@out
                }
                answer.add(s)
            }
        }
        return if (answer.isEmpty()) arrayOf("-1") else answer.toTypedArray()
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Solution2()
            val r = s.solution(arrayOf("ABACDEFG", "NOPQRSTU", "HIJKLKMM"), arrayOf("GPQM", "GPMZ", "EFU", "MMNA"))   // ["GPQM","MMNA"]
            println(r.contentToString())
        }
    }
}
// 걸린 시간(분):