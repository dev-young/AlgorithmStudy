package algorithm.blimp

class Solution2 {
    fun solution(C: Double, F: Double, X: Double): Double {

        // TODO: 이 체크 로직을 정확히 하면 해결될 것 같은데 머리가 안굴러간다 ㅠㅠ  
        //알바생 수가 주어졌을때 걸리는 시간 계산
        fun check(count: Int): Double {
            var time = 0.toDouble()
            var x = 0.toDouble()   //현재 닭
            var c = 0   //고용중인 인원 수
            while (c < count && x < X) {
                time += (C / 2)
                x += (c * F)
                c++
            }
            if (x < X) {
                val oneMinCnt = 2 + (c * F) //1분당 생성되는 닭
                time += ((X - x) / oneMinCnt)
            }
            return time
        }

        val employees = findBoundary(Int.MAX_VALUE) {
            val b = check(it) <= check(it + 1)
            b
        }

        return check(employees+1)
    }

    private fun findBoundary(n: Int, check: (n: Int) -> Boolean): Int {
        var s = 0
        var e = n - 1
        if (check(0)) return -1
        if (!check(e)) return -1
        check(3)
        while (check(s) == check(s + 1)) {
            val mid = (s + e) / 2
            if (check(mid)) {
                e = mid
            } else
                s = mid
        }
        return s
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Solution2()
//            val r = s.solution(30.toDouble(), 1.toDouble(), 2.toDouble())   // 1.0
            val r = s.solution(30.toDouble(), 2.toDouble(), 100.toDouble())   // 39.166667
            println(r)
        }
    }
}
// 걸린 시간(분):