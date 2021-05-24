package baek

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

//인구 이동
class Baek16234 {

    class Point(var x: Int, var y: Int)

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val (n, l, r) = readLine().split(" ").map { it.toInt() }
        val board = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }
        close()

        val dx = intArrayOf(1, 0, -1, 0)
        val dy = intArrayOf(0, 1, 0, -1)

        var answer = 0
        val visited = Array(n) {BooleanArray(n)}
        val que: Queue<Point> = LinkedList()
        val union = hashSetOf<Point>()

        fun movePeople(): Boolean {
            visited.forEach { it.fill(false) }

            var isMoved = false
            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (visited[i][j]) continue

                    que.clear()
                    union.clear()
                    visited[i][j] = true
                    val start = Point(i, j)
                    que.offer(start)
                    var unionSum = 0
                    while (que.isNotEmpty()) {
                        val p = que.poll()
                        union.add(p)
                        unionSum += board[p.x][p.y]
                        for (i in 0 until 4) {
                            val nx = p.x + dx[i]
                            val ny = p.y + dy[i]
                            if (nx in 0 until n && ny in 0 until n && Math.abs(board[nx][ny] - board[p.x][p.y]) in l..r) {
                                if (visited[nx][ny]) continue
                                que.offer(Point(nx, ny))
                                visited[nx][ny] = true
                            }
                        }
                    }
                    if (union.size > 1) {
                        isMoved = true
                        val p = unionSum / union.size
                        union.forEach {
                            board[it.x][it.y] = p
                        }
                    }
                }
            }
            return isMoved
        }

        while (movePeople()) {
            answer++
        }
        println(answer)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek16234().main()
        }
    }
}
// 걸린 시간(분): 46
