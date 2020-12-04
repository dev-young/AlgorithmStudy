package algorithm

import java.util.*
import kotlin.collections.HashMap

/***/
class Dijkstra {

    //인접리스트 가중치 그래프
    class ListGraph<T, U> {
        val graph = hashMapOf<T, HashSet<Pair<T,U>>>()

        fun setVertex(v: T) {
            if(!graph.containsKey(v)){
                graph[v] = hashSetOf()
            }
        }

        fun addEdge(from: T, to: T, v: U) {
            graph.computeIfAbsent(from) { hashSetOf() }.add(Pair(to, v))
            graph.computeIfAbsent(to) { hashSetOf() }.add(Pair(from, v))
        }

        //단방향 간선
        fun addSingleEdge(from: T, to: T, v:U) {
            graph.computeIfAbsent(from) { hashSetOf() }.add(Pair(to, v))
        }

        fun printGraph(){
            graph.forEach { t, u ->
                println("$t -> $u")
            }
        }
    }

    /**가중치 인접리스트를 이용한 다익스트라 알고리즘
     * @param from <key노드, key노드까지 도달하는 최단경로의 직전 노드> */
    fun <T> dijkstra(graph:ListGraph<T, Int>, start:T, from:HashMap<T, T> = hashMapOf()): HashMap<T, Int> {
        val inf = Int.MAX_VALUE
        val minDistance = hashMapOf<T, Int>()
        graph.graph.keys.forEach {
            minDistance[it] = inf
        }
        minDistance[start] = 0
        //가까운 노드부터 처리하도록 우선순위 큐 사용
        val pq = PriorityQueue<Pair<T, Int>>(kotlin.Comparator { o1, o2 ->
            -o1.second.compareTo(o2.second)
        })
        pq.add(Pair(start, 0))

        while (pq.isNotEmpty()) {
            val current = pq.peek().first   //노드
            val distance = pq.poll().second     //start ~ current 사이의 거리
            if(minDistance[current]!! < distance)
                continue
            graph.graph[current]?.forEach {
                val next = it.first
                val nextDistance = distance + it.second
                if(nextDistance < minDistance[next]!!) {
                    minDistance[next] = nextDistance
                    pq.add(Pair(next, nextDistance))
                    from[next] = current
                }
            }
        }

        return minDistance
    }

    fun <T>getPath(fromMap:HashMap<T, T>, destination: T): List<T> {
        val result = mutableListOf<T>()
        result.add(destination)
        var prev = destination
        while (prev != null) {
            fromMap[prev]?.let {
                result.add(it)
                prev = it
            }?: break
        }
        return result.reversed()
    }

    fun dijkstraTest(){
        val graph = ListGraph<Int, Int>()
        graph.addSingleEdge(1, 2, 2)
        graph.addSingleEdge(1,3,5)
        graph.addSingleEdge(1,4,1)
        graph.addSingleEdge(2,1,2)
        graph.addSingleEdge(2,3,3)
        graph.addSingleEdge(2,4,2)
        graph.addSingleEdge(3,1,5)
        graph.addSingleEdge(3,2,3)
        graph.addSingleEdge(3,4,3)
        graph.addSingleEdge(3,5,1)
        graph.addSingleEdge(3,6,5)
        graph.addSingleEdge(4,1,1)
        graph.addSingleEdge(4,2,2)
        graph.addSingleEdge(4,3,3)
        graph.addSingleEdge(4,5,1)
        graph.addSingleEdge(5,3,1)
        graph.addSingleEdge(5,4,1)
        graph.addSingleEdge(5,6,2)
        graph.addSingleEdge(6,3,5)
        graph.addSingleEdge(6,5,2)

        val from = hashMapOf<Int, Int>()
        println(dijkstra(graph, 1, from))

        println(getPath(from, 3))
        println(getPath(from, 5))
        println(getPath(from, 6))
    }




    //테스트
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val test = Dijkstra()
            test.dijkstraTest()

        }
    }
}