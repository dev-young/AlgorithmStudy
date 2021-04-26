package programmers.challenge

import java.util.*

//https://programmers.co.kr/learn/courses/30/lessons/76503
//level3
class c76503 {

    fun solution(a: IntArray, edges_: Array<IntArray>): Long {
        if (a.sum() != 0) return -1

        var answer: Long = 0
        val edges = Array(a.size) { hashSetOf<Int>()}
        val weight = LongArray(a.size)
        a.forEachIndexed { index, i -> weight[index] = i.toLong() }
        edges_.forEach {
            edges[it[0]].add(it[1])
            edges[it[1]].add(it[0])
        }

        val visited = hashSetOf<Int>()
        val stack = Stack<Pair<Int, Int>>()
        stack.push(Pair(0, 0))
        while (stack.isNotEmpty()) {
            val (node, parent) = stack.pop()
            if(visited.contains(node)) {
                weight[parent] += weight[node]
                answer += Math.abs(weight[node])
                continue
            }
            visited.add(node)
            stack.push(Pair(node, parent))
            for (e in edges[node]) {
                if(visited.contains(e)) continue
                stack.push(Pair(e, node))
            }
        }
        return answer
    }

    /**bfs로 하면 재귀호출이 너무 많아서 런타임 에러 뜨는듯 하다*/
    fun solution2(a: IntArray, edges_: Array<IntArray>): Long {
        var answer: Long = 0
        val edges = Array(a.size) { hashSetOf<Int>()}
        val weight = LongArray(a.size)
        a.forEachIndexed { index, i -> weight[index] = i.toLong() }
        edges_.forEach {
            edges[it[0]].add(it[1])
            edges[it[1]].add(it[0])
        }

        val visited = hashSetOf<Int>()
        fun dfs(node:Int) {
            visited.add(node)
            for (next in edges[node]) {
                if(visited.contains(next)) continue
                dfs(next)
                weight[node] += weight[next]
                answer += Math.abs(weight[next])
            }
        }
        dfs(0)
        return if (weight[0] == 0L) answer
        else -1
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = c76503()
            println(s.solution(intArrayOf(-5,0,2,1,2), arrayOf(intArrayOf(0,1),intArrayOf(3,4),intArrayOf(2,3),intArrayOf(0,3))))
            println(s.solution(intArrayOf(0,1,0), arrayOf(intArrayOf(0,1),intArrayOf(1,2))))
            println(s.solution(intArrayOf(-1,2), arrayOf(intArrayOf(0,1))))
            println(s.solution(intArrayOf(-1,1,1,-1), arrayOf(intArrayOf(0,1), intArrayOf(2,1), intArrayOf(2,3))))
        }
    }
}
// 걸린 시간(분): 114 (트리를 dfs방식으로 순회해서 해결해야하는 문제를 풀 때 재귀호출 제한으로 인해 재귀함수를 사용 못할 경우 스택을 이용하면 반복문으로 재귀호출과 같이 탐색 가능하다.)
