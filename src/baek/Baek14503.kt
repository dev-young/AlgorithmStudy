package baek

import java.io.BufferedReader
import java.io.InputStreamReader

//로봇 청소기
class Baek14503 {

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val temp = readLine().split(" ")
        val n = temp[0].toInt()
        val m = temp[1].toInt()
        val robot = readLine().split(" ").map { it.toInt() }.toIntArray()
        val board = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }
        close()

        var answer = 1
        //북 동 남 서
        val dx = intArrayOf(-1, 0, 1, 0)
        val dy = intArrayOf(0, 1, 0, -1)

        fun moveNext(): Boolean {
            board[robot[0]][robot[1]] = 2
            var direction = robot[2]
            for (i in 1..4) {
                direction = (direction + 3) % 4
                val nx = robot[0] + dx[direction]
                val ny = robot[1] + dy[direction]
                if (board[nx][ny] == 0) {
                    robot[0] = nx
                    robot[1] = ny
                    robot[2] = direction
                    answer++
                    return true
                }
            }
            val back = (robot[2] + 2) % 4
            var nx = robot[0] + dx[back]
            var ny = robot[1] + dy[back]
            if (board[nx][ny] == 1)
                return false
            robot[0] = nx
            robot[1] = ny
            return moveNext()
        }

        while (moveNext()) {
        }
        println(answer)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek14503().main()
        }
    }
}
// 걸린 시간(분): 40