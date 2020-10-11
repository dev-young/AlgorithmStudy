package programmers

import kotlin.Array
import kotlin.Boolean
import kotlin.BooleanArray
import kotlin.Comparator
import kotlin.Int
import kotlin.String
import kotlin.arrayOf


//https://programmers.co.kr/learn/courses/30/lessons/43164?language=kotlin
/**모든 정점을 방문하는 경로를 찾는 문제*/
class BFSDFS04 {
    fun solution(tickets: Array<Array<String>>): Array<String> {

        tickets.forEachIndexed { index, strings ->
            if (strings[0] == "ICN") {
                destinations.clear()
                destinations.add("ICN")
                dfs(tickets, index)
            }
        }

        answers.sortWith(Comparator { o1, o2 ->
            var r = 0
            for (i in o1.indices) {
                r = o1[i].compareTo(o2[i])
                if (r != 0) return@Comparator r
            }
            r
        })
        answers.forEach {println(it.contentToString())}
        println()
        if (answers.isEmpty()) answers.add(arrayOf())

        return answers[0]
    }

    val answers = arrayListOf<Array<String>>()
    val destinations = arrayListOf<String>()
    fun dfs(arr: Array<Array<String>>, i: Int = 0, visited: BooleanArray = BooleanArray(arr.size) { false }) {
//        println("탐색된 노드:${arr[i].contentToString()}")
        visited[i] = true
        destinations.add(arr[i][1])
        if (destinations.size == arr.size + 1) {
            if(!visited.contains(false))
                answers.add(destinations.toTypedArray())
        }
        for (j in arr.indices) {
            if (!visited[j]      /*방문하지 않았을 경우*/
                && isLinked(arr[i], arr[j])    /*간선이 존재하는 경우*/
                && i != j
            ) {
                /*노드 j가 노드 i에 연결되어있음*/
                dfs(arr, j, visited)
            }
        }
        visited[i] = false
        destinations.removeAt(destinations.lastIndex)
    }

    private fun isLinked(node1: Array<String>, node2: Array<String>): Boolean {
        return node1[1] == node2[0]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = BFSDFS04()
//            val strs = arrayOf(
//                arrayOf("ICN", "SFO"),
//                arrayOf("ICN", "ATL"),
//                arrayOf("SFO", "ATL"),
//                arrayOf("ATL", "ICN"),
//                arrayOf("ATL", "SFO"))

//            val strs = arrayOf(
//                arrayOf("ICN", "A"),
////                arrayOf("A", "C"),
//                arrayOf("ICN", "B"),
////                arrayOf("B", "C"),
//                arrayOf("B", "ICN"),
//                arrayOf("A", "ICN"))

            val strs = arrayOf(
                arrayOf("ICN", "B"),//
                arrayOf("ICN", "C"),//
                arrayOf("C", "D"),//
                arrayOf("D", "C"),//
                arrayOf("B", "D"),//
                arrayOf("D", "B"),//
                arrayOf("B", "ICN"),//
                arrayOf("C", "B"))//

//            val strs = arrayOf(
//                arrayOf("ICN", "A"),
//                arrayOf("ICN", "A"),
//                arrayOf("A", "ICN"))
            val r = s.solution(strs)
//            val r = Solution().solution(strs)
            println(r.contentDeepToString())
        }
    }
}

// 걸린 시간: 분