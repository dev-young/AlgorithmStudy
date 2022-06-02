package algorithm.coinone

import kotlin.math.abs

class Solution1 {

    fun solution(data: IntArray, d: Int, k: Int): IntArray {
        val delSet = hashSetOf<Int>()
        var cnt = 0
        for (i in data.indices) {
            if (i in 1 until data.lastIndex) {
                if (cnt <= k) {
                    if (abs(data[i] - data[i + 1]) <= d) {
                        cnt++
                        delSet.add(i)
                        continue
                    }
                }
            }
            cnt = 0
        }

        return data.filterIndexed { index, i -> !delSet.contains(index) }.toIntArray()
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Solution1()
            val r = s.solution(intArrayOf(13, 15, 14, 17, 19, 18, 22, -1, -4), 3, 2)
            r.forEach {
                println(it)
            }
        }
    }
}
// 걸린 시간(분): 14