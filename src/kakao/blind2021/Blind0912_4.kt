package kakao.blind2021

import java.util.*
import kotlin.Comparator
import kotlin.collections.HashMap

class Blind0912_4 {

    /**플로이드 와샬 알고리즘 사용 */
    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        var answer = Int.MAX_VALUE

        val inf = Int.MAX_VALUE
        val arr = Array(n + 1) { IntArray(n + 1) { inf } }
        fares.forEach {
            arr[it[0]][it[1]] = it[2]
            arr[it[1]][it[0]] = it[2]
        }
        for (i in 1..n) arr[i][i] = 0
        for (i in 1..n) {
            for (j in 1..n)
                for (k in 1..n) {
                    if (arr[j][i] == inf || arr[i][k] == inf) continue
                    if (arr[j][k] > arr[j][i] + arr[i][k]){
                        arr[j][k] = arr[j][i] + arr[i][k]
                        arr[k][j] = arr[j][k]
                    }
                }
        }

        for (i in 1..n) {
            (arr[s][i] + arr[i][a] + arr[i][b]).let {
                if (it < answer) answer = it
            }
        }

        return answer
    }

    class AdjacencyList {
        val adj = hashMapOf<Int, HashSet<Pair<Int, Int>>>()

        fun addEdge(from: Int, to: Int, v: Int) {
            adj.computeIfAbsent(from) { hashSetOf() }.add(Pair(to, v))
            adj.computeIfAbsent(to) { hashSetOf() }.add(Pair(from, v))
        }


        fun dijkstra(start: Int): HashMap<Int, Int> {
            val inf = Int.MAX_VALUE
            val minDistance = hashMapOf<Int, Int>()
            adj.keys.forEach {
                minDistance[it] = inf
            }
            minDistance[start] = 0
            //가까운 노드부터 처리하도록 우선순위 큐 사용
            val pq = PriorityQueue<Pair<Int, Int>>(Comparator { o1, o2 ->
                -o1.second.compareTo(o2.second)
            })
            pq.add(Pair(start, 0))

            while (pq.isNotEmpty()) {
                val current = pq.peek().first   //노드
                val distance = pq.poll().second     //start ~ current 사이의 거리
                if (minDistance[current]!! < distance)
                    continue
                adj[current]?.forEach {
                    val next = it.first
                    val nextDistance = distance + it.second
                    if (nextDistance < minDistance[next]!!) {
                        minDistance[next] = nextDistance
                        pq.add(Pair(next, nextDistance))
                    }
                }
            }

            return minDistance
        }
    }

    /**다익스트라로 하면 시간초과 뜬다... */
    fun solution2(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        var answer = Int.MAX_VALUE

        val alist = AdjacencyList()
        fares.forEach { alist.addEdge(it[0], it[1], it[2]) }

        val ds = alist.dijkstra(s)
        val da = alist.dijkstra(a)
        val db = alist.dijkstra(b)

        for (i in 1..n) {
            if (ds[i] == null || da[i] == null || db[i] == null) continue
            (ds[i]!! + da[i]!! + db[i]!!).let {
                if (it < answer) answer = it
            }
        }

        return answer
    }
}

fun main() {
    val s = Blind0912_4()
    val r = s.solution(6, 4, 6, 2,
            arrayOf(intArrayOf(4, 1, 10),
                    intArrayOf(3, 5, 24),
                    intArrayOf(5, 6, 2),
                    intArrayOf(3, 1, 41),
                    intArrayOf(5, 1, 24),
                    intArrayOf(4, 6, 50),
                    intArrayOf(2, 4, 66),
                    intArrayOf(2, 3, 22),
                    intArrayOf(1, 6, 25)))
    println(r)
}
// 소요시간(분): 77 (공부 48분)