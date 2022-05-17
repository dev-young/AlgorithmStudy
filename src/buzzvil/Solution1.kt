package buzzvil

class Solution1 {


    fun solution(initialEnergy: Array<Int>, th: Long): Int {
        val i = findBoundary(Int.MAX_VALUE.toLong()) { barrier ->
            val sum = initialEnergy.fold(0) { acc, i ->
                val x = i - barrier
                if (x < 0) acc else acc + x.toInt()
            }
            sum < th
        }
        return i
    }

    fun findBoundary(n: Long, check: (n: Long) -> Boolean): Int {
        var s = 0L
        var e = n
        if (check(0)) return 0
        if (!check(n)) return n.toInt()
        while (check(s) == check(s+1)) {
            val mid = (s + e) / 2
            if (check(mid)) {
                e = mid
            } else
                s = mid
        }
        return s.toInt()
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Solution1()
            val r = s.solution(arrayOf(5, 2, 13, 10), 8)
            println(r)
        }
    }
}
// 걸린 시간(분):