package kakao.blind2022

import java.math.BigInteger
import kotlin.math.sqrt

class Solution2 {
    fun solution(n: Int, k: Int): Int {
        val str = n.toString(k)
        val list = str.split("0")
                .filter { it.isNotBlank() && it != "1" }
                .map { it.replace(" ", "") }
                .map { it.toLong() }

        println(list.size)
        return list.filter { isPrime(it) }.size
    }

    private val primeSet = hashSetOf<Long>()
    fun isPrime(n: Long): Boolean {
        if (primeSet.contains(n)) return true
        var i = 2
        val sqrt = sqrt(n.toDouble()).toLong()
        while (i <= sqrt) {
            if (n % i == 0L) return false
            i++
        }
        primeSet.add(n)
        return true
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Solution2()
//            val str = "0010011000"
//            val list = str.split("0").filter { it.isNotBlank() }.map { it.replace(" ", "") }
//            val r = s.solution("10000000000000000".toInt(3), 3)
//            println(r)


        }
    }
}
// 걸린 시간(분): 73