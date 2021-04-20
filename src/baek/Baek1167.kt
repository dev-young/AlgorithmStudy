package baek

import java.io.BufferedReader
import java.io.InputStreamReader

//트리의 지름 (트리)
class Baek1167 {

    /**https://blog.myungwoo.kr/112 참고*/
    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        val edges = Array(n + 1) { hashSetOf<Pair<Int, Int>>() }
        repeat(n) {
            readLine().split(" ").let {
                val v = it[0].toInt()
                for (i in 1 until it.size / 2) {
                    val idx = i * 2
                    val e = it[idx - 1].toInt()
                    val s = it[idx].toInt()
                    edges[v].add(Pair(e, s))
                }
            }
        }

        /**start 에서 가장 먼 거리의 정점과 거리를 반환*/
        fun getMaxLength(start: Int): Pair<Int, Int> {
            var maxSum = 0
            var maxNode = start
            val visited = BooleanArray(edges.size+1) { false }

            fun dfs(node: Int, sum :Int = 0) {
                visited[node] = true
                if(maxSum < sum) {
                    maxSum = sum
                    maxNode = node
                }
                for (pair in edges[node]) {
                    if (visited[pair.first]) continue
                    dfs(pair.first, sum+pair.second)
                }
            }
            dfs(start)
            return Pair(maxNode, maxSum)
        }

        println(getMaxLength(getMaxLength(1).first).second)

        close()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek1167().main()
        }
    }
}
// 걸린 시간(분):