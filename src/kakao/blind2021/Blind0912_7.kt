package kakao.blind2021

import java.util.HashMap
import java.util.HashSet

class Blind0912_7 {

    fun solution(sales: IntArray, links: Array<IntArray>): Int {

        val edges = Array(sales.size + 1) { hashSetOf<Int>() }
        links.forEach {
            edges[it[0]].add(it[1])
            edges[it[1]].add(it[0])
        }

        fun makeTree(edges: Array<HashSet<Int>>, root: Int, parent: Int = -1, tree: HashMap<Int, Node> = hashMapOf()): HashMap<Int, Node> {
            val node = Node(root, sales[root - 1])
            tree[root] = node
            var isChildIncluded = false
            var minDiff = Int.MAX_VALUE
            var isChildExist =false
            for (child in edges[root]) {
                if (child != parent) {
                    makeTree(edges, child, root, tree)
                    tree[child]?.let { childNode ->
                        isChildExist =true
                        /**자녀노드가 생성되었을때 내가 포함되면 자녀는 포함될수도 안될수도 있다. 따라서 더 작은값을 합산한다.*/
                        node.include += Math.min(childNode.exclude, childNode.include)

                        /**내가 제외되면 자녀중 한명 이상은 무조건 포함되어야 한다. 자녀의 min(exclude, include) 값을 합산하고 만약 합산된 자녀에 include 값이 없으면
                         * 자녀들 중 include와 exclude 의 차가 가장 작은 자녀를 포함시킨다. (== 해당 자녀의 차를 합산한다.) */
                        if (childNode.exclude < childNode.include) {
                            node.exclude += childNode.exclude
                            minDiff = Math.min(minDiff, childNode.include - childNode.exclude)
                        } else {
                            node.exclude += childNode.include
                            isChildIncluded = true
                        }
                    }
                }
            }
            if (isChildExist && !isChildIncluded)
                node.exclude += minDiff

            return tree
        }

        val tree = makeTree(edges, 1)

        val min = tree[1]?.let { Math.min(it.include, it.exclude) } ?: 0
        return min
    }

    class Node(val v: Int, weight: Int) {
        var include = weight    //포함했을때의 값
        var exclude = 0 //제외했을때의 값
    }

    /**내가 푼건 시간과 공간복잡도가 너무 크다... (예전에 풀고 실패한것...) */
    fun solution2(sales: IntArray, links: Array<IntArray>): Int {
        val parentSet = hashSetOf<Int>()
        val pMap = hashMapOf<Int, Int>()
        links.forEach {
            parentSet.add(it[0])
            pMap[it[1]] = it[0]
        }

        var minSum = parentSet.sumBy { sales[it - 1] }.toLong()

        val visited = hashSetOf<Int>()

        fun powerSet(n: Int, idx: Int, sum: Long = 0) {
            if (sum >= minSum) return
            if (parentSet.size < visited.size)
                return

            if (idx == n) {
                val temp = mutableListOf<Int>()
                visited.forEach {
                    temp.add(it + 1)
                }
                print(temp)
                println(" $sum")
                val tSet = hashSetOf<Int>()
                for (i in temp) {
                    pMap[i]?.let { tSet.add(it) }
                    if (parentSet.contains(i)) tSet.add(i)
                    if (tSet.size == parentSet.size) {
                        minSum = sum
                        break
                    }
                }
                return
            }
            visited.add(idx)
            powerSet(n, idx + 1, sum + sales[idx])
            visited.remove(idx)
            powerSet(n, idx + 1, sum)
        }

        powerSet(sales.size, 0)

        return minSum.toInt()
    }
}

fun main() {
    val s = Blind0912_7()
    val r = s.solution(intArrayOf(5, 6, 5, 3, 4),
            arrayOf(intArrayOf(2, 3), intArrayOf(1, 4), intArrayOf(2, 5), intArrayOf(1, 2))
    )
//    val r = s.solution(intArrayOf(14, 17, 15, 18, 19, 14, 13, 16, 28, 17),
//            arrayOf(intArrayOf(10, 8), intArrayOf(1, 9), intArrayOf(9, 7), intArrayOf(5, 4), intArrayOf(1, 5), intArrayOf(5, 10), intArrayOf(10, 6), intArrayOf(1, 3), intArrayOf(10, 2))
//    )
    println(r)
}
// 걸린 시간(분): 49