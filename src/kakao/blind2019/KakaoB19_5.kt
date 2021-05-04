package kakao.blind2019


//https://programmers.co.kr/learn/courses/30/lessons/42892?language=kotlin
class KakaoB19_5 {

    data class Node(val v: Int, val x: Int, val y: Int) {
        var left: Node? = null
        var right: Node? = null
    }

    fun solution(nodeinfo: Array<IntArray>): Array<IntArray> {
        val pre = arrayListOf<Int>()
        val post = arrayListOf<Int>()
        val nodeList = nodeinfo.mapIndexed { index, ints -> Node(index + 1, ints[0], ints[1]) }
                .sortedWith(compareBy({ -it.y }, { it.x }))

        fun makeTree(list:List<Node>) {
            val root = list[0]

            val lefts = arrayListOf<Node>()
            val rights = arrayListOf<Node>()

            list.forEach {
                if (it.x < root.x) {
                    lefts.add(it)
                } else if (it.x > root.x){
                    rights.add(it)
                }
            }
            if (lefts.isNotEmpty()) {
                root.left = lefts[0]
                makeTree(lefts)
            }

            if (rights.isNotEmpty()) {
                root.right = rights[0]
                makeTree(rights)
            }
        }
        makeTree(nodeList)

        fun traversal(node: Node) {
            pre.add(node.v)
            node.left?.let { left -> traversal(left) }
            node.right?.let { right -> traversal(right) }
            post.add(node.v)
        }

        traversal(nodeList[0])

        return arrayOf(pre.toIntArray(), post.toIntArray())
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = KakaoB19_5()
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
// 걸린 시간: 37분