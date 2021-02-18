package baek

import kotlin.math.max
import kotlin.math.min

//01타일 (동적프로그래밍)
class Baek1904 {

    fun main() {
        val n = readLine()!!.toInt()
        val arr = LongArray(1000001).apply {
            this[1] = 1
            this[2] = 2
        }
        for (i in 3..n) {
            arr[i] = (arr[i-1] + arr[i-2]) % 15746
        }
        println(arr[n] % 15746)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek1904().main()
        }
    }
}
// 걸린 시간(분):24