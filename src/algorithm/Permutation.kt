package algorithm

import kotlin.collections.ArrayList
import kotlin.collections.arrayListOf
import kotlin.collections.contentToString
import kotlin.collections.forEach
import kotlin.collections.forEachIndexed
import kotlin.collections.toIntArray

class Permutation {

    /**순열 구하기  ex) arr = [1,2,3], r=2  -> result = {[1,2],[1,3],[2,1],[2,3],[3,1],[3,2]}
     * @param arr 구할 배열
     * @param r arr에서 몇개를 뽑을지
     * @param result 결과를 담을 배열 */
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
//            println(temp.contentToString())
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

    /**순열 구하기 (결과 return)*/
    fun permutation(arr: IntArray, r: Int): ArrayList<IntArray> {
        val result = arrayListOf<IntArray>()
        permutation(arr, r, result)
        return result
    }


    /**문자열로부터 모든 순열 반환
     * ex) "12"  -> 1, 12, 2, 21
     * ex) "123" -> 1, 12, 123, 13, 132, 2, 21, 213, 23, 231, 3, 31, 312, 32, 321
     * */
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

    /**IntArray 로부터 모든 순열 반환
     * ex) {1,2}  -> 1, 12, 2, 21
     * ex) {1,2,3} -> 1, 12, 123, 13, 132, 2, 21, 213, 23, 231, 3, 31, 312, 32, 321
     * */
    fun permutationAll(numbers: IntArray): ArrayList<IntArray> {
        val combNumbers = arrayListOf<IntArray>()
        fun temp(numbers: IntArray, intArray: ArrayList<Int> = arrayListOf()) {
            if (intArray.isNotEmpty()) {
                combNumbers.add(intArray.toIntArray())
            }

            if (numbers.isEmpty()) {
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

    fun permutationAll2(array: Array<Any>): ArrayList<Array<Any>> {
        val combNumbers = arrayListOf<Array<Any>>()
        fun temp(numbers: Array<Any>, arrayList: ArrayList<Any> = arrayListOf()) {
            if (arrayList.isNotEmpty()) {
                combNumbers.add(arrayList.toArray())
            }

            if (numbers.isEmpty()) {
                return
            }
            numbers.forEachIndexed { index, c ->
                val list = numbers.toMutableList().apply { removeAt(index) }
                val arr = arrayList.clone() as ArrayList<Any>
                arr.add(c)
                temp(list.toTypedArray(), arr)
            }

        }

        temp(array)
        return combNumbers
    }

    fun <T> permutationAll3(array: Array<T>): ArrayList<Array<T>> {
        val combNumbers = arrayListOf<Array<T>>()
        fun temp(numbers: Array<T>, arrayList: ArrayList<T> = arrayListOf()) {
            if (arrayList.isNotEmpty()) {
                combNumbers.add(arrayList.toArray() as Array<T>)
            }

            if (numbers.isEmpty()) {
                return
            }
            numbers.forEachIndexed { index, c ->
                val list = numbers.toMutableList().apply { removeAt(index) } as ArrayList<T>
                val arr = arrayList.clone() as ArrayList<T>
                arr.add(c)
                temp(list.toArray() as Array<T>, arr)
            }

        }

        temp(array)
        return combNumbers
    }

    //테스트
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val per = Permutation()
            val arr = intArrayOf(1, 2, 3)
            val arr2 = arrayOf("1", "2", "3")

            per.permutation(arr, 3).forEach { println(it.contentToString()) }
            println()
            per.permutation(arr, 2).forEach { println(it.contentToString()) }
            println()
            per.permutation(arr, 1).forEach { println(it.contentToString()) }
            println()
            per.permutation(arr, 0).forEach { println(it.contentToString()) }
            println()
            per.permutation(arr, 4).forEach { println(it.contentToString()) }
            println()

            per.permutationAll2(arr2 as Array<Any>).forEach { println(it.contentToString()) }
            println()


        }
    }
}