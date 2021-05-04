package kakao.intern2020

import java.util.*

//https://programmers.co.kr/learn/courses/30/lessons/67258?language=kotlin
class Kakao2020_4 {

    fun solution(board: Array<IntArray>): Int {
        val distance = Array(board.size) { IntArray(board.size) { Int.MAX_VALUE } }

        val direction = arrayOf(intArrayOf(0, 1), intArrayOf(0, -1), intArrayOf(1, 0), intArrayOf(-1, 0))

        val que: Queue<Pair<Triple<Int, Int, Int>, Pair<Int,Int>>> = LinkedList()
        que.offer(Pair(Triple(0, 0, 0), Pair(0,0)))
        while (que.isNotEmpty()) {
            val temp = que.poll()
            val row = temp.first.first
            val col = temp.first.second
            val dis = temp.first.third
            val prev = temp.second

            for (d in direction) {
                val nr = row + d[0]
                val nc = col + d[1]
                if(nr in board.indices && nc in board.indices && board[nr][nc] == 0) {
                    var nd = dis+100
                    if(prev.first != nr && prev.second != nc)
                        nd += 500
                    if(nd <= distance[nr][nc]) {
                        distance[nr][nc] = nd
                        que.offer(Pair(Triple(nr, nc, nd), Pair(row, col)))
                    }
                }
            }
        }

        return distance[board.size-1][board.size-1]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Kakao2020_4()
            val p = arrayOf(
                    intArrayOf(0, 0, 1, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 1, 0, 1),
                    intArrayOf(1, 0, 0, 0))

//            val p = arrayOf(
//                    intArrayOf(0, 0, 0, 0, 0, 0),
//                    intArrayOf(0, 1, 1, 1, 1, 0),
//                    intArrayOf(0, 0, 1, 0, 0, 0),
//                    intArrayOf(1, 0, 0, 1, 0, 1),
//                    intArrayOf(0, 1, 0, 0, 0, 1),
//                    intArrayOf(0, 0, 0, 0, 0, 0))


            var r = s.solution(p)
            println(r)
        }
    }
}
// 걸린 시간: 49