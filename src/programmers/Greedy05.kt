package programmers

//https://programmers.co.kr/learn/courses/30/lessons/42861?language=kotlin
class Greedy05 {

    fun solution(n: Int, costs: Array<IntArray>): Int {
        return kruskal(n, costs).sumBy { it[2] }
    }

    fun getParent(parent: IntArray, node:Int): Int {
        parent[node].let {
            if(it == node) return it
            return getParent(parent, it)
        }
    }

    fun union(parent: IntArray, node1:Int, node2:Int){
        val n1 = getParent(parent, node1)
        val n2 = getParent(parent, node2)
        if(n1 < n2)
            parent[n2] = n1
        else
            parent[n1] = n2
    }

    fun isSameParent(parent: IntArray, node1:Int, node2:Int):Boolean{
//        print("$node1  $node2 -> ")
        return getParent(parent, node1) == getParent(parent, node2)
    }

    fun kruskal(nodeCount: Int, edges: Array<IntArray>): List<IntArray> {
        val resultEdges = arrayListOf<IntArray>()
        val cycleTable = IntArray(nodeCount).apply { indices.forEach { this[it] = it } }

        //간선들을 가중치의 오름차순으로 정렬
        edges.sortBy { it[2] }

        for (edge in edges) {
            if(!isSameParent(cycleTable, edge[0], edge[1])){    //두 정점이 같은 부모를 가지고 있다면 두 노드를 연결했을 때 싸이클이 발생한다.
                //싸이클이 발생하지 않는 경우
                resultEdges.add(edge)
                union(cycleTable, edge[0], edge[1])
                if(resultEdges.size == nodeCount-1)
                    break   //최소비용트리의 간선 갯수는 (전체 노드 수 - 1)
            }
        }
        return resultEdges
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Greedy05()
            val r = s.solution(4, arrayOf(intArrayOf(0,1,1),intArrayOf(0,2,2),intArrayOf(1,2,5),intArrayOf(1,3,1),intArrayOf(2,3,8)))
            println(r)
        }
    }
}
// 걸린 시간: 9분