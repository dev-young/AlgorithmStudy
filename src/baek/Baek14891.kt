package baek

import java.io.BufferedReader
import java.io.InputStreamReader

//톱니바퀴
class Baek14891 {

    class Wheel(val w: IntArray) {
        var left: Wheel? = null
        var right: Wheel? = null
        var state = 0   //12시 방향 인덱스
        fun top() = w[state]
        fun left() = w[(state + 6) % 8]
        fun right() = w[(state + 2) % 8]
        fun rotate(direction: Int, from: Int = 0) {
            if (from >= 0) {
                left?.let {
                    if (it.right() != left()) it.rotate(-1 * direction, 1)
                }
            }
            if (from <= 0) {
                right?.let {
                    if (it.left() != right()) it.rotate(-1 * direction, -1)
                }
            }

            state += if (direction > 0) 7 else 1
            state %= 8
        }

    }

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val arr = Array(4) { Wheel(readLine().map { if (it == '1') 1 else 0 }.toIntArray()) }
        val n = readLine().toInt()
        val cycles = Array(n) { readLine().split(" ").let { Pair(it[0].toInt(), it[1].toInt()) } }
        close()
        arr.forEachIndexed { index, wheel ->
            if (index - 1 >= 0) wheel.left = arr[index - 1]
            if (index + 1 < arr.size) wheel.right = arr[index + 1]
        }

        cycles.forEach {
            arr[it.first - 1].rotate(it.second)
        }

        var answer = 0
        var temp = 1
        arr.map { it.top() }.forEach {
            if (it == 1) answer += temp
            temp *= 2
        }
        println(answer)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek14891().main()
        }
    }
}
// 걸린 시간(분): 64
