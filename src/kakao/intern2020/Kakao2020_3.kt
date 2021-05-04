package kakao.intern2020

//https://programmers.co.kr/learn/courses/30/lessons/67258?language=kotlin
class Kakao2020_3 {

    fun solution(gems: Array<String>): IntArray {
        val answer = intArrayOf(0, gems.size)
        val gemCount = hashMapOf<String, Int>()
        val gemC = gems.distinct().size
        if (gemC == 1) return intArrayOf(1, 1)
        if (gemC == gems.size) return intArrayOf(1, gemC)
        var s = 0
        var e = 0
        gemCount[gems[e]] = (gemCount[gems[e]] ?: 0) + 1
        while (true) {
            if (s > e) break
            if (gemCount.size == gemC) {
                if (e - s < answer[1] - answer[0]) {
                    answer[0] = s
                    answer[1] = e
                }
                if (gemCount[gems[s]] == 1) {
                    gemCount.remove(gems[s])
                } else {
                    gemCount[gems[s]] = gemCount[gems[s]]!! - 1
                }
                s++
            } else {
                e++
                if (e == gems.size) break
                gemCount[gems[e]] = (gemCount[gems[e]] ?: 0) + 1
            }

        }

        answer[0]++
        answer[1]++
        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Kakao2020_3()
            println(s.solution(arrayOf("DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA")).contentToString())
            println(s.solution(arrayOf("AA", "AB", "AC", "AA", "AC")).contentToString())
            println(s.solution(arrayOf("XYZ", "XYZ", "XYZ")).contentToString())
            println(s.solution(arrayOf("ZZZ", "YYY", "NNNN", "YYY", "BBB")).contentToString())
        }
    }
}
// 걸린 시간: 39