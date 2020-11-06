package algorithm

/**합집합 찾는 알고리즘 (서로소 집합 알고리즘)
 * 여러개의 노드가 존재할 때 임의의 노드 두개가 서로 같은 그래프에 속하는지 판별 */
class UnionFind {

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

    private fun test(){
        val parent = IntArray(10).also { it.forEachIndexed { index, i ->
            it[index] = index
        } }

        union(parent, 0, 1)
        union(parent, 3, 2)
        union(parent, 0, 3)
        union(parent, 3, 4)

        union(parent, 5, 6)
        union(parent, 6, 7)
        union(parent, 7, 8)
        union(parent, 8, 9)
        println(parent.contentToString())

        println(isSameParent(parent, 1, 2))
        println(isSameParent(parent, 4, 5))
        println(isSameParent(parent, 3, 0))
        println(isSameParent(parent, 3, 8))
    }

    //테스트
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val test = UnionFind()
            test.test()

        }
    }
}