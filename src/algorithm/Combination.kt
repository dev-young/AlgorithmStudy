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

    /**@param n 배열 크기 ex) n==4 -> {0,1,2,3} 중에서 r개 뽑는다.
     * @param r 뽑을 갯수*/
    fun combination(n: Int, r:Int): ArrayList<IntArray> {
        val visited = hashSetOf<Int>()
        val result = arrayListOf<IntArray>()
        fun comb(i:Int){
            if (i == n) return
            if(visited.size == r) {
                val temp = visited.map { it }.toIntArray()
                println(temp.contentToString())
                return
            }
            visited.add(i+1)
            comb(i+1)
            visited.remove(i+1)
            comb(i+1)
        }
        comb(-1)
        return result
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

            per.combination(1, 4)


        }
    }
}