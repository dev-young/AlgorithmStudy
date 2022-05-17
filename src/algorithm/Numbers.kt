package algorithm

import kotlin.math.sqrt

class Numbers {

    /**약수의 갯수 빠르게 구하는 방식*/
    fun divisorCount(n: Int): Int {
        var cnt = 0
        for (i in 1..n) {
            val ii = i * i
            if (ii > n) break
            if (ii == n) cnt++
            else if(n%i == 0) cnt += 2
        }
        return cnt
    }

    //최대 공약수 (유클리드 알고리즘)
    fun gcd(a_: Int, b_: Int): Int {
        var a = Math.max(a_, b_)
        var b = Math.min(a_, b_)
        while (b != 0) {
            (a % b).let {
                a = b
                b = it
            }
        }
        return a
    }

    //최속 공배수
    fun lcm(a: Int, b: Int) = (a * b) / gcd(a, b)

    //소수판별 ( 참고: https://myjamong.tistory.com/139?category=898047 )
    private val primeSet = hashSetOf<Long>()
    fun isPrime(n: Long): Boolean {
        if (primeSet.contains(n)) return true
        if (n < 0) return false
        for (i in 2..sqrt(n.toDouble()).toInt()) {
            if (n % i == 0L) return false
        }
        primeSet.add(n)
        return true
    }


    //테스트
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val nums = Numbers()
            intArrayOf(123).sortedDescending()
            println(nums.isPrime(997))
            intArrayOf(1,2,3,4).sortedWith(compareBy({it}, {it}, {it*2}))

        }
    }
}