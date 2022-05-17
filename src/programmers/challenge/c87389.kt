package programmers.challenge


//https://programmers.co.kr/learn/courses/30/lessons/87389
//level1
class c87389 {
    fun solution(n: Int): Int {
        for (i in 2 until n) {
            if (n % i == 1) return i
        }
        return 0
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = c87389()
            println(s.solution(10))
            println(s.solution(12))
        }
    }
}
// 걸린 시간(분): 3분
