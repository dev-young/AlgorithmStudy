package baek

//가장 긴 증가하는 부분 수열 (동적프로그래밍)
class Baek11053 {

    fun main() {
        readLine()
        val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
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
        println(idx + 1)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val r = Baek11053().main()
        }
    }
}
// 걸린 시간(분): 5