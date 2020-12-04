package kakao.intern2020

import java.util.*

//https://programmers.co.kr/learn/courses/30/lessons/67258?language=kotlin
class Kakao2020_4 {

    //인접리스트 가중치 그래프
    class ListGraph<T> {
        val graph = hashMapOf<T, HashSet<T>>()
        fun addEdge(from: T, to: T) {
            graph.computeIfAbsent(from) { hashSetOf() }.add(to)
            graph.computeIfAbsent(to) { hashSetOf() }.add(from)
        }
    }

    data class Node(val row: Int, val col: Int) {
        fun isSameLine(other: Node): Boolean {
            return row == other.row || col == other.col
        }
    }

    /**@param twoStepBefore 목적지 전전에 위치한 노드들의 집합 */
    fun getPrice(twoStepBefore: HashSet<Node>?, destination: Node): Int {
        if (twoStepBefore.isNullOrEmpty()) return 100
        for (before in twoStepBefore) {
            if (destination.isSameLine(before))
                return 100
        }
        return 600
    }

    /**가중치 인접리스트를 이용한 다익스트라 알고리즘
     * @param from <key노드, key노드까지 도달하는 최단경로의 직전 노드> */
    fun dijkstra(
        graph: ListGraph<Node>,
        start: Node,
        from: HashMap<Node, HashSet<Node>> = hashMapOf()
    ): HashMap<Node, Int> {
        val inf = Int.MAX_VALUE
        val minPrice = linkedMapOf<Node, Int>()
        graph.graph.keys.forEach {
            minPrice[it] = inf
        }
        minPrice[start] = 0
        val pq: Queue<Pair<Node, Int>> = LinkedList()   //우선순위 큐를 사용하면 안된다.
        pq.add(Pair(start, 0))

        while (pq.isNotEmpty()) {
            val current = pq.peek().first   //노드
            val price = pq.poll().second     //start ~ current 사이의 거리
            graph.graph[current]?.forEach {
                val next = it
                val nextDistance = price + getPrice(from[current], it)
                if (nextDistance < minPrice[next]!!) {
                    minPrice[next] = nextDistance
                    pq.add(Pair(next, nextDistance))
                    from.computeIfAbsent(next) { hashSetOf() }.apply {
                        clear()
                        add(current)
                    }
                } else if (nextDistance == minPrice[next]!!)
                    from.computeIfAbsent(next) { hashSetOf() }.add(current)
            }
        }
        return minPrice
    }

    fun solution(board: Array<IntArray>): Int {
        var answer = 0

        val graph = ListGraph<Node>()
        board.forEachIndexed { row, ints ->
            ints.forEachIndexed { col, int ->
                if (int == 0) {
                    val node = Node(row, col)
                    val rightCol = col + 1
                    val bottomRow = row + 1
                    if (rightCol < ints.size && board[row][rightCol] == 0)
                        graph.addEdge(node, Node(row, rightCol))
                    if (bottomRow < board.size && board[bottomRow][col] == 0)
                        graph.addEdge(node, Node(bottomRow, col))
                }
            }
        }

        dijkstra(graph, Node(0, 0)).let {
            it[Node(board.size - 1, board.size - 1)]?.let {
                answer = it
            }
        }
        return answer
    }

    /*
    다익스트라를 사용하여 문제를 해결했지만 좀 더 간단한 방법으로 bfs를 사용하여 모든 경로를 탐색하면서 최소비용으로 도달하는 경로를 찾는 방법도 있다.
    */

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Kakao2020_4()
//            val p = arrayOf<IntArray>(intArrayOf(0, 0, 1, 0),
//                intArrayOf(0, 0, 0, 0),
//                intArrayOf(0, 1, 0, 1),
//                intArrayOf(1, 0, 0, 0))

            val p = arrayOf(intArrayOf(0, 0, 0, 0, 0, 0),
                intArrayOf(0, 1, 1, 1, 1, 0),
                intArrayOf(0, 0, 1, 0, 0, 0),
                intArrayOf(1, 0, 0, 1, 0, 1),
                intArrayOf(0, 1, 0, 0, 0, 1),
                intArrayOf(0, 0, 0, 0, 0, 0))


            var r = s.solution(p)
            println(r)
        }
    }
}
// 걸린 시간: 94분 (다익스트라 공부하는데 2시간 사용)