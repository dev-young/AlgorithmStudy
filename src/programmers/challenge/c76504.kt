package programmers.challenge


//https://programmers.co.kr/learn/courses/30/lessons/76504
//level4
class c76504 {

    lateinit var m: IntArray
    private val gainMap: MutableMap<Int, MutableMap<Int, Int>> = HashMap()

    fun solution(n: Int, z: Int, roads: Array<IntArray>, queries: LongArray): LongArray {
        m = IntArray(z * z)
        buildGainMap(n, z, roads)
        buildMoves(n, z)
        val answer = LongArray(queries.size)
        var i = 0
        for (query in queries) {
            var index = (query % z).toInt()
            var stopTime = query / z
            var min: Long? = null
            while (stopTime >= 0 && index < z * z - z) {
                if (m[index] != -1) {
                    if (min == null || min > stopTime + m[index]) {
                        min = stopTime + m[index]
                    }
                }
                index += z
                stopTime--
            }
            if (min == null) {
                answer[i] = -1
            } else {
                answer[i] = min
            }
            i++
        }
        return answer
    }

    private fun buildGainMap(n: Int, z: Int, roads: Array<IntArray>) {
        for (i in 0 until n) {
            gainMap.computeIfAbsent(i) { x: Int? -> HashMap() }[i] = z
        }
        for (road in roads) {
            gainMap[road[1]]!![road[0]] = road[2]
        }
    }

    private fun buildMoves(n: Int, z: Int) {
        val moves = Array(n) { arrayOfNulls<Int>(z * z) }
        moves[0][0] = 0
        for (gain in 0 until z * z) {
            var min: Int? = null
            for (node in 0 until n) {
                gainMap[node]?.let { m ->
                    for ((src, roadgain) in m) {
                        val prevgain = gain - roadgain
                        if (prevgain >= 0 && moves[src][prevgain] != null) {
                            if (moves[node][gain] == null || moves[node][gain]!! > moves[src][prevgain]!! + 1) {
                                moves[node][gain] = moves[src][prevgain]!! + 1
                            }
                        }
                    }
                }
                if (moves[node][gain] != null) {
                    if (min == null || min > moves[node][gain]!!) {
                        min = moves[node][gain]
                    }
                }
            }
            if (min != null) {
                for (node in 0 until n) {
                    if (moves[node][gain] == null || moves[node][gain]!! > min + 1) {
                        moves[node][gain] = min + 1
                    }
                }
            }
            m[gain] = min ?: -1
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = c76504()
            println(s.solution(5,5, arrayOf(intArrayOf(1,2,3), intArrayOf(0,3,2)), longArrayOf(0,1,2,3,4,5,6)))
        }
    }
}
// 걸린 시간(분): 포기.. (도저히 모르겠다.. )
