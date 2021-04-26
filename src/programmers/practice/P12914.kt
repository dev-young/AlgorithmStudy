package programmers.practice


//https://programmers.co.kr/learn/courses/30/lessons/12914?language=kotlin
//멀리 뛰기
//level3
class P12914 {
    fun solution(n: Int): Long {
        if (n < 4) return n.toLong()
        val x = 1234567.toLong()
        var answer: Long = 0
        var n1 = 3L
        var n2 = 2L
        for (i in 4..n) {
            answer = (n1 + n2) % x
            n2 = n1
            n1 = answer
        }
        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = P12914()
            println(s.solution(4))
            println(s.solution(3))

        }
    }
}
// 걸린 시간(분): 21
