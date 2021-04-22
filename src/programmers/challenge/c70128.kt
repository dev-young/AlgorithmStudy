package programmers.challenge


//https://programmers.co.kr/learn/courses/30/lessons/70128
//level1
class c70128 {
    fun solution(a: IntArray, b: IntArray): Int {
        var answer: Int = 0
        a.forEachIndexed { idx, i ->
            answer += b[idx] * i
        }
        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = c70128()
//            println(s.solution(arrayOf(intArrayOf(1, 1, 1, 1, 1, 1, 1, 1), intArrayOf(0, 1, 1, 1, 1, 1, 1, 1), intArrayOf(0, 0, 0, 0, 1, 1, 1, 1), intArrayOf(0, 1, 0, 0, 1, 1, 1, 1), intArrayOf(0, 0, 0, 0, 0, 0, 1, 1), intArrayOf(0, 0, 0, 0, 0, 0, 0, 1), intArrayOf(0, 0, 0, 0, 1, 0, 0, 1), intArrayOf(0, 0, 0, 0, 1, 1, 1, 1))).contentToString())
//            println(s.solution(arrayOf(intArrayOf(1,1,0,0),intArrayOf(1,0,0,0),intArrayOf(1,0,0,1),intArrayOf(1,1,1,1))).contentToString())

        }
    }
}
// 걸린 시간: 3분
