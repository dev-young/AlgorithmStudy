package baek

import java.io.BufferedReader
import java.io.InputStreamReader

//트리 (트리)
class Baek4803 {

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

        val testCaseList = arrayListOf<Array<HashSet<Int>>>()

        while (true) {
            val line = readLine()
            if (line == "0 0") break

            line.split(" ").let {
                val n = it[0].toInt()
                val m = it[1].toInt()
                val arr = Array<HashSet<Int>>(n+1){ hashSetOf()}

                for (i in 1 .. m) {
                    readLine().split(" ").let {
                        val v1 = it[0].toInt()
                        val v2 = it[1].toInt()
                        arr[v1].add(v2)
                        arr[v2].add(v1)
                    }
                }
                testCaseList.add(arr)
            }
        }
        val sb = StringBuilder()
        var testCaseNumber = 0
        testCaseList.forEach {
            testCaseNumber++
            var treeCount = 0

            val visited = hashSetOf<Int>()
            val unVisited = (1 until it.size).toHashSet()
            while (unVisited.isNotEmpty()) {
                val target = unVisited.iterator().next()
                unVisited.remove(target)

                var isCycle = false
                fun dfs(current :Int, before:Int) {
                    visited.add(current)
                    unVisited.remove(current)
                    for (n in it[current]) {
                        if(n == before) continue
                        if(visited.contains(n) || current == before) {
                            isCycle = true
                            continue
                        }
                        dfs(n, current)
                    }
                }
                dfs(target, 0)
                if(!isCycle){
                    treeCount++
                }
            }

            sb.append("Case $testCaseNumber: ")
            if (treeCount == 0) sb.append("No trees.\n")
            else if(treeCount == 1) sb.append("There is one tree.\n")
            else sb.append("A forest of $treeCount trees.\n")
        }

        println(sb.toString())

        close()
    }

    class Node<T>(val v: T) {
        var left: Node<T>? = null
        var right: Node<T>? = null
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek4803().main()
        }
    }
}
// 걸린 시간(분): 59