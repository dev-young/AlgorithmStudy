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
        var answer = 0
        val numberArray = numbers.map { it.toString().toInt() }.toIntArray()
        val result = ArrayList<IntArray>()
//        println(permutationAll(numbers).toString())
        permutationAll(numberArray).forEach { println(it.contentToString()) }
//        permutation(numberArray, 1, result)
//        result.forEach { println(it.contentToString()) }


        return answer
    }

    /**백트래킹 사용하여 조합 구하기
     * @param arr 사용된 배열
     * @param r 몇개 뽑을건지
     * @param start 배열의 시작위치
     * @param n 배열의 끝
     * @param visited 방문 기록*/
    fun combination(
        arr: IntArray,
        r: Int,
        result: ArrayList<IntArray>,
        start: Int = 0,
        n: Int = arr.size,
        visited: BooleanArray = BooleanArray(arr.size)
    ) {
        if (r == 0) {
            val r = arrayListOf<Int>()
            visited.forEachIndexed { index, b ->
                if (b) {
                    r.add(arr[index])
                }
            }
            result.add(r.toIntArray())
            return
        }
        for (i in start until n) {
            visited[i] = true
            combination(arr, r - 1, result, i + 1, n, visited)
            visited[i] = false
        }
    }

    /**순열 구하기*/
    fun permutation(
        arr: IntArray,
        r: Int,
        result: ArrayList<IntArray>,
        temp: IntArray = IntArray(r),
        current: Int = 0,
        visited: BooleanArray = BooleanArray(arr.size)
    ) {
        if (r == current) {
            result.add(temp.clone())
            println(temp.contentToString())
        } else {
            for (i in arr.indices) {
                if (!visited[i]) {
                    visited[i] = true
                    temp[current] = arr[i]
                    permutation(arr, r, result, temp, current + 1, visited)
                    visited[i] = false
                }
            }
        }
    }


    /**문자열로부터 모든 순열 반환
     * ex) "12"  -> 1, 12, 2, 21
     * ex) "123" -> 1, 12, 123, 13, 132, 2, 21, 213, 23, 231, 3, 31, 312, 32, 321
     * */
    fun permutationAll(numbers: String): ArrayList<Int> {
        val combNumbers = arrayListOf<Int>()
        fun temp(numbers:String,result:String){
            if(!result.isNullOrEmpty()){
                combNumbers.add(result.toInt())
            }

            if(numbers.isEmpty()){
                return
            }
            numbers.forEachIndexed { index, c ->
                temp((numbers.removeRange(index..index)),result+c)
            }

        }

        temp(numbers, "")
        return combNumbers
    }

    fun permutationAll(numbers: IntArray): ArrayList<IntArray> {
        val combNumbers = arrayListOf<IntArray>()
        fun temp(numbers: IntArray, intArray: ArrayList<Int> = arrayListOf()){
            if(intArray.isNotEmpty()){
                combNumbers.add(intArray.toIntArray())
            }

            if(numbers.isEmpty()){
                return
            }
            numbers.forEachIndexed { index, c ->
                val list = numbers.toMutableList().apply { removeAt(index) }
                val arr = intArray.clone() as ArrayList<Int>
                arr.add(c)
                temp(list.toIntArray(), arr)
            }

        }

        temp(numbers)
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
}


fun main() {
    println(ExhaustiveSearch02().solution("123"))
}