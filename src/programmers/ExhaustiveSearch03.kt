package programmers

import kotlin.collections.ArrayList
import kotlin.collections.arrayListOf
import kotlin.collections.contentToString
import kotlin.collections.forEach
import kotlin.collections.forEachIndexed
import kotlin.collections.toIntArray


//https://programmers.co.kr/learn/courses/30/lessons/42842?language=kotlin
class ExhaustiveSearch03 {
    fun solution(brown: Int, yellow: Int): IntArray {
        for (x in 1..yellow) {
            for (y in yellow downTo 1) {
                if(x*y == yellow) {
                    val border = x*2 + y*2 + 4
                    if (border == brown)
                        return intArrayOf(y+2, x+2)
                }
            }
        }

        return intArrayOf()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = ExhaustiveSearch03()
            val r = s.solution(10, 2)
            println(r)
        }
    }
}

// 걸린 시간: 13분