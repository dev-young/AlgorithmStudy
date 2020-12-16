package programmers.skillcheck

class Level3_1 {

    data class Node(val n: Int, val x: Int, val y: Int)

    //인접리스트 그래프
    class ListGraph {
        val graph = hashMapOf<Int, LinkedHashSet<Node>>()

        fun addSingleEdge(from: Node, to: Node) {
            graph.computeIfAbsent(from.n) { linkedSetOf() }.add(to)
        }

    }

    fun solution(nodeinfo: Array<IntArray>): Array<IntArray> {
        val answer1 = arrayListOf<Int>()
        val answer2 = arrayListOf<Int>()
        val nodeList = nodeinfo.mapIndexed { index, ints ->
            Node(index + 1, ints[0], ints[1])
        }.sortedWith(Comparator { o1, o2 ->
            o1.y.compareTo(o2.y).let { if (it == 0) o1.x.compareTo(o2.x) else -it }
        })

        val graph = ListGraph()

        fun link(childs: List<Node>) {
            if (childs.isEmpty()) return
            val lefts = arrayListOf<Node>()
            val rights = arrayListOf<Node>()
            val parent = childs[0]
            for (i in 1 until childs.size) {
                val child = childs[i]
                if (parent.x < child.x) rights.add(child)
                else lefts.add(child)
            }
            if (lefts.isNotEmpty()) graph.addSingleEdge(parent, lefts[0])
            if (rights.isNotEmpty()) graph.addSingleEdge(parent, rights[0])
            link(lefts)
            link(rights)
        }

        link(nodeList)

        fun dfs(graph: ListGraph, i: Node, visited: HashSet<Int> = hashSetOf()) {
            visited.add(i.n)
            answer1.add(i.n)
            graph.graph[i.n]?.forEach { j ->
                if (!visited.contains(j.n) /*방문하지 않았을 경우*/
                ) {
                    /*노드 j가 노드 i에 연결되어있음*/
                    dfs(graph, j, visited)
                }
            }
            answer2.add(i.n)
        }

        dfs(graph, nodeList[0])

        return arrayOf(answer1.toIntArray(), answer2.toIntArray())
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Level3_1()
            println(s.solution(arrayOf(
                intArrayOf(8, 6),
                intArrayOf(3, 5),
                intArrayOf(11, 5),
                intArrayOf(1, 3),
                intArrayOf(13, 3)
            )
            ).contentDeepToString())

//            println(s.solution(arrayOf(intArrayOf(5, 3),
//                intArrayOf(11, 5),
//                intArrayOf(13, 3),
//                intArrayOf(3, 5),
//                intArrayOf(1, 3),
//                intArrayOf(8, 6),
//                intArrayOf(7, 2),
//                intArrayOf(2, 2))).contentDeepToString())

        }
    }

}