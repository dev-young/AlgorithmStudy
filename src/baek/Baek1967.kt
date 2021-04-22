package baek

import java.io.BufferedReader
import java.io.InputStreamReader

//트리의 지름 (트리)
class Baek1967 {
    /**개인적으로 트리의 지름을 가장 이해하기 쉽게 설명한 글 : https://yabmoons.tistory.com/597*/
    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        val edges = Array(n + 1) { hashSetOf<Pair<Int, Int>>() }
        repeat(n - 1) {
            readLine().split(" ").let {
                val v = it[0].toInt()
                val p = it[1].toInt()
                val s = it[2].toInt()
                edges[v].add(Pair(p, s))
                edges[p].add(Pair(v, s))
            }
        }

        fun getMaxLength(start: Int): Pair<Int, Int> {
            var maxSum = 0
            var maxNode = start
            val visited = BooleanArray(edges.size + 1) { false }

            fun dfs(node: Int, sum: Int = 0) {
                visited[node] = true
                if (maxSum < sum) {
                    maxSum = sum
                    maxNode = node
                }
                for (pair in edges[node]) {
                    if (visited[pair.first]) continue
                    dfs(pair.first, sum + pair.second)
                }
            }
            dfs(start)
            return Pair(maxNode, maxSum)
        }

        val v = getMaxLength(1).first
        val v1 = getMaxLength(v)
        println(v1.second)

        close()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek1967().main()
        }
    }
}
// 걸린 시간(분): 14