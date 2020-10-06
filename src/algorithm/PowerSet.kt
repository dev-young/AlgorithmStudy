package algorithm

import baek.getResult

class PowerSet {

    /**부분집합 구하기*/
    fun powerSet(arr: IntArray): MutableList<IntArray> {
        val result = mutableListOf<IntArray>()
        fun powerSet(arr: IntArray, visited: BooleanArray, n: Int, idx: Int) {
            if (idx == n) {
//            print(arr, visited, n)
                val temp = mutableListOf<Int>()
                visited.forEachIndexed { index, b ->
                    if(b) temp.add(arr[index])
                }
                result.add(temp.toIntArray())
                return
            }
            visited[idx] = false
            powerSet(arr, visited, n, idx + 1)
            visited[idx] = true
            powerSet(arr, visited, n, idx + 1)
        }

        powerSet(arr, BooleanArray(arr.size), arr.size, 0)
        return result
    }





    //테스트
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val powerSet = PowerSet()
            val arr = intArrayOf(1, 2, 3)

            powerSet.powerSet(arr).forEach { println(it.contentToString()) }
            println()



        }
    }
}