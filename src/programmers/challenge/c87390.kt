package programmers.challenge

import kotlin.concurrent.fixedRateTimer
import kotlin.math.max


//https://programmers.co.kr/learn/courses/30/lessons/87390
//level2
class c87390 {
    fun solution(n: Int, left: Long, right: Long): IntArray {
        var answer = IntArray((right - left + 1).toInt())

        for (i in left..right) {
            answer[(i - left).toInt()] = max(i%n, i/n).toInt() + 1
        }

        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = c87390()
            println(s.solution(3,2,5).contentToString())
            println(s.solution(4,7,14).contentToString())
        }
    }
}
// 걸린 시간(분): 10분
