package kakao.blind2019

import kotlin.Comparator

//https://programmers.co.kr/learn/courses/30/lessons/42892?language=kotlin
class KakaoB19_4 {

    data class Node(
        val v: Int,
        val xy: IntArray,
        var parent: Node? = null,
        var left: Node? = null,
        var right: Node? = null,
        val x: Int = xy[0],
        val y: Int = xy[1]
    ) {
        fun traversal(root: Node = this): MutableList<Int> {
            val result = mutableListOf<Int>()
            tailrec fun re(start: Node? = this) {
                if (start == null) return
                result.add(start.v)
                re(start.left)
                re(start.right)
            }
            re(root)
            return result
        }
        fun traversal2(root: Node = this): MutableList<Int> {
            val result = mutableListOf<Int>()
            fun re(start: Node? = this) {
                if (start == null) return
                re(start.left)
                re(start.right)
                result.add(start.v)
            }
            re(root)
            return result
        }
    }

    fun solution(nodeinfo: Array<IntArray>): Array<IntArray> {
        val nodeList = nodeinfo.mapIndexed { index, ints ->
            Node(index + 1, ints)
        }.sortedWith(Comparator { o1, o2 ->
            o1.xy[1].compareTo(o2.xy[1]).let {
                if (it == 0) o1.xy[0].compareTo(o2.xy[0])
                else -it
            }
        })

        /**정렬된 노드 리스트를 전달받으면 첫번째 노드를 중점으로 왼쪽에 올 노드들과 오른쪽에 올 수 있는 노드들을 나눠서 부모와 첫번째 노드 연결*/
        fun makeTree(nodeList_: List<Node>){
            val nodeList = nodeList_.toMutableList()
            val root = nodeList.removeAt(0)
            val leftList = mutableListOf<Node>()
            val rightList = mutableListOf<Node>()
            nodeList.forEach {
                if (it.x < root.x)
                    leftList.add(it)
                else
                    rightList.add(it)
            }

            if(leftList.isNotEmpty()) {
                root.left = leftList[0]
                makeTree(leftList)
            }
            if(rightList.isNotEmpty()) {
                root.right = rightList[0]
                makeTree(rightList)
            }

        }

        makeTree(nodeList)
        val root = nodeList[0]
        return arrayOf(root.traversal(root).toIntArray(), root.traversal2(root).toIntArray())
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = KakaoB19_4()
            val r = s.solution(arrayOf(intArrayOf(5, 3),
                intArrayOf(11, 5),
                intArrayOf(13, 3),
                intArrayOf(3, 5),
                intArrayOf(6, 1),
                intArrayOf(1, 3),
                intArrayOf(8, 6),
                intArrayOf(7, 2),
                intArrayOf(2, 2)))
            println(r.contentDeepToString())
        }
    }
}
// 걸린 시간: 163분