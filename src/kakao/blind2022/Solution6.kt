package kakao.blind2022

import toArrayOfIntArray

class Solution6 {

    fun solution(board: Array<IntArray>, skill: Array<IntArray>): Int {
        var answer = 0

        val delta = Array(board.size) { IntArray(board[0].size) { 0 } }

        skill.forEach {
            val r1 = it[1]
            val c1 = it[2]
            val r2 = it[3] + 1
            val c2 = it[4] + 1
            val degree = if(it[0] == 1) it[5] * -1 else it[5]

            delta[r1][c1] += degree
            kotlin.runCatching {
                delta[r1][c2] -= degree
            }
            kotlin.runCatching {
                delta[r2][c1] -= degree
                delta[r2][c2] += degree
            }
        }

        for (i in delta.indices) {
            var prev = 0
            for (j in delta[0].indices) {
                delta[i][j] = prev + delta[i][j]
                prev = delta[i][j]
            }
        }

        for (j in delta[0].indices) {
            var prev = 0
            for (i in delta.indices) {
                delta[i][j] = prev + delta[i][j]
                prev = delta[i][j]
                if(delta[i][j] + board[i][j] > 0) answer++
            }
        }

        return answer
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Solution6()
            var r = s.solution(
                "[[5,5,5,5,5],[5,5,5,5,5],[5,5,5,5,5],[5,5,5,5,5]]".toArrayOfIntArray(),
                "[[1,0,0,3,4,4],[1,2,0,2,3,2],[2,1,0,3,1,2],[1,0,1,3,3,1]]".toArrayOfIntArray()
            )
            println(r)  //10

            r = s.solution(
                "[[1,2,3],[4,5,6],[7,8,9]]".toArrayOfIntArray(),
                "[[1,1,1,2,2,4],[1,0,0,1,1,2],[2,2,0,2,0,100]]".toArrayOfIntArray()
            )
            println(r)  //6
        }
    }
}
// 걸린 시간(분): 63