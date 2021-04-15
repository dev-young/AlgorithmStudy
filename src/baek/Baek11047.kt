package baek

import kotlin.math.abs
import kotlin.math.min

//동전 0
class Baek11047 {


    fun main() {
        var answer = 0
        val t = readLine()!!.split(" ")
        val n = t[0].toInt()
        var k = t[1].toInt()

        val arr = IntArray(n) {0}
        for (i in 1..n) arr[n-i] = readLine()!!.toInt()

        for (i in arr) {
            answer += k/i
            k %= i
            if (k == 0) break
        }
        println(answer)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val r = Baek11047().main()
        }
    }
}
// 걸린 시간(분):