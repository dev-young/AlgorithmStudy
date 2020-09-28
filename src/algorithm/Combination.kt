package algorithm

import kotlin.collections.ArrayList
import kotlin.collections.arrayListOf
import kotlin.collections.contentToString
import kotlin.collections.forEach
import kotlin.collections.forEachIndexed
import kotlin.collections.toIntArray

class Combination {

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
        visited: BooleanArray = BooleanArray(arr.size),
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

    fun combination(arr: IntArray, r: Int): ArrayList<IntArray> {
        val result = arrayListOf<IntArray>()
        combination(arr, r, result)
        return result
    }


    //테스트
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val per = Combination()
            val arr = intArrayOf(1, 2, 3)
            val arr2 = arrayOf("1", "2", "3")

            per.combination(arr, 2).forEach { println(it.contentToString()) }
            println()



        }
    }
}