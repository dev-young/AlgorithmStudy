package kakao.intern2020

import java.util.*

//https://programmers.co.kr/learn/courses/30/lessons/67260?language=kotlin
class Kakao2020_5 {
    //인접리스트 그래프
    class ListGraph {
        val graph = hashMapOf<Int, HashSet<Int>>()

        fun addEdge(from: Int, to: Int) {
            graph.computeIfAbsent(from) { hashSetOf() }.add(to)
            graph.computeIfAbsent(to) { hashSetOf() }.add(from)
        }
    }

    /**효율성 10번에서 시간초과가 난다... bfs 말고 dfs를 사용하면 해결될것으로 보인다. */
    fun solution(n: Int, path: Array<IntArray>, order: Array<IntArray>): Boolean {
        val graph = ListGraph()
        path.forEach {
            graph.addEdge(it[0], it[1])
        }
        val disableRoom = hashSetOf<Int>()
        val orderMap = order.let {
            hashMapOf<Int, Int>().apply {
                it.forEach {
                    put(it[0], it[1])
                    disableRoom.add(it[1])
                }
            }
        }

        fun bfs(graph: ListGraph, root: Int = 0): Boolean {
            val gr = graph.graph
            val visited = hashSetOf<Int>()
            val que: Queue<Int> = LinkedList()
            val failedSet = hashSetOf<Int>()    //방문을 시도했지만 실패한 노드 집합
            que.offer(root)
            while (!que.isEmpty()) {
                val i = que.poll()
                if (visited.contains(i)) continue

                if(disableRoom.contains(i)) {
                    que.offer(i)
                    failedSet.add(i)
                    if(que.size == failedSet.size){
                        return false
                    }
                    continue
                }
                orderMap[i]?.let {
                    disableRoom.remove(it)
                    orderMap.remove(it)
                    failedSet.remove(it)
                }

                visited.add(i)
                if(visited.size == n) return true
                for (j in gr[i]!!) {
                    que.offer(j)
                }

            }
            return true
        }

        return bfs(graph)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Kakao2020_5()
            println(s.solution(9,
                arrayOf(intArrayOf(0, 1),
                    intArrayOf(0, 3),
                    intArrayOf(0, 7),
                    intArrayOf(8, 1),
                    intArrayOf(3, 6),
                    intArrayOf(1, 2),
                    intArrayOf(4, 7),
                    intArrayOf(7, 5)),
                arrayOf(intArrayOf(8, 5), intArrayOf(6, 7), intArrayOf(4, 1))))

            println(s.solution(9,
                arrayOf(intArrayOf(0, 1),
                    intArrayOf(0, 3),
                    intArrayOf(0, 7),
                    intArrayOf(8, 1),
                    intArrayOf(3, 6),
                    intArrayOf(1, 2),
                    intArrayOf(4, 7),
                    intArrayOf(7, 5)),
                arrayOf(intArrayOf(4, 1), intArrayOf(8, 7), intArrayOf(6, 5))))
        }
    }
}
// 걸린 시간: 154분