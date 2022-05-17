package buzzvil

import kotlin.math.max

class Solution6 {

    fun bestSumAnyTreePath(parent: Array<Int>, values: Array<Int>): Int {
        val nodes = Array(parent.size) { arrayListOf<Int>()}
        parent.forEachIndexed { node, parent ->
            nodes[node].add(parent)
            if (parent != -1) nodes[parent].add(node)
        }
        var ans = values[0]
        val visited = BooleanArray(nodes.size)
        fun dfs(node: Int, _sum:Int) {
            val sum = _sum + values[node]
            ans = max(sum, ans)

            nodes[node].forEach { n ->
                if(!visited[n]){
                    visited[n] = true
                    dfs(n, sum)
                    visited[n] = false
                }
            }
        }

        parent.indices.forEach {
            visited[it] = true
            dfs(it, 0)
            visited[it] = false
        }

        return ans
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Solution6()
//            val r = s.bestSumAnyTreePath("")
//            println(r)
        }
    }
}
// 걸린 시간(분):