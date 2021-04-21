package baek

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

//사회망 서비스(SNS) (트리에서의 동적 계획법 (Tree DP))
class Baek2533 {

    class Node(val v: Int) {
        var include = 1    //포함했을때의 값
        var exclude = 0 //제외했을때의 값
    }

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        val edges = Array(n + 1) { hashSetOf<Int>() }
        repeat(n - 1) {
            StringTokenizer(readLine()).let {
                val v1 = it.nextToken().toInt()
                val v2 = it.nextToken().toInt()
                edges[v1].add(v2)
                edges[v2].add(v1)
            }
        }

        fun makeTree(edges: Array<HashSet<Int>>, root: Int, parent: Int = -1, tree: HashMap<Int, Node> = hashMapOf()): HashMap<Int, Node> {
            val node = Node(root)
            tree[root] = node
            for (child in edges[root]) {
                if (child != parent) {
                    makeTree(edges, child, root, tree)
                    tree[child]?.let { childNode ->
                        /**자녀노드가 생성되었을때 내가 포함되면 자녀는 포함될수도 안될수도 있다. 따라서 더 작은값을 합산한다.*/
                        node.include += Math.min(childNode.exclude, childNode.include)
                        /**내가 제외되면 자녀는 무조건 포함되어야 한다.*/
                        node.exclude += childNode.include
                    }
                }
            }
            return tree
        }

        val tree = makeTree(edges, 1)

        val min = tree[1]?.let { Math.min(it.include, it.exclude) } ?: 0
        println(min)

        close()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek2533().main()
        }
    }
}
// 걸린 시간(분): 25