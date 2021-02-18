package baek


//평범한 배낭 (동적프로그래밍)
class Baek12865 {

    fun main() {
        val t = readLine()!!.split(" ")
        val n = t[0].toInt()
        val k = t[1].toInt()
        val list = arrayListOf<IntArray>()
        for (i in 1..n) {
            readLine()!!.split(" ").let {
                list.add(intArrayOf(it[0].toInt(), it[1].toInt()))
            }
        }
        /**@return 가치 */
        val dp = Array(n + 1) { IntArray(k + 1) }
        fun go(i: Int, w: Int): Int {
            if (dp[i][w] > 0) return dp[i][w]
            if (i == n) {
                return 0
            }


            val n1 =
                if (w + list[i][0] <= k)
                    list[i][1] + go(i + 1, w + list[i][0])
                else
                    0

            val n2 = go(i + 1, w)
            dp[i][w] = Math.max(n1, n2)
            return dp[i][w]
        }

        println(go(0, 0))

    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek12865().main()
        }
    }
}
// 걸린 시간(분):77