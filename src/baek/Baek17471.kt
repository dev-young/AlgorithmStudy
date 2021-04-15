package baek

import kotlin.math.abs
import kotlin.math.min

//게리맨더링
class Baek17471 {

    class ListGraph {
        val edges = hashMapOf<Int, HashSet<Int>>()

        fun addEdge(from: Int, to: Int) {
            edges.computeIfAbsent(from) { hashSetOf() }.add(to)
            edges.computeIfAbsent(to) { hashSetOf() }.add(from)
        }
    }

    fun main() {
        var answer = Int.MAX_VALUE
        val n = readLine()!!.toInt()
        val v = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        val graph = ListGraph()
        for (i in 0 until n) {
            readLine()!!.apply {
                if(length > 1) {
                    substring(2).split(" ").toList().map { it.toInt() }.forEach {
                        graph.addEdge(i, it - 1)
                    }
                }
            }
        }
        val disableNode = hashSetOf<Int>()
        fun dfs(graph: ListGraph, i: Int, visited: HashSet<Int>) {
            if (disableNode.contains(i)) return
            visited.add(i)
            graph.edges[i]?.forEach { j ->
                if (!visited.contains(j)
                ) {
                    dfs(graph, j, visited)
                }
            }
        }

        fun powerSet(arr: IntArray, visited: BooleanArray, n: Int, idx: Int) : Boolean{
            if (idx == n) {
                disableNode.clear()
                var startNode = -1
                visited.forEachIndexed { index, b ->
                    if (b) startNode = index
                    else disableNode.add(index)
                }
                if (startNode > -1) {
                    val dfsVisited = hashSetOf<Int>()
                    dfs(graph, startNode, dfsVisited)
                    if (dfsVisited.size == n-disableNode.size) {
                        disableNode.clear()
                        startNode = -1
                        visited.forEachIndexed { index, b ->
                            if (!b) startNode = index
                            else disableNode.add(index)
                        }
                        if (startNode > -1) {
                            dfsVisited.clear()
                            dfs(graph, startNode, dfsVisited)
                            if (dfsVisited.size == n-disableNode.size) {
                                var s1 = 0
                                var s2 = 0
                                visited.forEachIndexed { index, b ->
                                    if (b) s1 += v[index]
                                    else s2 += v[index]
                                }
                                Math.abs(s1 - s2).let {
                                    if (answer > it) answer = it
                                    if (it == 0) {
                                        return true
                                    }
                                }

                            }
                        }
                    }
                }
                return false
            }
            visited[idx] = false
            if (powerSet(arr, visited, n, idx + 1)) return true
            visited[idx] = true
            if (powerSet(arr, visited, n, idx + 1)) return true
            return false
        }

        val arr = v.indices.toList().toIntArray()
        powerSet(arr, BooleanArray(arr.size), arr.size, 1)

        if (answer == Int.MAX_VALUE) println(-1)
        else println(answer)

    }

    /**다른놈꺼 가져옴
     * 아마 비트연산자를 통해 조합을 구하고 dfs로 연결 여부 확인하여 진행하는 듯 하다 (아직 완전히 이해 못함)*/
    fun main2() {
        val n = readLine()!!.toInt()
        val p = readLine()!!.split(" ").map { it.toInt() }
        val g = Array<MutableList<Int>>(n) { _ -> mutableListOf() }
        for (i in 0 until n) {
            val t = readLine()!!.split(" ").map { it.toInt() }
            for (j in 1..t[0]) {
                g[i].add(t[j] - 1)
            }
        }
        val inf = 1e9.toInt()
        var ans = inf
        for (t in 1 until (1 shl n)) {
            var foo = 0
            val c = IntArray(n)
            for (i in 0 until n) {
                c[i] = (t shr i) and 1
                foo += if (c[i] == 1) p[i] else -p[i]
            }
            var cnt = 0
            val was = BooleanArray(n) { false }
            fun dfs(v: Int) {
                was[v] = true
                for (u in g[v]) {
                    if (!was[u] && c[v] == c[u]) {
                        dfs(u)
                    }
                }
            }
            for (i in 0 until n) {
                if (!was[i]) {
                    dfs(i)
                    cnt++
                }
            }
            if (cnt == 2) {
                ans = min(ans, abs(foo))
            }
        }
        println(if (ans == inf) -1 else ans)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val r = Baek17471().main()
        }
    }
}
// 걸린 시간(분): 104