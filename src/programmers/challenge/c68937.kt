package programmers.challenge

import java.util.*
import kotlin.collections.HashSet
import kotlin.math.max


//https://programmers.co.kr/learn/courses/30/lessons/68937
//level4
class c68937 {

    fun solution(n: Int, edges: Array<IntArray>): Int {
        val edgeList = Array(n + 1) { hashSetOf<Int>() }
        edges.forEach {
            edgeList[it[0]].add(it[1])
            edgeList[it[1]].add(it[0])
        }

        fun getMaxLength(edges: Array<HashSet<Int>>, start: Int): Pair<List<Int>, Int> {
            val visited = BooleanArray(edges.size + 1) { false }
            val dist = IntArray(edges.size + 1)
            val que: Queue<Int> = LinkedList()
            que.offer(start)
            while (que.isNotEmpty()) {
                val node = que.poll()
                visited[node] = true
                for (next in edges[node]) {
                    if (visited[next] || node == next) continue
                    que.offer(next)
                    dist[next] = dist[node] + 1
                }
            }
            var maxDist = 1 //최대거리
            val result = arrayListOf<Int>()
            dist.forEachIndexed { index, i ->
                if (maxDist < i) {
                    maxDist = i
                    result.clear()
                    result.add(index)
                } else if (maxDist == i) {
                    result.add(index)
                }
            }
            return Pair(result, maxDist)
        }

        val r =getMaxLength(edgeList, getMaxLength(edgeList, 1).first[0])
        return if (r.first.size > 1) {
            r.second
        } else {
            getMaxLength(edgeList, r.first[0]).let {
                if (it.first.size > 1) {
                    it.second
                } else {
                    it.second - 1
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = c68937()
//            println(s.solution(arrayOf(intArrayOf(1, 1, 1, 1, 1, 1, 1, 1), intArrayOf(0, 1, 1, 1, 1, 1, 1, 1), intArrayOf(0, 0, 0, 0, 1, 1, 1, 1), intArrayOf(0, 1, 0, 0, 1, 1, 1, 1), intArrayOf(0, 0, 0, 0, 0, 0, 1, 1), intArrayOf(0, 0, 0, 0, 0, 0, 0, 1), intArrayOf(0, 0, 0, 0, 1, 0, 0, 1), intArrayOf(0, 0, 0, 0, 1, 1, 1, 1))).contentToString())
//            println(s.solution(4, arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4))))
            println(s.solution(11, arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4), intArrayOf(4, 5), intArrayOf(5, 6), intArrayOf(6, 7), intArrayOf(7, 8), intArrayOf(8, 9), intArrayOf(6, 10), intArrayOf(10, 11))))

        }
    }
}
// 걸린 시간: 120분
