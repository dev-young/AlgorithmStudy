package programmers.challenge


//https://programmers.co.kr/learn/courses/30/lessons/68935
//level1
class c68935 {
    fun solution(n: Int): Int {

        return n.toString(3).reversed().toInt(3)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = c68935()
//            println(s.solution(arrayOf(intArrayOf(1, 1, 1, 1, 1, 1, 1, 1), intArrayOf(0, 1, 1, 1, 1, 1, 1, 1), intArrayOf(0, 0, 0, 0, 1, 1, 1, 1), intArrayOf(0, 1, 0, 0, 1, 1, 1, 1), intArrayOf(0, 0, 0, 0, 0, 0, 1, 1), intArrayOf(0, 0, 0, 0, 0, 0, 0, 1), intArrayOf(0, 0, 0, 0, 1, 0, 0, 1), intArrayOf(0, 0, 0, 0, 1, 1, 1, 1))).contentToString())
//            println(s.solution(arrayOf(intArrayOf(1,1,0,0),intArrayOf(1,0,0,0),intArrayOf(1,0,0,1),intArrayOf(1,1,1,1))).contentToString())

        }
    }
}
// 걸린 시간: 9분
