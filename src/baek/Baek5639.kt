package baek

import java.io.BufferedReader
import java.io.InputStreamReader

//이진 검색 트리 (트리)
class Baek5639 {

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val preorder = arrayListOf<Int>()
        while (true) {
            val line = readLine()
            if (line.isNullOrEmpty()) break

            preorder.add(line.toInt())
        }

        fun makeTree(startIdx: Int = 0, endIdx: Int): Node<Int>? {
            if (startIdx > endIdx) return null
            val node = Node(preorder[startIdx])

            var leftEndIdx = startIdx
            for (i in startIdx + 1..endIdx) {
                if (preorder[i] < node.v) {
                    leftEndIdx = i
                } else {
                    makeTree(i, endIdx)?.let { node.right = it }
                    break
                }
            }
            makeTree(startIdx + 1, leftEndIdx)?.let { node.left = it }
            return node
        }

        val sb = StringBuilder()
        fun <T> postorderTraversal(node: Node<T>) {
            node.left?.let { left -> postorderTraversal(left) }
            node.right?.let { right -> postorderTraversal(right) }
            sb.append("${node.v}\n")
        }
        makeTree(0, preorder.size - 1)?.let {
            postorderTraversal(it)
            println(sb.toString())
        }





        close()
    }

    class Node<T>(val v: T) {
        var left: Node<T>? = null
        var right: Node<T>? = null
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek5639().main()
        }
    }
}
// 걸린 시간(분): 48