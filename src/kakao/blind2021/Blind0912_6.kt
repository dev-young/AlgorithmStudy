package kakao.blind2021

import java.util.*

class Blind0912_6 {
    /**풀이법 (https://www.youtube.com/watch?v=aZfzE4jIIMU) 참고
     * */
    data class Point(val r: Int, val c: Int)

    fun solution(board: Array<IntArray>, r: Int, c: Int): Int {
        val cards = hashMapOf<Int, Array<Point>>().apply {
            board.forEachIndexed { i, ints ->
                ints.forEachIndexed { j, int ->
                    if (int > 0) {
                        if (contains(int)) {
                            this[int]!![1] = Point(i, j)
                        } else {
                            this[int] = Array(2) { Point(i, j) }
                        }
                    }
                }
            }
        }
        val keyList = cards.keys.toList()

        //어떤 카드를 먼저 할지 순서목록을 구한다
        val result = arrayListOf<Array<Point>>()
        val temp = Array(cards.size * 2) { Point(-1, -1) }
        val visited = hashSetOf<Int>()
        fun permutation(current: Int = 0) {
            if (cards.size == current) {
                result.add(temp.clone())
            } else {
                for (key in keyList) {
                    if (!visited.contains(key)) {
                        visited.add(key)
                        temp[current * 2] = cards[key]!![0]
                        temp[current * 2 + 1] = cards[key]!![1]
                        permutation(current + 1)
                        temp[current * 2] = cards[key]!![1]
                        temp[current * 2 + 1] = cards[key]!![0]
                        permutation(current + 1)
                        visited.remove(key)
                    }
                }
            }
        }
        permutation()

        /**2차원 좌표계에서 시작(x1y1)과 끝(x2y2)이 주어졌을 때 최소 이동 거리 구하기
         * 이동할 수 없는경우 -1 리턴 */
        val delta = arrayOf(intArrayOf(0, 1), intArrayOf(0, -1), intArrayOf(-1, 0), intArrayOf(1, 0))   //상하좌우
        fun bfs(board: Array<IntArray>, r1: Int, c1: Int, r2: Int, c2: Int): Int {
            var moveCount = -1
            val visited = Array(board.size) { BooleanArray(board.size) { false } }
            val que: Queue<Pair<Int, Int>> = LinkedList()
            que.offer(Pair(r1, c1))
            while (!que.isEmpty()) {
                moveCount++
                for (i in 1.. que.size) {
                    val point = que.poll()
                    if (visited[point.first][point.second]) continue

                    visited[point.first][point.second] = true
//                println("방문 좌표:${point.first},${point.second}")
                    if (point.first == r2 && point.second == c2) return moveCount
                    for (d in delta) {
                        var nr = point.first + d[0]
                        var nc = point.second + d[1]
                        /**보드 범위 내에 있는 경우*/
                        if (nr in board.indices && nc in board[0].indices ) {
                            /**방문한적이 없는 경우 */
                            if(!visited[nr][nc])
                                que.offer(Pair(nr,nc))
                        } else continue
                        if(board[nr][nc] != 0) continue

                        for (i in 0..2) {
                            nr += d[0]
                            nc += d[1]
                            if (nr in board.indices && nc in board[0].indices ) {
                                if(board[nr][nc] != 0) break
                            } else {
                                nr -= d[0]
                                nc -= d[1]
                                break
                            }
                        }
                        if(!visited[nr][nc])
                            que.offer(Pair(nr,nc))

                    }
                }
            }
            return -1
        }

        var minDis = Int.MAX_VALUE
        //순서에 맞게 이동거리를 계산한다.
        out@ for (arr in result) {
            val copyBoard = Array(board.size){IntArray(board.size)}
            board.forEachIndexed { i, ints -> ints.forEachIndexed { j, int -> copyBoard[i][j] = int } }
            var dis = bfs(copyBoard, r,c, arr[0].r, arr[0].c) + 1
            copyBoard[arr[0].r][arr[0].c] = 0
            for (i in 1 until arr.size) {
                dis += bfs(copyBoard, arr[i - 1].r, arr[i - 1].c, arr[i].r, arr[i].c) + 1
                copyBoard[arr[i].r][arr[i].c] = 0
                if (dis > minDis)
                    continue@out
            }
            minDis = dis
        }
        return minDis
    }


}

fun main() {
    val s = Blind0912_6()
    val r = s.solution(arrayOf(intArrayOf(1, 0, 0, 3), intArrayOf(2, 0, 0, 0), intArrayOf(0, 0, 0, 2), intArrayOf(3, 0, 1, 0)), 1, 0)
    println(r)
}