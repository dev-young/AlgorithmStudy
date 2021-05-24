package baek

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.collections.ArrayDeque

//아기 상어
class Baek16236 {

    class Shark(var r: Int = 0, var c: Int = 0) {
        var size = 2
        var eat = 0
        fun eat() {
            eat++
            if (size == eat) {
                size++
                eat = 0
            }
        }
    }

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        val board = Array(n) { readLine().split(" ").map(String::toInt).toIntArray() }
        close()

        val dx = intArrayOf(-1, 0, 0, 1)
        val dy = intArrayOf(0, -1, 1, 0)

        var fishCount = 0
        val shark = Shark()
        for (i in board.indices) {
            for (j in board.indices)
                if (board[i][j] == 9) {
                    board[i][j] = 0
                    shark.r = i
                    shark.c = j
                } else if (board[i][j] != 0) {
                    fishCount++
                }
        }

        fun moveNext(): Int? {
            val que = ArrayDeque<Pair<Int, Int>>()
            val visited = hashSetOf<Pair<Int, Int>>()
            Pair(shark.r, shark.c).let {
                que.add(it)
                visited.add(it)
            }
            val targets = arrayListOf<Pair<Int, Int>>()
            var time = 0
            out@ while (que.isNotEmpty()) {
                for (x in 0 until que.size) {
                    val p = que.removeFirst()
                    val (r, c) = p
                    if (board[r][c] in 1 until shark.size) {
                        targets.add(p)
                        continue
                    }

                    for (i in 0 until 4) {
                        val nr = r + dx[i]
                        val nc = c + dy[i]
                        if (nr in board.indices && nc in board.indices) {
                            val next = Pair(nr, nc)
                            if (visited.contains(next)) continue
                            if (board[nr][nc] > shark.size) continue
                            visited.add(next)
                            que.addLast(next)
                        }
                    }
                }
                if (targets.isNotEmpty()) {
                    targets.sortWith(compareBy({ it.first }, { it.second }))
                    val (r, c) = targets.first()
                    shark.eat()
                    board[r][c] = 0
                    shark.r = r
                    shark.c = c
                    fishCount--
                    return time
                }
                time++
            }
            return null
        }

        var answer = 0
        while (fishCount > 0) {
            answer += moveNext() ?: break
        }

        println(answer)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek16236().main()
        }
    }
}
// 걸린 시간(분): 60
