package programmers.challenge

import java.util.*
import kotlin.collections.HashSet
import kotlin.math.max


//https://programmers.co.kr/learn/courses/30/lessons/12977
//level1
class c12977 {

    fun solution(nums: IntArray): Int {
        var answer = 0
        val primeSet = hashSetOf<Int>()
        primeSet.add(1)
        primeSet.add(2)
        primeSet.add(3)
        fun isPrime(n:Int) : Boolean{
            if (primeSet.contains(n)) return true
            val half = n/2
            for (i in 2 .. half) {
                if (n%i ==0) return false
            }
            primeSet.add(n)
            return true
        }

        for (i in nums.indices) {
            for (j in i+1 until nums.size)
                for (k in j+1 until nums.size) {
                    val sum = nums[i] + nums[j] + nums[k]
                    if (isPrime(sum))
                        answer++
                }
        }

        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = c12977()
//            println(s.solution(arrayOf(intArrayOf(1, 1, 1, 1, 1, 1, 1, 1), intArrayOf(0, 1, 1, 1, 1, 1, 1, 1), intArrayOf(0, 0, 0, 0, 1, 1, 1, 1), intArrayOf(0, 1, 0, 0, 1, 1, 1, 1), intArrayOf(0, 0, 0, 0, 0, 0, 1, 1), intArrayOf(0, 0, 0, 0, 0, 0, 0, 1), intArrayOf(0, 0, 0, 0, 1, 0, 0, 1), intArrayOf(0, 0, 0, 0, 1, 1, 1, 1))).contentToString())
//            println(s.solution(4, arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4))))

        }
    }
}
// 걸린 시간:
