package baek

import java.io.BufferedReader
import java.io.InputStreamReader

//큐빙
class Baek5373 {
    class Cube() {
        val up = Array(3) { CharArray(3) { 'w' } }
        val down = Array(3) { CharArray(3) { 'y' } }
        val forward = Array(3) { CharArray(3) { 'r' } }
        val back = Array(3) { CharArray(3) { 'o' } }
        val left = Array(3) { CharArray(3) { 'g' } }
        val right = Array(3) { CharArray(3) { 'b' } }

        fun rotate(target: Char, direction: Char) {
            rotate(target)
            if (direction == '-') {
                rotate(target)
                rotate(target)
            }
        }

        private fun rotate(target: Char) {
            when (target) {
                'U' -> {
                    rotate(up)
                    val temp = forward[0]
                    forward[0] = right[0]
                    right[0] = back[0]
                    back[0] = left[0]
                    left[0] = temp
                }
                'D' -> {
                    rotate(down)
                    val temp = forward[2]
                    forward[2] = left[2]
                    left[2] = back[2]
                    back[2] = right[2]
                    right[2] = temp
                }
                'F' -> {
                    rotate(forward)
                    val temp = charArrayOf(up[2][0], up[2][1], up[2][2])
                    up[2][0] = left[2][2]
                    up[2][1] = left[1][2]
                    up[2][2] = left[0][2]
                    left[0][2] = down[0][0]
                    left[1][2] = down[0][1]
                    left[2][2] = down[0][2]
                    down[0][0] = right[2][0]
                    down[0][1] = right[1][0]
                    down[0][2] = right[0][0]
                    right[2][0] = temp[2]
                    right[1][0] = temp[1]
                    right[0][0] = temp[0]
                }
                'B' -> {
                    rotate(back)
                    val temp = charArrayOf(up[0][0], up[0][1], up[0][2])
                    up[0][0] = right[0][2]
                    up[0][1] = right[1][2]
                    up[0][2] = right[2][2]
                    right[0][2] = down[2][2]
                    right[1][2] = down[2][1]
                    right[2][2] = down[2][0]
                    down[2][2] = left[2][0]
                    down[2][1] = left[1][0]
                    down[2][0] = left[0][0]
                    left[2][0] = temp[0]
                    left[1][0] = temp[1]
                    left[0][0] = temp[2]
                }
                'L' -> {
                    rotate(left)
                    val temp = charArrayOf(up[0][0], up[1][0], up[2][0])
                    up[0][0] = back[2][2]
                    up[1][0] = back[1][2]
                    up[2][0] = back[0][2]
                    back[2][2] = down[0][0]
                    back[1][2] = down[1][0]
                    back[0][2] = down[2][0]
                    down[0][0] = forward[0][0]
                    down[1][0] = forward[1][0]
                    down[2][0] = forward[2][0]
                    forward[0][0] = temp[0]
                    forward[1][0] = temp[1]
                    forward[2][0] = temp[2]
                }
                'R' -> {
                    rotate(right)
                    val temp = charArrayOf(up[0][2], up[1][2], up[2][2])
                    up[0][2] = forward[0][2]
                    up[1][2] = forward[1][2]
                    up[2][2] = forward[2][2]
                    forward[0][2] = down[0][2]
                    forward[1][2] = down[1][2]
                    forward[2][2] = down[2][2]
                    down[0][2] = back[2][0]
                    down[1][2] = back[1][0]
                    down[2][2] = back[0][0]
                    back[2][0] = temp[0]
                    back[1][0] = temp[1]
                    back[0][0] = temp[2]
                }
            }
        }

        /**행렬 회전
         * @param direction +: 시계방향, -: 반시계방향*/
        fun rotate(target: Array<CharArray>, direction: Char) {
            val temp = Array(target.size) { target[it].clone() }
            if (direction == '+')
                for (i in target.indices) {
                    for (j in target[0].indices) {
                        target[j][target.size - i - 1] = temp[i][j]
                    }
                }
            else
                for (i in target.indices) {
                    for (j in target[0].indices) {
                        target[target.size - j - 1][i] = temp[i][j]
                    }
                }
        }

        fun rotate(target: Array<CharArray>) {
            val temp = Array(target.size) { target[it].clone() }
            for (i in target.indices) {
                for (j in target[0].indices) {
                    target[j][target.size - i - 1] = temp[i][j]
                }
            }
        }
    }

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        val testCase = Array(n) {
            readLine()
            readLine().split(" ")
        }
        close()

        Cube().apply {
            rotate('U', '+')
            println()
        }
        Cube().apply {
            rotate('D', '+')
            println()
        }
        Cube().apply {
            rotate('F', '+')
            println()
        }
        Cube().apply {
            rotate('B', '+')
            println()
        }
        Cube().apply {
            rotate('L', '+')
            println()
        }
        Cube().apply {
            rotate('R', '+')
            println()
        }

        for (test in testCase) {
            val cube = Cube()
            test.forEach {
                cube.rotate(it[0], it[1])
            }
            cube.up.forEach {
                println(it.concatToString())
            }
        }

    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek5373().main()
        }
    }
}
// 걸린 시간(분): 76 (다시는 이딴 구현문제는 풀지 말자... 그냥 노가다 하는 기분이다..)
