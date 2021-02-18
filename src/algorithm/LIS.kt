package algorithm

import java.util.*

//가장 긴 증가하는 부분 수열
class LIS(val arr: IntArray) {
    val dp = IntArray(arr.size)

    /**해당 값 혹은 해당 값 다음으로 큰 값을 가지는 인덱스 반환 */
    fun bs(arr: IntArray, search: Int): Int {
        var low = 0
        var mid = 0
        var high = arr.size

        if (search > arr.last()) return arr.lastIndex

        while (low <= high) {
            mid = (low + high) / 2
            val target = arr[mid]
            when {
                target < search -> {
                    low = mid + 1
                }
                target > search -> {
                    high = mid - 1
                }
                else -> {
                    return mid
                }
            }
        }
        if (mid < low)
            return mid + 1
        return mid
    }

    fun cal() {
        for (i in arr.indices) {
            dp[i] = 1

            for (j in 0 until i) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1)
                }
            }
        }
    }

    /**https://jason9319.tistory.com/113
     * O(NlogN)방법 */
    fun calFast(): Int {
        val dp = IntArray(arr.size) { Int.MAX_VALUE }
        dp[0] = arr[0]
        var idx = 0
        for (i in 1 until arr.size) {
            val n = arr[i]
            if (dp[idx] < n) {
                dp[++idx] = n
            } else {
                val ii = bs(dp, n)
                dp[ii] = n
            }
        }
        return idx + 1
    }

    /**정답이 여러개인 경우 마지막 경로만 출력*/
    fun calFastWithPrint(): Int {
        val dp = IntArray(arr.size) { Int.MAX_VALUE }
        dp[0] = arr[0]
        val tracking = Array(arr.size) { Pair(0, arr[0]) }
        var idx = 0
        for (i in 1 until arr.size) {
            val n = arr[i]
            if (dp[idx] < n) {
                dp[++idx] = n
                tracking[i] = Pair(idx, n)
            } else {
                bs(dp, n).let {
                    dp[it] = n
                    tracking[i] = Pair(it, n)
                }
            }
        }

        var temp = idx
        val result = arrayListOf<Int>()
        for (i in arr.indices.reversed()) {
            if(temp == tracking[i].first){
                result.add(0, tracking[i].second)
                temp--
            }
        }
        println(result)

        return idx + 1
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
//            val lis = LIS(intArrayOf(10, 20, 10, 30, 20, 50))
            val lis = LIS(intArrayOf(11,12, 4,5,6,7,8,1,2,3,4,10,1,2))
            println(lis.calFastWithPrint())
        }
    }
}
