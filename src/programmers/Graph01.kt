package programmers

import java.util.*

//https://programmers.co.kr/learn/courses/30/lessons/49189?language=kotlin
class Graph01 {

    class ListGraph<T> {
        val graph = hashMapOf<T, HashSet<T>>()
        fun addEdge(from: T, to: T) {
            graph.computeIfAbsent(from) { hashSetOf() }.add(to)
            graph.computeIfAbsent(to) { hashSetOf() }.add(from)
        }
    }

    fun solution(n: Int, edge: Array<IntArray>): Int {
        var answer = 0
        var currentLevel = 0

        val listGraph = ListGraph<Int>()
        edge.forEach {
            listGraph.addEdge(it[0], it[1])
        }

        val graph = listGraph.graph
        val visited = hashSetOf<Int>()
        val que: Queue<Int> = LinkedList()
        que.offer(1)
        var level = 0
        while (!que.isEmpty()) {
            val qSize = que.size
            for (n in 1..qSize) {
                val i = que.poll()
                if (visited.contains(i)) continue
                visited.add(i)
                if(currentLevel < level) {
                    currentLevel = level
                    answer = 0
                }
                answer++
                for (j in graph[i]!!) {
                    if (graph[i]!!.contains(j) &&   /*간선이 존재하는경우*/
                        i != j &&           /*자기 자신이 아닌 경우*/
                        !visited.contains(j)         /*방문한적이 없는 경우*/
                    ) {
                        que.offer(j)
                    }
                }
            }
            level++

        }

        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Graph01()
            val r = s.solution(6, arrayOf(
                intArrayOf(3, 6),
                intArrayOf(4, 3),
                intArrayOf(3, 2),
                intArrayOf(1, 3),
                intArrayOf(1, 2),
                intArrayOf(2, 4),
                intArrayOf(5, 2)))
            println(r)
        }
    }
}
// 걸린 시간: 80분