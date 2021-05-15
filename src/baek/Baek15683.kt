package baek

import java.io.BufferedReader
import java.io.InputStreamReader

//감시
class Baek15683 {

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val board = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }
        val emptyBoard = Array(n) { IntArray(m) }
        close()
        var answer = 0
        val cctvs = arrayListOf<Triple<Int, Int, Int>>() // (i,j,타입)
        board.forEachIndexed { i, ints ->
            ints.forEachIndexed { j, int ->
                if (int != 6) answer++
                else emptyBoard[i][j] = 6

                if (int in 1..5) {
                    cctvs.add(Triple(i, j, int))
                    emptyBoard[i][j] = 1
                }
            }
        }
        val directions = IntArray(cctvs.size)


        //북 동 남 서
        val dx = intArrayOf(-1, 0, 1, 0)
        val dy = intArrayOf(0, 1, 0, -1)

        fun fill(board: Array<IntArray>, i: Int, j: Int, direction: Int) {
            var ni = i
            var nj = j
            while (true) {
                ni += dx[direction]
                nj += dy[direction]
                if (ni in 0 until n && nj in 0 until m) {
                    if(board[ni][nj] == 6) break
                    board[ni][nj] = 1
                } else break
            }
        }

        fun getSafe(): Int {
            val b = Array(n) { emptyBoard[it].clone() }
            for (i in directions.indices) {
                val cctv = cctvs[i]
                val dir = directions[i]

                when (cctv.third) {
                    1 -> {
                        fill(b, cctv.first, cctv.second, dir)
                    }
                    2 -> {
                        fill(b, cctv.first, cctv.second, dir)
                        fill(b, cctv.first, cctv.second, (dir + 2) % 4)
                    }
                    3 -> {
                        fill(b, cctv.first, cctv.second, dir)
                        fill(b, cctv.first, cctv.second, (dir + 1) % 4)
                    }
                    4 -> {
                        fill(b, cctv.first, cctv.second, dir)
                        fill(b, cctv.first, cctv.second, (dir + 1) % 4)
                        fill(b, cctv.first, cctv.second, (dir + 2) % 4)
                    }
                    5 -> {
                        fill(b, cctv.first, cctv.second, dir)
                        fill(b, cctv.first, cctv.second, (dir + 1) % 4)
                        fill(b, cctv.first, cctv.second, (dir + 2) % 4)
                        fill(b, cctv.first, cctv.second, (dir + 3) % 4)
                    }
                }

            }
            return b.sumOf { it.count { it == 0 } }
        }

        fun dfs(n: Int) {
            if (n == cctvs.size) {
                answer = Math.min(answer, getSafe())
                return
            }
            fun f(i: Int) {
                directions[n] = i
                dfs(n + 1)
            }
            when (board[cctvs[n].first][cctvs[n].second]) {
                1 -> for (i in 0 until 4) f(i)
                2 -> for (i in 0 until 2) f(i)
                3 -> for (i in 0 until 4) f(i)
                4 -> for (i in 0 until 4) f(i)
                5 -> f(0)
            }
        }
        dfs(0)

        println(answer)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek15683().main()
        }
    }
}
// 걸린 시간(분): 61
