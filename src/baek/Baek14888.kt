package baek

import kotlin.math.max
import kotlin.math.min

//연산자 끼워넣기 (백트래킹)
class Baek14888 {

    fun main() {
        val n = readLine()!!.toInt()
        val numbers = readLine()!!.split(" ").map { it.toInt() }
        val operator = readLine()!!.split(" ").let {
            arrayListOf<Int>().apply {
                it.forEachIndexed { index, s ->
                    for (i in 0 until s.toInt())
                        add(index)
                }
            }
        }
        var max = -100000000
        var min = 100000000

        fun cal(operators: IntArray): Int {
            var sum = numbers[0]
            operators.forEachIndexed { index, op ->
                when (op) {
                    0 -> sum += numbers[index + 1]
                    1 -> sum -= numbers[index + 1]
                    2 -> sum *= numbers[index + 1]
                    3 -> sum /= numbers[index + 1]
                }
            }
            return sum
        }

        fun permutation(
            arr: IntArray,
            temp: IntArray = IntArray(arr.size),
            current: Int = 0,
            visited: BooleanArray = BooleanArray(arr.size),
        ) {
            if (arr.size == current) {
                cal(temp).let {
                    max = max(it, max)
                    min = min(it, min)
                }
            } else {
                for (i in arr.indices) {
                    if (!visited[i]) {
                        visited[i] = true
                        temp[current] = arr[i]
                        permutation(arr, temp, current + 1, visited)
                        visited[i] = false
                    }
                }
            }
        }

        permutation(operator.toIntArray())
        println(max)
        println(min)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek14888().main()
        }
    }
}
// 걸린 시간(분):36