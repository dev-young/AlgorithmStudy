package baek


//LCS (동적프로그래밍)
class Baek9251 {

    fun main() {
        val str1 = readLine()!!
        val str2 = readLine()!!
        val lcs = Array(str1.length + 1) { IntArray(str2.length + 1) }

        for (i in 1..str1.length) {
            for (j in 1..str2.length) {
                if (str1[i - 1] == str2[j - 1]) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1
                } else {
                    lcs[i][j] = Math.max(lcs[i][j - 1], lcs[i - 1][j])
                }
            }
        }
        println(lcs[str1.length][str2.length])
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek9251().main()
        }
    }
}
// 걸린 시간(분): 5