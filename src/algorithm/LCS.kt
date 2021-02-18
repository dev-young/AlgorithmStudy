package algorithm

//최장길이 공통 부분문자열
class LCS(val str1: String, val str2: String) {
    val lcs = Array(str1.length + 1) { IntArray(str2.length + 1) }

    /**최장길이 공통 부분문자열의 길이 */
    fun calLength(i_: Int = str1.length, j_: Int = str2.length): Int {
        for (i in 1..i_) {
            for (j in 1..j_) {
                if (str1[i - 1] == str2[j - 1]) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1
                } else {
                    lcs[i][j] = Math.max(lcs[i][j - 1], lcs[i - 1][j])
                }
            }
        }
        return lcs[i_][j_]
    }

    /**최장길이 공통 부분문자열 출력*/
    fun getResult(): ArrayList<String> {
        val result = arrayListOf<String>()
        fun rec(i: Int, j: Int, r: String = "") {
            if (i == 0 || j == 0) {
                result.add(r)
                return
            }

            if (str1[i - 1] == str2[j - 1]) {
                rec(i - 1, j - 1, str1[i - 1] + r)
            } else {
                if (lcs[i][j] == lcs[i][j - 1]) {
                    rec(i, j - 1, r)
                }
                if (lcs[i][j] == lcs[i - 1][j]) {
                    rec(i - 1, j, r)
                }
            }
        }
        rec(str1.length, str2.length)
        return result
    }


    //테스트
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val lcs = LCS("bdcaba", "abcbdab")
            println(lcs.calLength())

            lcs.getResult().forEach { println(it) }
        }
    }
}