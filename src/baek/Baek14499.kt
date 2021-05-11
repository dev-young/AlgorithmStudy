package baek

import java.io.BufferedReader
import java.io.InputStreamReader

//주사위 굴리기
class Baek14499 {

    class Dice() {
        var top = 0
        var bottom = 0
        var left = 0
        var right = 0
        var up = 0
        var down = 0
        fun move(direction: Int) {
            when (direction) {
                1 -> {  //동쪽으로 굴림
                    val temp = left
                    left = bottom
                    bottom = right
                    right = top
                    top = temp
                }
                2 -> {  //서쪽으로 굴림
                    val temp = left
                    left = top
                    top = right
                    right = bottom
                    bottom = temp
                }
                3 -> {  //북쪽으로 굴림
                    val temp = bottom
                    bottom = up
                    up = top
                    top = down
                    down = temp
                }
                4 -> {  //남쪽으로 굴림
                    val temp = bottom
                    bottom = down
                    down = top
                    top = up
                    up = temp
                }
            }
        }
    }

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val temp = readLine().split(" ").map { it.toInt() }
        val N = temp[0]
        val M = temp[1]
        var x = temp[2]
        var y = temp[3]
        val board = Array(N) { readLine().split(" ").map { it.toInt() }.toIntArray() }
        val moves = readLine().split(" ").map { it.toInt() }
        close()
        val dice = Dice()
        //idx : 1234 = 동 서 북 남
        val dx = intArrayOf(0, 0, 0, -1, 1)
        val dy = intArrayOf(0, 1, -1, 0, 0)

        for (direction in moves) {
            var nx = dx[direction] + x
            var ny = dy[direction] + y
            if (nx in 0 until N && ny in 0 until M) {
                x = nx
                y = ny

                dice.move(direction)
                if (board[x][y] == 0) {
                    board[x][y] = dice.bottom
                } else {
                    dice.bottom = board[x][y]
                    board[x][y] = 0
                }

                println(dice.top)
            }
        }

    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek14499().main()
        }
    }
}
// 걸린 시간(분): 48