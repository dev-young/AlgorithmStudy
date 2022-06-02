package algorithm.coinone

class Solution2 {
    fun solution(deposit: IntArray): IntArray {
        val answer = arrayListOf<Int>()

        deposit.forEach {
            if (it > 0) {
                answer.add(it)
            } else {
                var remain = it
                while (remain < 0) {
                    val last = answer.removeLast()
                    remain += last
                    if (remain > 0) {
                        answer.add(remain)
                    }
                }
            }
        }

        return answer.toIntArray()
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Solution2()
            val r = s.solution(intArrayOf(500, 1000, -300, 200, -400, 100, -100))   // 500,500
            println(r.contentToString())
        }
    }
}
// 걸린 시간(분):