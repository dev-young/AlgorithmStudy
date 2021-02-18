package baek


//포도주 시식 (동적프로그래밍)
class Baek2156 {

    fun main() {
        val n = readLine()!!.toInt()
        val arr = IntArray(n + 2)
        val dp = IntArray(n + 2)
        for (i in 1..n) {
            arr[i] = readLine()!!.toInt()
        }
        dp[1] = arr[1]
        dp[2] = arr[1] + arr[2]

        for (i in 3..n) {
            var r = dp[i - 1]
            (dp[i - 2] + arr[i]).let {
                if (r < it)
                    r = it
            }
            (dp[i - 3] + arr[i] + arr[i - 1]).let {
                if (r < it)
                    r = it
            }

            dp[i] = r
        }

        println(dp[n])

    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek2156().main()
        }
    }
}
// 걸린 시간(분):72