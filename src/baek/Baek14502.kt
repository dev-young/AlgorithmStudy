package baek

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

//연구소
class Baek14502 {

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val temp = readLine().split(" ")
        val n = temp[0].toInt()
        val m = temp[1].toInt()
        val board = Array(n) { readLine().split(" ").map { it.toInt() } }
        close()

        val virus = arrayListOf<Pair<Int, Int>>()
        val wall = arrayListOf<Pair<Int, Int>>()
        val empty = arrayListOf<Pair<Int, Int>>()
        board.forEachIndexed { i, ints ->
            ints.forEachIndexed { j, int ->
                when (int) {
                    2 -> virus.add(Pair(i, j))
                    1 -> wall.add(Pair(i, j))
                    else -> empty.add(Pair(i, j))
                }
            }
        }
        var answer = 0

        val dx = intArrayOf(1, 0, -1, 0)
        val dy = intArrayOf(0, 1, 0, -1)

        fun getSafe(wallIdx: IntArray): Int {
            val board = Array(n) { board[it].toIntArray() }
            wallIdx.forEach {
                board[empty[it].first][empty[it].second] = 1
            }
            var res = empty.size - 3
            val que : Queue<Pair<Int, Int>> = LinkedList()
            que.addAll(virus)

            while (que.isNotEmpty()) {
                val p = que.poll()
                if(res < answer) break
                for (i in 0 until 4) {
                    val nx = p.first + dx[i]
                    val ny = p.second + dy[i]
                    if(nx in 0 until n && ny in 0 until m) {
                        if(board[nx][ny] == 0){
                            board[nx][ny] = 1
                            res --
                            que.offer(Pair(nx, ny))
                        }
                    }
                }
            }

            return res
        }

        val visited = hashSetOf<Int>()
        fun perm(temp: IntArray = IntArray(3), current: Int = 0) {
            if (3 == current) {
                answer = Math.max(answer, getSafe(temp))
            } else {
                for (i in 0 until empty.size) {
                    if (!visited.contains(i)) {
                        visited.add(i)
                        temp[current] = i
                        perm(temp, current + 1)
                        visited.remove(i)
                    }
                }
            }
        }
        perm()

        println(answer)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek14502().main()
        }
    }
}
// 걸린 시간(분): 67