package kakao.intern2020

import java.util.*

//https://programmers.co.kr/learn/courses/30/lessons/67260?language=kotlin
class Kakao2020_5 {
    fun solution(n: Int, path: Array<IntArray>, order: Array<IntArray>): Boolean {
        val postMap = hashMapOf<Int, Int>()
        val preMap = hashMapOf<Int, Int>()
        val edges = Array(n + 1) { hashSetOf<Int>() }
        path.forEach {
            edges[it[0]].add(it[1])
            edges[it[1]].add(it[0])
        }
        order.forEach {
            preMap[it[1]] = it[0]
            postMap[it[0]] = it[1]
        }
        if (preMap.containsKey(0)) return false
        val visited = hashSetOf<Int>()
        val waiting = hashSetOf<Int>()
        val que: Queue<Int> = LinkedList()
        que.offer(0)
        postMap[0]?.let {
            preMap.remove(it)
            if(waiting.contains(it))
                que.offer(it)
        }
        postMap.remove(0)
        while (que.isNotEmpty()) {
            val node = que.poll()
            visited.add(node)
            waiting.remove(node)
            println(node)

            for (e in edges[node]) {
                if (visited.contains(e)) continue
                if (preMap.containsKey(e)) {
                    waiting.add(e)
                    continue
                }
                que.offer(e)
                postMap[e]?.let {
                    preMap.remove(it)
                    if(waiting.contains(it))
                        que.offer(it)
                }
                postMap.remove(e)

            }
        }
        return visited.size == n
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
// 걸린 시간: 141분