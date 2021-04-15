package baek

//전깃줄 (동적프로그래밍)
class Baek2565 {

    fun main() {
        val n = readLine()!!.toInt()
        val arr = arrayListOf<IntArray>()
        for (i in 0 until n){
            arr.add(readLine()!!.split(" ").toList().map { it.toInt() }.toIntArray())
        }
        arr.sortBy { it[0] }
        fun bs(arr: Array<IntArray>, search: IntArray): Int {
            var low = 0
            var mid = 0
            var high = arr.size

            if (search[0] > arr.last()[0] && search[1] > arr.last()[1]) return arr.lastIndex

            while (low <= high) {
                mid = (low + high) / 2
                val target = arr[mid]
                when {
                    target[0] < search[0] && target[1] < search[1] -> {
                        low = mid + 1
                    }
                    else -> {
                        high = mid - 1
                    }
                }
            }
            if (mid < low)
                return mid + 1
            return mid
        }

        val dp = Array(arr.size) { intArrayOf(Int.MAX_VALUE, Int.MAX_VALUE) }
        dp[0] = arr[0]
        var idx = 0
        for (i in 1 until arr.size) {
            val n = arr[i]
            if (dp[idx][0] < n[0] && dp[idx][1] < n[1]) {
                dp[++idx] = n
            } else {
                val ii = bs(dp, n)
                dp[ii] = n
            }
        }

        println(n - idx - 1)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val r = Baek2565().main()
        }
    }
}
// 걸린 시간(분): 60