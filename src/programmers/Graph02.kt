package programmers

import kotlin.collections.HashSet

//https://programmers.co.kr/learn/courses/30/lessons/49191?language=kotlin
class Graph02 {

    class ListGraph<T> {
        val graph = hashMapOf<T, HashSet<T>>()
        fun addEdge(from: T, to: T) {
            graph.computeIfAbsent(from) { hashSetOf() }.add(to)
        }

        fun printGraph() {
            graph.forEach { t, u ->
                println("$t -> $u")
            }
        }
    }

    fun solution(n: Int, results: Array<IntArray>): Int {
        var answer = 0

        val listGraph = ListGraph<Int>()
        val listGraphInv = ListGraph<Int>()
        results.forEach {
            listGraph.addEdge(it[0], it[1])
            listGraphInv.addEdge(it[1], it[0])
        }

        listGraph.printGraph()
        listGraphInv.printGraph()

        fun findCompleteSet(result: HashSet<Int>, targetGraph: ListGraph<Int>, start: HashSet<Int>) {
            start.forEach {
                if(!result.contains(it))
                    targetGraph.graph[it]?.let { findCompleteSet(result, targetGraph, it) }
            }
            result.addAll(start)
        }

        for (v in 1..n) {
            val completeSet = hashSetOf(v)//확정적으로 결과를 알 수 있는 집합
            listGraph.graph[v]?.let { findCompleteSet(completeSet, listGraph, it) }
            listGraphInv.graph[v]?.let { findCompleteSet(completeSet, listGraphInv, it) }
            println("$v -> $completeSet")

            if(completeSet.size == n) answer++
        }

        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Graph02()
            val r = s.solution(5, arrayOf(
                intArrayOf(4, 3),
                intArrayOf(4, 2),
                intArrayOf(3, 2),
                intArrayOf(1, 2),
                intArrayOf(2, 5)))
            println(r)
        }
    }
}
// 걸린 시간: 90분