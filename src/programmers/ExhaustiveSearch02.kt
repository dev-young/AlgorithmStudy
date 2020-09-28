package programmers

import kotlin.collections.ArrayList
import kotlin.collections.arrayListOf
import kotlin.collections.contentToString
import kotlin.collections.forEach
import kotlin.collections.forEachIndexed
import kotlin.collections.toIntArray


//https://programmers.co.kr/learn/courses/30/lessons/42839?language=kotlin
class ExhaustiveSearch02 {
    fun solution(numbers: String): Int {
        var answer = hashSetOf<Int>()
        val numberArray = numbers.map { it.toString().toInt() }.toIntArray()
        permutationAll(numbers).forEach {
            if(isPrime(it)){
                println(it)
                answer.add(it)
            }
        }

        return answer.size
    }
    fun permutationAll(numbers: String): ArrayList<Int> {
        val combNumbers = arrayListOf<Int>()
        fun temp(numbers: String, result: String) {
            if (!result.isNullOrEmpty()) {
                combNumbers.add(result.toInt())
            }

            if (numbers.isEmpty()) {
                return
            }
            numbers.forEachIndexed { index, c ->
                temp((numbers.removeRange(index..index)), result + c)
            }

        }

        temp(numbers, "")
        return combNumbers
    }

    //소수 판별
    fun isPrime(num: Int): Boolean {
        if (num == 1 || num == 0) {
            return false
        }
        for (i in 2..num / 2) {
            if (num % i == 0) {
                return false
            }
        }
        return true
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = ExhaustiveSearch02()
            val r = s.solution("011")
            println(r)
        }
    }
}