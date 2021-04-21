package baek

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

//우수 마을 (트리에서의 동적 계획법 (Tree DP))
class Baek1949 {

    class Node(val v: Int, weight:Int) {
        var include = weight    //포함했을때의 값
        var exclude = 0 //제외했을때의 값
    }

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        val edges = Array(n + 1) { hashSetOf<Int>() }
        val w = arrayListOf<Int>()
        StringTokenizer(readLine()).let {
            while (it.hasMoreTokens())
                w.add(it.nextToken().toInt())
        }
        repeat(n - 1) {
            StringTokenizer(readLine()).let {
                val v1 = it.nextToken().toInt()
                val v2 = it.nextToken().toInt()
                edges[v1].add(v2)
                edges[v2].add(v1)
            }
        }

        fun makeTree(edges: Array<HashSet<Int>>, root: Int, parent: Int = -1, tree: HashMap<Int, Node> = hashMapOf()): HashMap<Int, Node> {
            val node = Node(root, w[root-1])
            tree[root] = node
            for (child in edges[root]) {
                if (child != parent) {
                    makeTree(edges, child, root, tree)
                    tree[child]?.let { childNode ->
                        /**자녀노드가 생성되었을때 내가 포함되면 자녀는 포함되지 않았을때의 값이 합산되어야 한다.*/
                        node.include += childNode.exclude
                        /**내가 제외되면 자녀는 포함될수도 제외될수도 있다. 때문에 그중 더 큰 값을 합산한다.*/
                        node.exclude += Math.max(childNode.exclude, childNode.include)
                    }
                }
            }
            return tree
        }

        val tree = makeTree(edges, 1)

        val max = tree[1]?.let { Math.max(it.include, it.exclude) } ?: 0
        println(max)

        close()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek1949().main()
        }
    }
}
// 걸린 시간(분): 25