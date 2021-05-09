package baek

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

//2048 (Easy)
class Baek12100 {

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        var max = 0
        var answer = 2
        val board = Array(n) {
            readLine().split(" ").map { it.toInt() }.toIntArray().apply {
                answer = Math.max(maxOrNull()?:2, answer)
                max += sum()
            }
        }
        close()
        val moves = intArrayOf(1,2,3,2,0)

        fun merge(list: LinkedList<Int>): List<Int> {
            var i = 0
            while (i < list.size - 1) {
                if (list[i] == list[i + 1]) {
                    list[i] *= 2
                    answer = Math.max(answer, list[i])
                    list.removeAt(i + 1)
                }
                i++
            }
            return list
        }

        fun move(board: Array<IntArray>, idx: Int) {
            when (idx) {
                0 -> {  //아래로 이동
                    for (j in board.indices) {
                        val list = LinkedList<Int>()
                        for (i in board.indices.reversed()) {
                            if(board[i][j] != 0) list.add(board[i][j])
                            board[i][j] = 0
                        }
                        merge(list).forEachIndexed { index, v ->
                            board[board.size-1-index][j] = v
                        }
                    }
                }
                1 -> {  //오른쪽으로 이동
                    for (i in board.indices) {
                        val list = LinkedList<Int>()
                        for (j in board.indices.reversed()) {
                            if(board[i][j] != 0) list.add(board[i][j])
                            board[i][j] = 0
                        }
                        merge(list).forEachIndexed { index, v ->
                            board[i][board.size-1-index] = v
                        }
                    }
                }
                2 -> {  //위쪽으로 이동
                    for (j in board.indices) {
                        val list = LinkedList<Int>()
                        for (i in board.indices) {
                            if(board[i][j] != 0) list.add(board[i][j])
                            board[i][j] = 0
                        }
                        merge(list).forEachIndexed { index, v ->
                            board[index][j] = v
                        }
                    }
                }
                3 -> {  //왼쪽으로 이동
                    for (i in board.indices) {
                        val list = LinkedList<Int>()
                        for (j in board.indices) {
                            if(board[i][j] != 0) list.add(board[i][j])
                            board[i][j] = 0
                        }
                        merge(list).forEachIndexed { index, v ->
                            board[i][index] = v
                        }
                    }
                }

            }
        }

        fun check(): Boolean {
            //moves의 값에 맞게 board 재배치
//            println(moves.contentToString())
            val board = Array(n) { i -> IntArray(n) { j -> board[i][j] } }
            moves.forEach {
                move(board, it)
            }
            return answer == max
        }

        // 이동시 1개 남으면 true 반환
        fun dfs(idx: Int): Boolean {
            if (idx == 4) {
                return check()
            }
            for (i in 0 until 4) {
                moves[idx + 1] = i
                if (dfs(idx + 1)) return true
            }
            return false
        }
        dfs(-1)
//        check()
        println(answer)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek12100().main()
        }
    }
}
// 걸린 시간(분): 161