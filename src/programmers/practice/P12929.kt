package programmers.practice

//https://programmers.co.kr/learn/courses/30/lessons/12929
//올바른 괄호의 갯수
//level4
class P12929 {

    /**카탈란 수를 사용하는 문제이다.
     * https://bb-dochi.tistory.com/51 참고*/
    fun solution(n: Int): Int {
        val dp = IntArray(n+1)
        dp[0] = 1
        dp[1] = 1

        for(i in 2 .. n) {
            for (j in 1 .. i) {
                dp[i] += dp[i-j] * dp[j-1]
            }
        }

        return dp[n]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = P12929()
            println(s.solution(2))
            println(s.solution(4))
        }
    }
}
// 걸린 시간(분):