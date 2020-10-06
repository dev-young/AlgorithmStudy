package programmers

import kotlin.collections.ArrayList
import kotlin.collections.arrayListOf
import kotlin.collections.contentToString
import kotlin.collections.forEach
import kotlin.collections.forEachIndexed
import kotlin.collections.toIntArray


//https://programmers.co.kr/learn/courses/30/lessons/43165?language=kotlin
class BFSDFS01 {
    fun solution(numbers: IntArray, target: Int): Int {
        var answer = 0

        val ps = powerSet(numbers.indices.toList().toIntArray())
        ps.forEach {
            val temp = numbers.clone()
            it.forEach {
                temp[it] *= -1
            }
            if(temp.sum() == target) answer++
        }


        return answer
    }

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


    //솔직히 봐도 봐도 이해 불가...
    fun solOther(numbers: IntArray, target: Int): Int {
        val ans = numbers.fold(listOf(0)) { list, i ->
            println("i: $i, list: ${list.toString()}")
            list.run {
                val r = map { it + i } + map { it - i }
                println("i: $i, listr: $r")
                r
            }

        }


        return ans.count {
            println(it)
            it == target
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = BFSDFS01()
            val r = s.solOther(intArrayOf(1, 2, 3), 3)
            println(r)
        }
    }
}

// 걸린 시간: 22분