package algorithm

/**모든 정점에서 모든 정점으로의 최소비용(최단거리)을 구하는 알고리즘  O(n^3) */
class Floyd {

    /**인접행렬을 사용한 플로이드 알고리즘
     * @param arr 인접행렬 */
    fun floyd(arr: Array<IntArray>): Array<IntArray> {
        val inf = Int.MAX_VALUE
        val result = arr.clone()
        for (i in arr.indices) {
            for (j in arr.indices)
                for (k in arr.indices) {
                    if (arr[j][i] == inf || arr[i][k] == inf) continue
                    if (result[j][k] > result[j][i] + result[i][k]) {
                        result[j][k] = result[j][i] + result[i][k]
                    }
                }
        }
        return result
    }

    /**경로 출력이 필요한 경우 사용*/
    fun floydWithPath(arr: Array<IntArray>): Array<IntArray> {
        val inf = Int.MAX_VALUE
        val result = arr.clone()
        //next[i][j] : 정점 i에서 j를 가기 위해 거처야 하는 정점
        val next = Array(arr.size) { i -> IntArray(arr.size) { j -> if (arr[i][j] == inf) -1 else j } }

        for (k in arr.indices) {
            for (i in arr.indices)
                for (j in arr.indices) {
                    if (arr[i][k] == inf || arr[k][j] == inf) continue
                    val d = result[i][k] + result[k][j]
                    if (result[i][j] > d) {
                        result[i][j] = d
                        next[i][j] = next[i][k]
                    }
                }
        }
        return next
    }

    //경로 추적
    fun trace(start: Int, end: Int, next: Array<IntArray>): List<Int> {
        val result = arrayListOf(start)
        fun t(u: Int, v: Int) {
            if (u != v) {
                result.add(next[u][v])
                t(next[u][v], v)
            }
        }
        t(start, end)
        return result
    }

    //테스트
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Floyd().apply {
                val inf = Int.MAX_VALUE
                val arr = arrayOf(
                        intArrayOf(0, 5, inf, 8),
                        intArrayOf(7, 0, 9, inf),
                        intArrayOf(2, inf, 0, 4),
                        intArrayOf(inf, inf, 3, 0)
                )
                val next = floydWithPath(arr)
                val result = trace(1, 3, next)
                result.forEach {
                    println(it)
                }
            }

        }
    }
}