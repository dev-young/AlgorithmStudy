package programmers.practice

import java.util.*

//https://programmers.co.kr/learn/courses/30/lessons/68647
//level4
class P62050 {

    class Group(val num: Int) {
        val members = hashMapOf<Pair<Int, Int>, Int>()

        val adj = hashMapOf<Int, Int>()

        fun add(member: Pair<Int, Int>, h: Int) {
            members[member] = h
        }

        fun addAdj(groupNum: Int, diff: Int) {
            adj[groupNum] = Math.min(adj[groupNum] ?: Int.MAX_VALUE, diff)
        }

        fun absolve(child: Group) {
            members.putAll(child.members)
        }
    }

    fun solution(land: Array<IntArray>, limit: Int): Int {

        val moves = arrayOf(intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(0, -1))
        val group = hashMapOf<Pair<Int, Int>, Group>()
        val groupMap = hashMapOf<Int, Group>()
        var groupNum = 1

        fun mergeGroup(parent: Group, child: Group) {
            parent.absolve(child)
            child.members.forEach { group[it.key] = parent }
            groupMap.remove(child.num)
        }

        val que: Queue<Pair<Int, Int>> = LinkedList()
        val first = Pair(0, 0)
        group[first] = Group(groupNum++).also {
            it.add(first, land[0][0])
            groupMap[it.num] = it
        }
        que.offer(first)
        while (que.isNotEmpty()) {
            val p = que.poll()
            val h = land[p.first][p.second]
            val g = group[p]!!

            for (move in moves) {
                val nr = p.first + move[0]
                val nc = p.second + move[1]
                if (nr in land.indices && nc in land.indices) {
                    val np = Pair(nr, nc)
                    val nh = land[nr][nc]
                    if (group.containsKey(np)) {
                        val ng = group[np]!!
                        if (ng.num != g.num) {
                            //np가 속한 그룹이 p가 속한 그룹과 다르고
                            //p가 np와 이어질 수 있으면 ng를 g에 포함시킨다.
                            if (Math.abs(nh - h) <= limit) {
                                if (g.num < ng.num) mergeGroup(g, ng)
                                else mergeGroup(ng, g)
                            }
                        }
                        continue
                    }

                    if (Math.abs(nh - h) <= limit) {
                        //np와 p의 높이차가 limit 이하이면 g에 포함시킨다.
                        g.add(np, nh)
                        group[np] = g
                    } else {
                        //np와 p의 높이차가 limit 보다 크면 새로운 그룹을 생성한다.
                        val ng = Group(groupNum++).also {
                            it.add(np, nh)
                            groupMap[it.num] = it
                        }
                        group[np] = ng
                    }

                    que.offer(np)
                }

            }
        }

        que.offer(first)
        val vis = hashSetOf<Pair<Int,Int>>()
        while (que.isNotEmpty()) {
            val p = que.poll()
            val h = land[p.first][p.second]
            val g = group[p]!!

            for (move in moves) {
                val nr = p.first + move[0]
                val nc = p.second + move[1]
                if (nr in land.indices && nc in land.indices) {
                    val np = Pair(nr, nc)
                    val nh = land[nr][nc]
                    val ng = group[np]!!

                    if (ng.num != g.num) {
                        //np가 속한 그룹이 p가 속한 그룹과 다르고
                        g.addAdj(ng.num, Math.abs(nh - h))
                    }
                    if (vis.contains(np)) continue
                    vis.add(np)
                    que.offer(np)
                }

            }
        }

        val rSet = hashMapOf<Pair<Int, Int>, Int>()
        groupMap.values.forEach {
            it.adj.forEach { t, u ->
                if (groupMap.containsKey(t)) {
                    val p = if (it.num < t) Pair(it.num, t) else Pair(t, it.num)
                    rSet[p] = Math.min(rSet[p] ?: Int.MAX_VALUE, u)
                }
            }
        }

        val cycleTable = hashMapOf<Int,Int>()
        groupMap.keys.forEach {
            cycleTable[it] = it
        }

        val sortedSet = rSet.map { (t, u) -> intArrayOf(t.first, t.second, u) }.sortedBy { it[2] }
        return kruskal(cycleTable, sortedSet).map { it[2] }.sum()

    }

    fun getParent(parent: HashMap<Int, Int>, node: Int): Int {
        parent[node].let {
            if (it == node) return node
            return getParent(parent, it!!)
        }
    }

    fun union(parent: HashMap<Int, Int>, node1: Int, node2: Int) {
        val n1 = getParent(parent, node1)
        val n2 = getParent(parent, node2)
        if (n1 < n2)
            parent[n2] = n1
        else
            parent[n1] = n2
    }

    fun isSameParent(parent: HashMap<Int, Int>, node1: Int, node2: Int): Boolean {
//        print("$node1  $node2 -> ")
        return getParent(parent, node1) == getParent(parent, node2)
    }

    /**@param nodeCount 정점의 갯수
     * @param edges 간선 정보 [{node1, node2, 가중치}]
     * @return 최소비용신장트리를 만들 수 있는 간선들의 리스트를 반환한다. */
    fun kruskal(cycleTable: HashMap<Int,Int>, edges: List<IntArray>): List<IntArray> {
        val resultEdges = arrayListOf<IntArray>()
//        val cycleTable = IntArray(nodeCount).apply { indices.forEach { this[it] = it } }

        for (edge in edges) {
            if (!isSameParent(cycleTable, edge[0], edge[1])) {    //두 정점이 같은 부모를 가지고 있다면 두 노드를 연결했을 때 싸이클이 발생한다.
                //싸이클이 발생하지 않는 경우
                resultEdges.add(edge)
                union(cycleTable, edge[0], edge[1])
                if (resultEdges.size == cycleTable.size - 1)
                    break   //최소비용트리의 간선 갯수는 (전체 노드 수 - 1)
            }
        }
        return resultEdges
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = P62050()
//            println(s.solution(arrayOf(intArrayOf(1, 1, 1, 1), intArrayOf(1, 5, 5, 1), intArrayOf(1, 10, 10, 3), intArrayOf(1, 1, 2, 3)), 1))
//            println(s.solution(arrayOf(intArrayOf(10, 30, 50, 70), intArrayOf(7, 50, 30, 10), intArrayOf(10, 30, 50, 70), intArrayOf(70, 50, 30, 10)), 1))
            println(s.solution(arrayOf(intArrayOf(1, 4, 8, 10), intArrayOf(5, 5, 5, 5), intArrayOf(30, 10, 40, 10), intArrayOf(10, 10, 40, 20)), 3))
            println(s.solution(arrayOf(intArrayOf(10, 11, 10, 11), intArrayOf(2, 21, 20, 10), intArrayOf(1, 20, 21, 11), intArrayOf(2, 1, 2, 1)), 1))

        }
    }
}
// 걸린 시간(분): 298분 동안 했지만 실패... 나중에 천천히 다시 풀어보자
