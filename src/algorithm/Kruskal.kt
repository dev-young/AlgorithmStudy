package algorithm

/**최소비용신장트리 만드는 알고리즘 중 한가지.
 * UnionFind 알고리즘을 사용해서 사이클 발생 여부를 확인한 뒤 사이클이 발생하지 않으면 트리에 포함시킨다.*/
class Kruskal {

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

    /**@param nodeCount 정점의 갯수
     * @param edges 간선 정보 [{node1, node2, 가중치}]
     * @return 최소비용신장트리를 만들 수 있는 간선들의 리스트를 반환한다. */
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

    private fun test(){
        val nodeCount = 6
        val edges = ArrayList<IntArray>().apply {
            add(intArrayOf(0,1,7))
            add(intArrayOf(0,2,1))
            add(intArrayOf(0,3,9))
            add(intArrayOf(1,2,6))
            add(intArrayOf(2,5,24))
            add(intArrayOf(3,4,31))
            add(intArrayOf(4,5,11))
        }

        val result = kruskal(nodeCount, edges.toTypedArray())
        result.forEach { println(it.contentToString()) }



    }

    //테스트
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val test = Kruskal()
            test.test()

        }
    }
}