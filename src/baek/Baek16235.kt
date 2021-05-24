package baek

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.system.measureTimeMillis

//나무 재테크
class Baek16235 {

    val dx = intArrayOf(1, 0, -1, 0, 1, 1, -1, -1)
    val dy = intArrayOf(0, 1, 0, -1, 1, -1, -1, 1)
    val board = Array(10) { IntArray(10) { 5 } }
    val treeMap = Array(10) { Array(10) { ArrayDeque<Int>() } }
    var answer = 0

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val (n, m, k) = readLine().split(" ").map { it.toInt() }
        val arr = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }
        repeat(m) {
            readLine().split(" ").map { it.toInt() }.let {
                treeMap[it[0] - 1][it[1] - 1].add(it[2])
            }
        }
        close()

        answer = m

        fun springSummer() {
            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (treeMap[i][j].isEmpty()) continue
                    val p = Pair(i, j)
                    val list = treeMap[i][j]
                    var deadPoint: Int? = null
                    for (i in list.indices) {
                        if (board[p.first][p.second] < list[i]) {
                            //양분이 부족한 경우
                            deadPoint = i
                            break
                        }
                        board[p.first][p.second] -= list[i]
                        list[i]++
                    }

                    deadPoint?.let { start ->
                        repeat(list.size - start) {
                            board[p.first][p.second] += (list.last() / 2)
                            list.removeLast()
                            answer--
                        }
                    }
                }
            }
        }

        fun fall() {
            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (treeMap[i][j].isEmpty()) continue
                    val p = Pair(i, j)
                    val list = treeMap[i][j]

                    val newTreeCount = list.count { it % 5 == 0 }
                    if (newTreeCount > 0) {
                        for (i in 0 until 8) {
                            val nx = p.first + dx[i]
                            val ny = p.second + dy[i]

                            if (nx in 0 until n && ny in 0 until n) {
                                for (a in 0 until newTreeCount)
                                    treeMap[nx][ny].addFirst(1)
                                answer += newTreeCount
                            }
                        }
                    }
                }
            }
        }

        fun winter() {
            for (i in 0 until n) {
                for (j in 0 until n) {
                    board[i][j] += arr[i][j]
                }
            }
        }
        val time = measureTimeMillis {
            for (i in 0 until k) {
                springSummer()
                fall()
                winter()
                if (answer <= 0) break
            }
        }

        println(answer)
        println(time)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek16235().main()
        }
    }
}
// 걸린 시간(분): 70 (각 자료구조별 특징을 다시 공부할 필요가 있을 듯 하다.)
