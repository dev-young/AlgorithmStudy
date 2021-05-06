package kakao.blind2020

import java.util.*

//https://programmers.co.kr/learn/courses/30/lessons/60063?language=kotlin
class Blind_7 {
    data class Robot(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {
        companion object {
            val moves = arrayOf(intArrayOf(0, 1), intArrayOf(0, -1), intArrayOf(1, 0), intArrayOf(-1, 0))//우 좌 하 상
        }

        fun getNext(board: Array<IntArray>): List<Robot> {
            val range = board.indices
            fun check(nr: Int, nc: Int): Boolean {
                return nr in range && nc in range && board[nr][nc] == 0
            }

            val res = arrayListOf<Robot>()

            moves.forEachIndexed { i, move ->
                val nr1 = x1 + move[0]
                val nc1 = y1 + move[1]
                val nr2 = x2 + move[0]
                val nc2 = y2 + move[1]

                if (check(nr1, nc1) && check(nr2, nc2)) {
                    res.add(Robot(nr1, nc1, nr2, nc2))

                    if (x1 == x2) {  //가로상태
                        if (i == 2) {
                            res.add(Robot(x1, y1, nr1, nc1))
                            res.add(Robot(x2, y2, nr2, nc2))
                        } else if (i == 3) {
                            res.add(Robot(nr1, nc1, x1, y1))
                            res.add(Robot(nr2, nc2, x2, y2))
                        }
                    } else { //세로상태
                        if (i == 0) {
                            res.add(Robot(x1, y1, nr1, nc1))
                            res.add(Robot(x2, y2, nr2, nc2))
                        } else if (i == 1) {
                            res.add(Robot(nr1, nc1, x1, y1))
                            res.add(Robot(nr2, nc2, x2, y2))
                        }
                    }
                }
            }
            return res
        }
    }

    fun solution(board: Array<IntArray>): Int {
        val times = Array(board.size) { IntArray(board.size) { Int.MAX_VALUE } }
        times[0][0] = 0
        times[0][1] = 0
        val visited = hashSetOf<Robot>()
        val que: Queue<Pair<Robot, Int>> = LinkedList()
        que.offer(Pair(Robot(0, 0, 0, 1), 0))
        visited.add(Robot(0, 0, 0, 1))
        while (que.isNotEmpty()) {
            val p = que.poll()
            val nd = p.second + 1
            for (r in p.first.getNext(board)) {
                if (nd <= times[r.x1][r.y1]) {
                    times[r.x1][r.y1] = nd
                }
                if (nd <= times[r.x2][r.y2]) {
                    times[r.x2][r.y2] = nd
                }

                if (!visited.contains(r)) {
                    visited.add(r)
                    que.offer(Pair(r, nd))
                }
            }
        }

        return times[board.size-1][board.size-1]
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Blind_7()
//            println(s.solution(arrayOf(
//                    intArrayOf(0, 0, 0, 1, 1),
//                    intArrayOf(0, 0, 0, 1, 0),
//                    intArrayOf(0, 1, 0, 1, 1),
//                    intArrayOf(1, 1, 0, 0, 1),
//                    intArrayOf(0, 0, 0, 0, 0)
//            )))
            println(s.solution(arrayOf(
                    intArrayOf(0, 0, 0, 0, 0),
                    intArrayOf(1, 0, 1, 0, 0),
                    intArrayOf(0, 1, 1, 0, 0),
                    intArrayOf(0, 0, 1, 0, 0),
                    intArrayOf(0, 0, 1, 0, 0)
            )))
        }
    }
}
// 걸린 시간(분): 86