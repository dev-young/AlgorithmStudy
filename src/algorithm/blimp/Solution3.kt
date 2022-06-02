package algorithm.blimp

class Solution3 {
    fun makePrime(n: Int): ArrayList<Int> {
        val check = BooleanArray(n + 1)
        check[1] = true
        for (i in 2..n / 2) {
            var j = i * 2
            if (check[i]) continue
            while (j <= n) {
                check[j] = true
                j += i
            }
        }

        val primes = arrayListOf<Int>()
        check.forEachIndexed { index, b ->
            if (!b) primes.add(index)
        }
        primes.removeFirst()
        return primes
    }

    fun solution(N: Int, M: Int): Int {
        var answer = 0

        val primes = makePrime(N)

        val primeSum = IntArray(primes.size+1)
        var primeIdx = 1
        for (prime in primes) {
            primeSum[primeIdx] = primeSum[primeIdx - 1] + prime
            primeIdx++
        }

        var s = 0
        var e = 0

        while (s < primeIdx) {
            val prefixSum = primeSum[e] - primeSum[s]
            if (prefixSum < M) {
                if (e < primeIdx - 1) {
                    e++
                } else {
                    s++
                }
            } else if (prefixSum > M) {
                s++
            } else {
                answer++
                s++
            }
        }

        return answer
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Solution3()
            val r = s.solution(20, 36)   //2
            println(r)
        }
    }
}
// 걸린 시간(분):