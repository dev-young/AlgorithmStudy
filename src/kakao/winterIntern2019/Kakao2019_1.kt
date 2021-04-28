package kakao.winterIntern2019

//https://programmers.co.kr/learn/courses/30/lessons/64061
class Kakao2019_1 {

    fun solution(board: Array<IntArray>, moves: IntArray): Int {
        var answer = 0

        val basket = arrayListOf<Int>()

        fun get(n: Int): Int? {
            val j = n - 1
            for (i in board.indices) {
                if (board[i][j] != 0) {
                    val t = board[i][j]
                    board[i][j] = 0
                    return t
                }
            }
            return null
        }

        moves.forEach {
            get(it)?.let {
                if(basket.isNotEmpty() && basket.last() == it) {
                    basket.removeAt(basket.lastIndex)
                    answer += 2
                } else {
                    basket.add(it)
                }
            }
        }

        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Kakao2019_1()
            val board: Array<IntArray> = arrayOf(
                    intArrayOf(0, 0, 0, 0, 0),
                    intArrayOf(0, 0, 1, 0, 3),
                    intArrayOf(0, 2, 5, 0, 1),
                    intArrayOf(4, 2, 4, 4, 2),
                    intArrayOf(3, 5, 1, 3, 1))
            val moves: IntArray = intArrayOf(1, 5, 3, 5, 1, 2, 1, 4)
            val r = s.solution(board, moves)
            println(r)
        }
    }
}
// 걸린 시간(분): 17