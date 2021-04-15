package programmers.challenge

//https://programmers.co.kr/learn/courses/30/lessons/68645?language=kotlin
//level3
class c68645 {
    fun solution(n_: Int): IntArray {
        var n = n_
        val board = Array(n) {IntArray(n) }
        var answer = arrayListOf<Int>()
        var i = 0
        var j = 0
        var num = 1
        while (n > 0) {
            if(n == 1) {
                board[i][j] = num
                break
            }
            for (a in 1 until n) {
                board[i++][j] = num++
            }
            for (a in 1 until n) {
                board[i][j++] = num++
            }
            for (a in 1 until n) {
                board[i--][j--] = num++
            }
            i+=2
            j++
            n -= 3
        }

        for (i in 0 until n_) {
            for (j in 0 .. i) {
                answer.add(board[i][j])
            }
        }

        return answer.toIntArray()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = c68645()
            val r = s.solution(6)
            println(r.contentToString())

        }
    }
}
// 걸린 시간: 49분
