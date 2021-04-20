package baek

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

//트리의 순회 (트리)
class Baek2263 {

    /**후위 순회의 결과에서 마지막 노드가 루트 노드이다
     * 중위 훈회의 결과에서 루트노드가 나오기 전까지는 왼쪽부분이고 루트 이후에는 오른쪽 부분이다.*/
    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        val ino = readLine().split(" ").map { it.toInt() }
        val posto = readLine().split(" ").map { it.toInt() }

        fun makePreorder(rootIdx: Int, startIdx: Int, size: Int): Node? {
            if(size == 0) return null
            val root = Node(posto[rootIdx])

            var leftSize = 0
            var rightSize = 0
            var isRootFind = false
            var centerIdx = 0
            for (i in startIdx until startIdx + size) {
                if (root.v == ino[i]) {
                    isRootFind = true
                    centerIdx = i
                } else {
                    if (isRootFind) {
                        rightSize++
                    } else {
                        leftSize++
                    }
                }
            }
            val startIdxL = centerIdx - leftSize
            val startIdxR = centerIdx + 1

            val rootIdxL = rootIdx - size + leftSize
            val rootIdxR = rootIdx - 1

            root.left = makePreorder(rootIdxL, startIdxL, leftSize)
            root.right = makePreorder(rootIdxR, startIdxR, rightSize)

            return root
        }

        val root = makePreorder(posto.size - 1, 0, posto.size)

        val result = arrayListOf<Int>()
        fun preorderTraversal(node: Node) {
            result.add(node.v)
            node.left?.let { left -> preorderTraversal(left) }
            node.right?.let { right -> preorderTraversal(right) }
        }

        preorderTraversal(root!!)

        println(result.joinToString(" "))

        close()
    }

    class Node(val v: Int) {
        var left: Node? = null
        var right: Node? = null
    }

    val sb = StringBuilder()

    lateinit var inorder:IntArray
    lateinit var postorder:IntArray
    lateinit var positionInorder:IntArray


    /**훨씬 빠른 방법 (다른사람 코드)*/
    fun main2(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()

        var st = StringTokenizer(readLine())
        inorder = IntArray(n) { st.nextToken().toInt() }
        st = StringTokenizer(readLine())
        postorder = IntArray(n) { st.nextToken().toInt() }

        positionInorder = IntArray(n + 1)
        for(i in inorder.indices) {
            positionInorder[inorder[i]] = i
        }

        makePriorder(0, n-1, 0, n-1)
        print(sb.toString())
    }

    fun makePriorder(inStart: Int, inEnd: Int, postStart: Int, postEnd: Int) {
        if(postStart > postEnd) return

        val root = postorder[postEnd]
        sb.append("$root ")

        val rootIndexInorder = positionInorder[root]
        val leftLength = rootIndexInorder - inStart
        makePriorder(inStart, rootIndexInorder - 1, postStart, postStart + leftLength - 1)
        makePriorder(rootIndexInorder + 1, inEnd, postStart + leftLength, postEnd - 1)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek2263().main()
        }
    }
}
// 걸린 시간(분): 119