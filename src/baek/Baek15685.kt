package baek

import java.io.BufferedReader
import java.io.InputStreamReader

//드래곤 커브
class Baek15685 {
    class Dragon(val start: Pair<Int, Int>, val d: Int, val g: Int)

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        val dragons = Array(n) { readLine().split(" ").map { it.toInt() }.let { Dragon(Pair(it[0], it[1]), it[2], it[3]) } }
        close()
        val board = Array(101) { BooleanArray(101) }

        fun makeDragon(dragon: Dragon) {
            var g = 0
            val points = arrayListOf<Pair<Int, Int>>()
            points.add(dragon.start)
            when (dragon.d) {
                0 -> points.add(Pair(dragon.start.first + 1, dragon.start.second))
                1 -> points.add(Pair(dragon.start.first, dragon.start.second - 1))
                2 -> points.add(Pair(dragon.start.first - 1, dragon.start.second))
                3 -> points.add(Pair(dragon.start.first, dragon.start.second + 1))
            }
            for (r in points) {
                if (r.first in board.indices && r.second in board.indices)
                    board[r.second][r.first] = true
            }

            while (g < dragon.g) {
                val (x, y) = points[points.size-1]
                val rotated = arrayListOf<Pair<Int, Int>>()
                for (i in points.size - 2 downTo 0) {
                    val (a, b) = points[i]
                    val r = Pair(y - b + x, a - x + y)  //pivot (x,y) 를 기준으로 점(a,b)를 시계방향으로 90도 회전시킬때 공식
                    rotated.add(r)
                    if (r.first in board.indices && r.second in board.indices)
                        board[r.second][r.first] = true
                }
                points.addAll(rotated)
                g++
            }
        }

        dragons.forEach { makeDragon(it) }

        var answer = 0
        for (i in 0 until board.size-1) {
            for (j in 0 until board.size-1) {
                if (board[i][j] && board[i + 1][j] && board[i][j + 1] && board[i + 1][j + 1])
                    answer++
            }
        }
        println(answer)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek15685().main()
        }
    }
}
// 걸린 시간(분): 48 (방향을 나열하여 규칙을 찾아서 푸는 방법도 있다.)
