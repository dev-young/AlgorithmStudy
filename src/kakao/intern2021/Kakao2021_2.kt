package kakao.intern2021

import java.lang.Exception

class Kakao2021_2 {
    private val delta = arrayOf(intArrayOf(0, 1), intArrayOf(0, -1), intArrayOf(-1, 0), intArrayOf(1, 0))   //상하좌우
    private val delta2 = arrayOf(intArrayOf(1, 1), intArrayOf(1, -1), intArrayOf(-1, 1), intArrayOf(-1, -1))    //대각
    private val delta3 = arrayOf(intArrayOf(0, 2), intArrayOf(0, -2), intArrayOf(-2, 0), intArrayOf(2, 0))   //
    fun solution(places: Array<Array<String>>): IntArray {
        var answer = IntArray(places.size) { 1 }
        places.forEachIndexed { index, board ->
            val people = arrayListOf<IntArray>()
            board.forEachIndexed { x, s ->
                s.forEachIndexed { y, c ->
                    if (c == 'P') people.add(intArrayOf(x, y))
                }
            }

            loop@ for (p in people) {
                val (x, y) = Pair(p[0], p[1])
                for (it in delta) {
                    try {
                        if (board[x + it[0]][y + it[1]] == 'P') {
                            answer[index] = 0
                            break@loop
                        }
                    } catch (e: Exception) {
                    }
                }
                for (it in delta2) {
                    try {
                        val (nx, ny) = Pair(x + it[0], y + it[1])
                        if (board[nx][ny] == 'P') {
                            if (board[x][ny] != 'X' || board[nx][y] != 'X') {
                                answer[index] = 0
                                break@loop
                            }
                        }
                    } catch (e: Exception) {
                    }
                }
                for (it in delta3) {
                    try {
                        val (nx, ny) = Pair(x + it[0], y + it[1])
                        if (board[nx][ny] == 'P') {
                            if (board[(x + nx) / 2][(y + ny) / 2] != 'X') {
                                answer[index] = 0
                                break@loop
                            }
                        }
                    } catch (e: Exception) {
                    }
                }

            }
        }
        return answer
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Kakao2021_2()
            println(
                s.solution(
                    arrayOf(
                        arrayOf("POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"),
                        arrayOf("POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"),
                        arrayOf("PXOPX", "OXOXP", "OXPXX", "OXXXP", "POOXX"),
                        arrayOf("OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"),
                        arrayOf("PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP")
                    )
                ).contentToString()
            )
        }
    }
}
// 걸린 시간(분): 48