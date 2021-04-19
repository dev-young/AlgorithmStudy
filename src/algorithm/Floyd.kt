package algorithm

import java.util.*
import kotlin.collections.HashMap

/**플로이드 와샬 알고리즘*/
class Floyd {

    //인접리스트 가중치 그래프
    class AdjacencyList<T, U> {
        val adj = hashMapOf<T, HashSet<Pair<T,U>>>()

        fun setVertex(v: T) {
            if(!adj.containsKey(v)){
                adj[v] = hashSetOf()
            }
        }

        fun addEdge(from: T, to: T, v: U) {
            adj.computeIfAbsent(from) { hashSetOf() }.add(Pair(to, v))
            adj.computeIfAbsent(to) { hashSetOf() }.add(Pair(from, v))
        }

        //단방향 간선
        fun addSingleEdge(from: T, to: T, v:U) {
            adj.computeIfAbsent(from) { hashSetOf() }.add(Pair(to, v))
        }

        fun printGraph(){
            adj.forEach { t, u ->
                println("$t -> $u")
            }
        }
    }

    /**가중치 인접리스트를 이용한 다익스트라 알고리즘
     * @param from <key노드, key노드까지 도달하는 최단경로의 직전 노드> */
    fun <T> floyd(graph:AdjacencyList<T, Int>, start:T, from:HashMap<T, T> = hashMapOf()): HashMap<T, Int> {
        val inf = Int.MAX_VALUE
        val minDistance = hashMapOf<T, Int>()
        graph.adj.keys.forEach {
            minDistance[it] = inf
        }
        minDistance[start] = 0
        //가까운 노드부터 처리하도록 우선순위 큐 사용
        val pq = PriorityQueue<Pair<T, Int>>(kotlin.Comparator { o1, o2 ->
            -o1.second.compareTo(o2.second)
        }) /**프로그래머스에서는 PriorityQueue 사용시 "import java.util.*" 해주기! */
        pq.add(Pair(start, 0))

        while (pq.isNotEmpty()) {
            val current = pq.peek().first   //노드
            val distance = pq.poll().second     //start ~ current 사이의 거리
            if(minDistance[current]!! < distance)
                continue
            graph.adj[current]?.forEach {
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


    /**인접행렬을 사용한 플로이드 알고리즘 */
    fun floyd(arr:Array<IntArray>): Array<IntArray> {
        val inf = Int.MAX_VALUE
        val result = arr.clone()
        for (i in arr.indices) {
            for (j in arr.indices)
                for (k in arr.indices) {
                    if (arr[j][i] == inf || arr[i][k] == inf) continue
                    if(result[j][k] > result[j][i] + result[i][k])
                        result[j][k] = result[j][i] + result[i][k]
                }
        }
        return result
    }




    fun test(){
        val graph = AdjacencyList<Int, Int>()
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
        println(floyd(graph, 1, from))

        println(getPath(from, 3))
        println(getPath(from, 5))
        println(getPath(from, 6))
    }




    //테스트
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Floyd().apply {
                test()
            }

        }
    }
}