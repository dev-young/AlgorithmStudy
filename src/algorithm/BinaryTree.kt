package algorithm

import baek.Baek5639
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class BinaryTree {

    inner class Node<T>(val v: T) {
        var left: Node<T>? = null
        var right: Node<T>? = null
    }

    /**전위 순회*/
    fun <T> preorderTraversal(node: Node<T>) {
        print(node.v)
        node.left?.let { left -> preorderTraversal(left) }
        node.right?.let { right -> preorderTraversal(right) }
    }

    /**중위 순회*/
    fun <T> inorderTraversal(node: Node<T>) {
        node.left?.let { left -> inorderTraversal(left) }
        print(node.v)
        node.right?.let { right -> inorderTraversal(right) }
    }

    /**후위 순회*/
    fun <T> postorderTraversal(node: Node<T>) {
        node.left?.let { left -> postorderTraversal(left) }
        node.right?.let { right -> postorderTraversal(right) }
        print(node.v)
    }


    /*
    입력:
6
4 2 5 1 6 3
4 5 2 6 3 1

    결과:
    1 2 4 5 3 6
    */
    fun makePreorderTest() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()

        var st = StringTokenizer(readLine())
        inorder = IntArray(n) { st.nextToken().toInt() }
        st = StringTokenizer(readLine())
        postorder = IntArray(n) { st.nextToken().toInt() }

        positionInorder = IntArray(n + 1)
        for (i in inorder.indices) {
            positionInorder[inorder[i]] = i
        }

        makePreorder()
        println("인오더와 포스트오더를 통해 프리오더 출력: ")
        println(result.joinToString(" "))
        println("인오더와 포스트오더를 통해 트리 구성후 프리오더 출력: ")
        makeTreeWithInAndPost()?.let { preorderTraversal(it) }

    }


    /**인오더와 포스트오더가 주어졌을때 프리 오더를 구하는 함수 (분할 정복 기법을 사용하여 계산한다.)
     * http://melonicedlatte.com/algorithm/2018/02/04/145104.html 참고*/
    lateinit var inorder: IntArray
    lateinit var postorder: IntArray
    lateinit var positionInorder: IntArray
    val result: ArrayList<Int> = arrayListOf()
    fun makePreorder(inStart: Int = 0, inEnd: Int = postorder.size - 1, postStart: Int = 0, postEnd: Int = postorder.size - 1) {
        if (postStart > postEnd) return

        val root = postorder[postEnd]
        result.add(root)

        val rootIndexInorder = positionInorder[root]
        val leftLength = rootIndexInorder - inStart
        makePreorder(inStart, rootIndexInorder - 1, postStart, postStart + leftLength - 1)
        makePreorder(rootIndexInorder + 1, inEnd, postStart + leftLength, postEnd - 1)
        return
    }

    /**인오더와 포스트오더가 주어졌을때 트리를 구성하는 함수 */
    fun makeTreeWithInAndPost(inStart: Int = 0, inEnd: Int = postorder.size - 1, postStart: Int = 0, postEnd: Int = postorder.size - 1): Node<Int>? {
        if (postStart > postEnd) return null

        val root = Node(postorder[postEnd])

        val rootIndexInorder = positionInorder[root.v]
        val leftLength = rootIndexInorder - inStart
        makeTreeWithInAndPost(inStart, rootIndexInorder - 1, postStart, postStart + leftLength - 1)?.let { root.left = it }
        makeTreeWithInAndPost(rootIndexInorder + 1, inEnd, postStart + leftLength, postEnd - 1).let { root.right = it }
        return root
    }

    /**이진탐색트리의 프리오더가 주어졌을 때 트리를 구성하는 함수
     * @return 구성된 트리의 Root노드 반환 */
    fun makeBSTreeFromPreorder(preorder:List<Int>, startIdx: Int = 0, endIdx: Int = preorder.size-1): Baek5639.Node<Int>? {
        if (startIdx > endIdx) return null
        val node = Baek5639.Node(preorder[startIdx])

        var leftEndIdx = startIdx
        for (i in startIdx + 1..endIdx) {
            if (preorder[i] < node.v) {
                leftEndIdx = i
            } else {
                makeBSTreeFromPreorder(preorder, i, endIdx)?.let { node.right = it }
                break
            }
        }
        makeBSTreeFromPreorder(preorder, startIdx + 1, leftEndIdx)?.let { node.left = it }
        return node
    }

    //테스트
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val test = BinaryTree()
//            test.bfsTest()
            test.makePreorderTest()
        }
    }

}