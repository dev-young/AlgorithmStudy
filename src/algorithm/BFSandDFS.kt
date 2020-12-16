package algorithm

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet


/**간선의 수가 적은 희소그래프일 경우 인접행렬보다 인접리스트를 사용하는것이 유리
 * https://devuna.tistory.com/32 참고 (정리 깔끔) */

class BFSandDFS {

    //인접리스트 그래프
    class ListGraph<T> {
        val graph = hashMapOf<T, HashSet<T>>()

        fun setVertex(v: T) {
            if (!graph.containsKey(v)) {
                graph[v] = hashSetOf()
            }
        }

        fun addEdge(from: T, to: T) {
            graph.computeIfAbsent(from) { hashSetOf() }.add(to)
            graph.computeIfAbsent(to) { hashSetOf() }.add(from)
        }

        //단방향 간선
        fun addSingleEdge(from: T, to: T) {
            graph.computeIfAbsent(from) { hashSetOf() }.add(to)
        }

        fun printGraph() {
            graph.forEach { t, u ->
                println("$t -> $u")
            }
        }
    }

    private fun bfs(arr: Array<IntArray>, root: Int = 0) {
        val visited = BooleanArray(arr.size) { false }
        val que: Queue<Int> = LinkedList()
        que.offer(root)
        while (!que.isEmpty()) {
            val i = que.poll()
            if (visited[i]) continue
            visited[i] = true
            println("방문 노드:$i")
//            for (j in i until arr.size) //방향성이 없는 경우 // TODO: 테스트 필요
            for (j in arr.indices) {
                if (arr[i][j] == 1 &&   /*간선이 존재하는경우*/
                    i != j &&           /*자기 자신이 아닌 경우*/
                    !visited[j]         /*방문한적이 없는 경우*/
                ) {
                    que.offer(j)
                }
            }
        }
    }

    private fun bfsPrintLevel(arr: Array<IntArray>, root: Int = 0) {
        val visited = BooleanArray(arr.size) { false }
        val que: Queue<Int> = LinkedList()
        que.offer(root)
        var level = 0
        while (!que.isEmpty()) {
            val qSize = que.size
            for (n in 1..qSize) {
                val i = que.poll()
                if (visited[i]) continue
                visited[i] = true
                println("방문 노드:$i  level:$level")
//            for (j in i until arr.size) //방향성이 없는 경우 // TODO: 테스트 필요
                for (j in arr.indices) {
                    if (arr[i][j] == 1 &&   /*간선이 존재하는경우*/
                        i != j &&           /*자기 자신이 아닌 경우*/
                        !visited[j]         /*방문한적이 없는 경우*/
                    ) {
                        que.offer(j)
                    }
                }
            }
            level++

        }
    }

    private fun bfsPrintLevel(graph: ListGraph<Int>, root: Int = 0) {
        val graph = graph.graph
        val visited = hashSetOf<Int>()
        val que: Queue<Int> = LinkedList()
        que.offer(root)
        var level = 0
        while (!que.isEmpty()) {
            val qSize = que.size
            for (n in 1..qSize) {       //level을 알기 위해 반복문을 하나 더 사용한다.
                val i = que.poll()
                if (visited.contains(i)) continue
                visited.add(i)
                println("방문 노드:$i  level:$level")
                for (j in graph[i]!!) {
                    que.offer(j)
                }
            }
            level++

        }
    }

    private fun bfsTest() {
        val arr = arrayOf(
            intArrayOf(1, 1, 0, 1, 1),
            intArrayOf(0, 1, 1, 0, 1),
            intArrayOf(0, 0, 1, 0, 1),
            intArrayOf(0, 0, 0, 1, 0),
            intArrayOf(0, 0, 0, 0, 1))

        bfsPrintLevel(arr)

        println("인접리스트로 표현")
        val listGraph = ListGraph<Int>()
        arr.forEachIndexed { i, ints ->
            ints.forEachIndexed { j, int ->
                if (int == 1)
                    listGraph.addEdge(i, j)
            }
        }
        listGraph.printGraph()
        bfsPrintLevel(listGraph)

    }

    /**인접행렬 사용*/
    private fun dfs(arr: Array<IntArray>, visited: BooleanArray, i: Int) {
        visited[i] = true
        println("탐색된 노드:$i")
        for (j in arr.indices) {
            if (!visited[j] &&      /*방문하지 않았을 경우*/
                arr[i][j] == 1 &&   /*간선이 존재하는 경우*/
                i != j              /*자기 자신이 아닌 경우*/
            ) {
                /*노드 j가 노드 i에 연결되어있음*/
                dfs(arr, visited, j)
            }
        }
    }

    /**인접리스트 사용*/
    private fun dfs(graph: ListGraph<Int>, i: Int, visited: HashSet<Int> = hashSetOf()) {
        visited.add(i)
        println("탐색된 노드:$i")
        graph.graph[i]?.forEach { j->
            if (!visited.contains(j) /*방문하지 않았을 경우*/
            ) {
                /*노드 j가 노드 i에 연결되어있음*/
                dfs(graph, j, visited)
            }
        }
    }

    private fun dfsTest() {
        val arr = arrayOf(
            intArrayOf(1, 1, 0, 1, 1),
            intArrayOf(0, 1, 1, 0, 1),
            intArrayOf(0, 0, 1, 0, 1),
            intArrayOf(0, 0, 0, 1, 0),
            intArrayOf(0, 0, 0, 0, 1))

        val visited = BooleanArray(arr.size) { false }
        dfs(arr, visited, 0)

        println("인접리스트로 표현")
        val listGraph = ListGraph<Int>()
        arr.forEachIndexed { i, ints ->
            ints.forEachIndexed { j, int ->
                if (int == 1)
                    listGraph.addEdge(i, j)
            }
        }
//        listGraph.printGraph()
        dfs(listGraph, 0)
    }

    /**모든 노드를 방문하는 경로 탐색
     * @param arr 인접행렬
     * @return 모든 노드를 방문하는 경로들을 반환한다.
     * */
    fun pathVisitedAll(arr: Array<IntArray>, root: Int = 0): ArrayList<IntArray> {
        //간선 리스트 {출발지, 도착지}
        val pathList = arrayListOf<IntArray>().let {
            for (i in arr.indices) {
                for (j in arr[i].indices) {
                    if (arr[i][j] == 1 && i != j)
                        it.add(intArrayOf(i, j))
                }
            }
            it.toTypedArray()
        }
        val visited = BooleanArray(pathList.size) { false }

        pathList.forEach { println(it.contentToString()) }

        val answers = arrayListOf<IntArray>()
        val destinations = arrayListOf<Int>().apply { add(root) }
        fun pathVisitedAll(arr: Array<IntArray>, i: Int, visited: BooleanArray = BooleanArray(arr.size) { false }) {
//        println("탐색된 노드:$i, ${arr[i].contentToString()}")
            visited[i] = true
            destinations.add(arr[i][1])
            if (destinations.size == arr.size + 1) {
                if (!visited.contains(false))
                    answers.add(destinations.toIntArray())
//                return    //리턴할경우 한개의 결과만 찾고 더이상 찾지 않는다.
            }
            for (j in arr.indices) {
                if (!visited[j]      /*방문하지 않았을 경우*/
                    && arr[i][1] == arr[j][0]     /*간선이 존재하는 경우*/
                    && i != j   /*자기 자신이 아닐때*/
                ) {
                    /*노드 j가 노드 i에 연결되어있음*/
                    pathVisitedAll(arr, j, visited)
                }
            }
            visited[i] = false
            destinations.removeAt(destinations.lastIndex)
        }

        pathList.forEachIndexed { index, ints ->
            if (root == ints[0])
                pathVisitedAll(pathList, index, visited)
        }


        return answers
    }

    fun pathVisitedAll(graph: ListGraph<Int>, root: Int = 0): ArrayList<IntArray> {
        //간선 리스트 {출발지, 도착지}
        val pathList = arrayListOf<IntArray>().let {
            graph.graph.forEach { start, u ->
                u.forEach {  end->
                    it.add(intArrayOf(start, end))
                }
            }
            it.toTypedArray()
        }
        val visited = BooleanArray(pathList.size) { false }

        pathList.forEach { println(it.contentToString()) }

        val answers = arrayListOf<IntArray>()
        val destinations = arrayListOf<Int>().apply { add(root) }
        fun pathVisitedAll(i: Int, visited: BooleanArray = BooleanArray(pathList.size) { false }) {
//        println("탐색된 노드:$i, ${arr[i].contentToString()}")
            visited[i] = true
            destinations.add(pathList[i][1])
            if (destinations.size == pathList.size + 1) {
                if (!visited.contains(false))
                    answers.add(destinations.toIntArray())
//                return    //리턴할경우 한개의 결과만 찾고 더이상 찾지 않는다.
            } else {
                for (j in pathList.indices) {
                    if (!visited[j]      /*방문하지 않았을 경우*/
                        && pathList[i][1] == pathList[j][0]     /*간선이 존재하는 경우*/
                        && i != j   /*자기 자신이 아닐때*/
                    ) {
                        /*노드 j가 노드 i에 연결되어있음*/
                        pathVisitedAll(j, visited)
                    }
                }
            }
            visited[i] = false
            destinations.removeAt(destinations.lastIndex)
        }

        pathList.forEachIndexed { index, ints ->
            if (root == ints[0])
                pathVisitedAll(index, visited)
        }


        return answers
    }

    fun pathVisitedAllTest() {
        val arr = arrayOf(
            intArrayOf(1, 1, 1),
            intArrayOf(1, 1, 0),
            intArrayOf(1, 0, 1))

        val result = pathVisitedAll(arr)
        result.forEach {
            println(it.contentToString())
        }
    }


    // TODO: 인접리스트를 사용할 경우


    //테스트
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val test = BFSandDFS()
//            test.bfsTest()
            test.dfsTest()
        }
    }
}