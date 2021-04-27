package programmers.practice


//https://programmers.co.kr/learn/courses/30/lessons/12952
//N-Queen
//level3
class P12952 {
    fun solution(n: Int): Int {
        var answer = 0

        fun isOk(x: Int, y: Int, x2: Int, y2: Int): Boolean {
            if (x == x2 || y == y2) return false
            return Math.abs(y - y2).let {
                if (it == 0) true else {
                    Math.abs(x - x2) != it
                }
            }
        }

        val visitedSet = hashSetOf<Pair<Int, Int>>()
        fun check(i: Int) {
            if (i > n) return
            for (j in 1..n) {
                var ok = true
                for (pair in visitedSet) {
                    if (!isOk(i, j, pair.first, pair.second)) {
                        ok = false
                        break
                    }
                }
                if (ok) {
                    val p = Pair(i, j)
                    visitedSet.add(p)
                    if (visitedSet.size == n) {
                        answer++
                    }
                    check(i + 1)

                    visitedSet.remove(p)
                }
            }

        }
        check(1)
        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = P12952()
//            println(s.solution(3))
//            println(s.solution(4))
            println(s.solution(5))
//            println(s.solution(6))

        }
    }
}
// 걸린 시간(분):100
