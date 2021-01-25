package baek

//스도쿠 (백트래킹)
class Baek2580 {

    fun main() {
        val board = arrayListOf<IntArray>()
        for (i in 1..9)
            board.add(readLine()!!.split(" ").map { it.toInt() }.toIntArray())

        val blankList = arrayListOf<Pair<Int, Int>>()
        board.forEachIndexed { y, ints ->
            ints.forEachIndexed { x, int ->
                if (int == 0)
                    blankList.add(Pair(y, x))
            }
        }

        fun check(blank: Pair<Int, Int>, num: Int): Boolean {
            for (i in 0 until 9) {
                if (board[blank.first][i] == num || board[i][blank.second] == num)
                    return false
            }

            val startI = blank.first.div(3) * 3
            val startJ = blank.second.div(3) * 3
            for (i in startI..startI + 2) {
                for (j in startJ..startJ + 2) {
                    if (board[i][j] == num)
                        return false
                }
            }

            return true
        }

        fun dfs(blankIdx: Int, num: Int): Boolean {

            if (blankIdx == blankList.size) {
                return true
            }

            //num이 가능한지 확인
            if (!check(blankList[blankIdx], num))
                return false

            for (i in 1..9) {
                board[blankList[blankIdx].first][blankList[blankIdx].second] = num
                if (dfs(blankIdx + 1, i))
                    return true
                board[blankList[blankIdx].first][blankList[blankIdx].second] = 0
            }

            return false
        }

        for (i in 1..9) {
            if (dfs(0, i)) break
        }

        board.forEach {
            println(it.joinToString(" "))
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek2580().main()
        }
    }
}
// 걸린 시간(분):110