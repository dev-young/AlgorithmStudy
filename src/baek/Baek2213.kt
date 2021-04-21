package baek

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

//트리의 독립집합 (트리에서의 동적 계획법 (Tree DP))
class Baek2213 {

    class Node(val v: Int, val weight: Int) {
        val child = arrayListOf<Int>()
        var include = weight    //포함했을때의 최대값
        var exclude = 0 //제외했을때의 최대값
    }

    /**최대 독립집합의 구성과 수치를 구하는 문제*/
    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        val w = arrayListOf<Int>()
        StringTokenizer(readLine()).let {
            while (it.hasMoreTokens())
                w.add(it.nextToken().toInt())
        }
        val edges = Array(n + 1) { hashSetOf<Int>() }
        repeat(n - 1) {
            StringTokenizer(readLine()).let {
                val v1 = it.nextToken().toInt()
                val v2 = it.nextToken().toInt()
                edges[v1].add(v2)
                edges[v2].add(v1)
            }
        }

        /**dfs를 통해 트리를 만들면서 자신이 include or exclude 하느냐에 따라 최대값을 계산하여 저장한다. */
        fun makeTree(edges: Array<HashSet<Int>>, root: Int, parent: Int = -1, tree: HashMap<Int, Node> = hashMapOf()): HashMap<Int, Node> {
            val node = Node(root, w[root - 1])
            tree[root] = node
            for (child in edges[root]) {
                if (child != parent) {
                    node.child.add(child)
                    makeTree(edges, child, root, tree)
                    tree[child]?.let {
                        /**자녀노드가 생성되었을때 내가 포함되면 자녀는 포함되지 않았을때의 값이 합산되어야 한다.*/
                        node.include += it.exclude
                        /**내가 제외되면 자녀는 포함될수도 제외될수도 있다. 때문에 그중 더 큰 값을 합산한다.*/
                        node.exclude += Math.max(it.exclude, it.include)
                    }
                }
            }
            return tree
        }

        val tree = makeTree(edges, 4)

        /**최대독립집합의 구성을 찾는다.*/
        val result = arrayListOf<Int>()
        fun findMaxSet(node: Int, isParentInclude: Boolean) {
            tree[node]?.let {
                var isIncluded = false
                if (!isParentInclude && it.include > it.exclude) {
                    result.add(node)
                    isIncluded = true
                }
                it.child.forEach {
                    findMaxSet(it, isIncluded)
                }
            }
        }

        val max = tree[4]?.let { Math.max(it.include, it.exclude) } ?: 0
        findMaxSet(4, false)
        println(max)
        println(result.sorted().joinToString(" "))

        close()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek2213().main()
        }
    }
}
// 걸린 시간(분): 58