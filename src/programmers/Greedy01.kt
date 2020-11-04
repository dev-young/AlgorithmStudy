package programmers

//https://programmers.co.kr/learn/courses/30/lessons/42862?language=kotlin
class Greedy01 {

    fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
        var answer = n
        val reserveSet = hashSetOf<Int>().apply { reserve.forEach { add(it) } }
        val lostSet = hashSetOf<Int>().apply {
            lost.forEach {
                if (reserveSet.contains(it))
                    reserveSet.remove(it)
                else add(it)
            }
        }

        for (i in lostSet) {
            when {
                reserveSet.contains(i - 1) -> reserveSet.remove(i - 1)
                reserveSet.contains(i + 1) -> reserveSet.remove(i + 1)
                else -> answer--
            }
        }

        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Greedy01()
            val r = s.solution(5, intArrayOf(2, 4), intArrayOf(1, 3, 5))
            println(r)
//            println(r2.contentToString())
        }
    }
}
// 걸린 시간: 16분