package baek

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

//구슬 탈출 2
class Baek13460 {

    data class State(val red: Pair<Int, Int>, val blue: Pair<Int, Int>)

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().split(" ")
        val r = n[0].toInt()
        val c = n[1].toInt()
        val board = Array(r) { readLine().toCharArray() }
        close()
        var red = Pair(0, 0)
        var blue = Pair(0, 0)
        for (i in board.indices) {
            for (j in board[0].indices) {
                if (board[i][j] == 'B')
                    blue = Pair(i, j)
                if (board[i][j] == 'R')
                    red = Pair(i, j)
            }
        }
        board[red.first][red.second] = '.'
        board[blue.first][blue.second] = '.'

        val dx = intArrayOf(1, 0, -1, 0)
        val dy = intArrayOf(0, 1, 0, -1)

        fun moveToEnd(state:State, dIdx: Int): State {
            val dx = dx[dIdx]
            val dy = dy[dIdx]
            var redR = state.red.first
            var redC = state.red.second
            var blueR = state.blue.first
            var blueC = state.blue.second

            fun moveRed(){
                while (true) {
                    if(board[redR][redC] == 'O') break
                    val nr = redR + dx
                    val nc = redC + dy
                    if(board[nr][nc] == 'O') {
                        redR = nr
                        redC = nc
                        break
                    }
                    if(board[nr][nc] == '#') break
                    if(nr == blueR && nc == blueC) break
                    redR = nr
                    redC = nc
                }
            }

            fun moveBlue(){
                while (true) {
                    if(board[blueR][blueC] == 'O') break
                    val nr = blueR + dx
                    val nc = blueC + dy
                    if(board[nr][nc] == 'O') {
                        blueR = nr
                        blueC = nc
                        break
                    }
                    if(board[nr][nc] == '#') break
                    if(nr == redR && nc == redC) break
                    blueR = nr
                    blueC = nc
                }
            }

            moveRed()
            moveBlue()
            moveRed()

            return State(Pair(redR,redC),Pair(blueR,blueC))
        }


        val que: Queue<State> = LinkedList()
        que.offer(State(red, blue))

        val visited = hashSetOf<State>()
        visited.add(State(red, blue))
        var answer = 0
        while (que.isNotEmpty()) {
            answer++
            if(answer > 10) {
                println(-1)
                return
            }
            out@for (x in 0 until que.size) {
                val p = que.poll()
                for (i in 0 until 4) {
                    val nextState = moveToEnd(p, i)
                    if(visited.contains(nextState))
                        continue
                    if(nextState.red == nextState.blue)
                        continue
                    if(board[nextState.red.first][nextState.red.second] == 'O') {
                        println(answer)
                        return
                    }

                    visited.add(nextState)
                    que.offer(nextState)
                }
            }

        }
        println(-1)
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek13460().main()
        }
    }
}
// 걸린 시간(분): 112