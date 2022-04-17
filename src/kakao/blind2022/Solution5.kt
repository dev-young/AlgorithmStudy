package kakao.blind2022

import toArrayOfIntArray

class Solution5 {

    fun solution(info: IntArray, _edges: Array<IntArray>): Int {
        var answer = 0
        val edges = hashMapOf<Int, HashSet<Int>>()

        _edges.forEach { edges.computeIfAbsent(it[0]) { hashSetOf() }.add(it[1]) }


        val visited = BooleanArray(info.size)
        fun dfs(node: Int, ship: Int, wolf: Int, _next: Set<Int>) {
            val isWolf = info[node] == 1
            val next = _next.toHashSet()
            next.addAll(_next)
            var shipCnt = ship
            var wolfCnt = wolf
            if (isWolf) {
                wolfCnt++
            } else {
                shipCnt++
            }
            if (wolfCnt == shipCnt) {
                return
            }

            if (answer < shipCnt) answer = shipCnt
            edges[node]?.forEach {
                if (!visited[it]) next.add(it)
            }
            next.remove(node)


            visited[node] = true

            next.forEach {
                dfs(it, shipCnt, wolfCnt, next)
            }
            visited[node] = false

        }

        dfs(0, 0, 0, setOf())

        return answer
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Solution5()
            val r = s.solution(
                intArrayOf(0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1),
                "[[0,1],[1,2],[1,4],[0,8],[8,7],[9,10],[9,11],[4,3],[6,5],[4,6],[8,9]]".toArrayOfIntArray()
            )
            println(r)
        }
    }
}
// 걸린 시간(분): 42