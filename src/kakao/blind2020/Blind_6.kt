package kakao.blind2020

//https://programmers.co.kr/learn/courses/30/lessons/60062?language=kotlin
class Blind_6 {

    fun solution(n: Int, weak: IntArray, dist: IntArray): Int {
        dist.sortDescending()
        fun check(dist: IntArray): Boolean {
            fun f(start: Int): Boolean {
                val ws = weak.toHashSet()
                var i = start
                for (d in dist) {
                    val range = weak[i]..d+weak[i]
                    for (jj in i until i + weak.size) {
                        val j = jj % weak.size
                        val w = weak[j]
                        if (w in range || w + n in range) {
                            ws.remove(w)
                        } else {
                            i = j
                            break
                        }
                    }
                }
                return ws.isEmpty()
            }
            for (start in weak.indices) {
                if (f(start)) return true
            }
            return false
        }

        for (n in 1..dist.size) {
            val d = dist.copyOfRange(0, n)
            val r = permutation(d, d.size)
            for (permuted in r) {
                if (check(permuted)) return n
            }

        }
        return -1
    }

    fun permutation(arr: IntArray, r: Int): ArrayList<IntArray> {
        val result = arrayListOf<IntArray>()
        val visited = BooleanArray(arr.size)
        fun perm(temp: IntArray = IntArray(r), current: Int = 0) {
            if (r == current) {
                result.add(temp.clone())
            } else {
                for (i in arr.indices) {
                    if (!visited[i]) {
                        visited[i] = true
                        temp[current] = arr[i]
                        perm(temp, current + 1)
                        visited[i] = false
                    }
                }
            }
        }
        perm()
        return result
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Blind_6()
//            println(s.solution(12, intArrayOf(1, 5, 6, 10), intArrayOf(1, 2, 3, 4)))
//            println(s.solution(100, intArrayOf(1, 5, 9, 12, 13, 20, 24, 28, 92), intArrayOf(1, 1, 8, 13)))
            println(s.solution(200, intArrayOf(0, 10, 50, 80, 120, 160), intArrayOf(1, 10, 5, 40, 30)))
//            println(s.solution(12, intArrayOf(0,2,4,6,8,10), intArrayOf(1,1,2,1,1)))
//            println(s.solution(12, intArrayOf(1, 3, 4, 9, 10), intArrayOf(1, 1, 1)))

        }
    }
}
// 걸린 시간(분): 133