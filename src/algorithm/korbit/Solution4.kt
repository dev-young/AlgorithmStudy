package algorithm.korbit

import kotlin.math.abs
import kotlin.math.min

class Solution4 {
    fun solution(numbers: IntArray, K: Int): Int {
        var answer = Int.MAX_VALUE

        //인접 차이가 K 이하인지 확인
        fun check(arr: IntArray): Boolean {
            for (i in 0 until arr.lastIndex) {
                if (abs(numbers[arr[i]] - numbers[arr[i + 1]]) > K) return false
            }
            return true
        }

        //스왑 몇번 했는지 계산
        fun swapCount(_arr: IntArray): Int {
            var cnt = 0
            val arr = _arr.map { numbers[it] }.toIntArray()
            for (i in arr.indices) {
                if (numbers[i] != arr[i]) {
                    for (j in arr.indices) {
                        if (arr[j] == numbers[i]) {
                            val temp = arr[i]
                            arr[i] = arr[j]
                            arr[j] = temp
                            break
                        }
                    }
                    cnt++
                }
            }
            return cnt
        }

        val visited = hashSetOf<Int>()
        val r = numbers.size
        fun perm(temp: IntArray = IntArray(r), current: Int = 0) {
            if (r == current) {
                if(check(temp)) {
                    answer = min(answer, swapCount(temp))
                }
            } else {
                for (i in numbers.indices) {
                    if (!visited.contains(i)) {
                        visited.add(i)
                        temp[current] = i
                        perm(temp, current + 1)
                        visited.remove(i)
                    }
                }
            }
        }
        perm()

        return if (answer == Int.MAX_VALUE) -1 else answer
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Solution4()
            val r = s.solution(intArrayOf(3, 7, 2, 8, 6, 4, 5, 1), 3)   // 2
            println(r)
        }
    }
}
// 걸린 시간(분):