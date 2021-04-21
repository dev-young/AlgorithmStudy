package baek

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

//트리와 쿼리 (트리에서의 동적 계획법 (Tree DP))
class Baek15681 {

    class Node(val v: Int, val parent: Int) {
        val child = arrayListOf<Int>()
        var vCount = 1  //현재 노드를 루트로 하는 서브트리의 정점의 수를 저장
    }

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        var bw = BufferedWriter(OutputStreamWriter(System.out))

        val temp = readLine().split(" ")
        val n = temp[0].toInt()
        val root = temp[1].toInt()
        val qCount = temp[2].toInt()
        val edges = Array(n+1){ hashSetOf<Int>()}
        val queries = arrayListOf<Int>()
        repeat(n-1) {
            readLine().split(" ").let {
                val v1 = it[0].toInt()
                val v2 = it[1].toInt()
                edges[v1].add(v2)
                edges[v2].add(v1)
            }
        }

        for (i in 0 until qCount)
            queries.add(readLine().toInt())

        fun makeTree(edges: Array<HashSet<Int>>, root: Int, parent: Int = -1, tree: HashMap<Int, Node> = hashMapOf()): HashMap<Int, Node> {
            val node = Node(root, parent)
            tree[root] = node
            for (child in edges[root]) {
                if(child != parent) {
                    node.child.add(child)
                    makeTree(edges, child, root, tree)
                    node.vCount += tree[child]?.vCount ?: 0
                }
            }
            return tree
        }

        val tree = makeTree(edges, root)

        queries.forEach {
            bw.write("${tree[it]?.vCount}\n")
        }


        close()
        bw.close()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek15681().main()
        }
    }
}
// 걸린 시간(분): 19