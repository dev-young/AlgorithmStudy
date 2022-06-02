package algorithm.korbit

import kotlin.math.pow

//https://www.acmicpc.net/problem/1740 참고
class Solution3 {
    fun solution(n: Long): Long {
        var answer = 0L
        var x = n
        var i = 0
        while (x > 1) {
            if (x%2 == 1L) {
                answer += 3.toDouble().pow(i).toLong()
            }
            x /= 2
            i++
        }
        if (x == 1L) {
            answer += 3.toDouble().pow(i).toLong()
        }

        return answer
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Solution3()
            val r = s.solution(11)
            println(r)
        }
    }
}
// 걸린 시간(분):