package buzzvil

class Solution1_2 {


    fun getMaxBarrier(initialEnergy: Array<Int>, th: Long): Int {
        val i = findBoundary(Int.MAX_VALUE.toLong()) { barrier ->
            var sum = 0L
            for (energy in initialEnergy) {
                val r = energy - barrier
                if(r > 0) sum += r
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
            val s = Solution1_2()
            val r = s.getMaxBarrier(arrayOf(5, 2, 13, 10), 8)
            println(r)
        }
    }
}
// 걸린 시간(분):