package baek

import java.io.BufferedReader
import java.io.InputStreamReader

//테트로미노
class Baek14500 {

    /** 출제 의도는 DFS를 사용해 푸는것이였는데 난 바보같이 블럭을 선언해서 풀었다... ㅗ모양을 제외한 나머지 모양은 dfs를 이용해 연속된 4개의 블럭을 구할 수 있다.*/
    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val temp = readLine().split(" ")
        val n = temp[0].toInt()
        val m = temp[1].toInt()
        val board = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray()}
        close()

        val tetrominos = arrayOf(
                arrayOf(Pair(0,0),Pair(0,1),Pair(0,2),Pair(0,3)),
                arrayOf(Pair(0,0),Pair(1,0),Pair(2,0),Pair(3,0)),

                arrayOf(Pair(0,0),Pair(0,1),Pair(1,0),Pair(1,1)),

                arrayOf(Pair(0,0),Pair(1,0),Pair(2,0),Pair(2,1)),
                arrayOf(Pair(0,0),Pair(0,1),Pair(0,2),Pair(1,0)),
                arrayOf(Pair(0,0),Pair(0,1),Pair(1,1),Pair(2,1)),
                arrayOf(Pair(0,2),Pair(1,0),Pair(1,1),Pair(1,2)),

                arrayOf(Pair(0,1),Pair(1,1),Pair(2,1),Pair(2,0)),
                arrayOf(Pair(0,0),Pair(1,0),Pair(1,1),Pair(1,2)),
                arrayOf(Pair(0,0),Pair(0,1),Pair(1,0),Pair(2,0)),
                arrayOf(Pair(0,0),Pair(0,1),Pair(0,2),Pair(1,2)),

                arrayOf(Pair(0,0),Pair(1,0),Pair(1,1),Pair(2,1)),
                arrayOf(Pair(0,1),Pair(0,2),Pair(1,0),Pair(1,1)),

                arrayOf(Pair(0,1),Pair(1,0),Pair(1,1),Pair(2,0)),
                arrayOf(Pair(0,0),Pair(0,1),Pair(1,1),Pair(1,2)),

                arrayOf(Pair(0,0),Pair(0,1),Pair(0,2),Pair(1,1)),
                arrayOf(Pair(1,0),Pair(1,1),Pair(1,2),Pair(0,1)),
                arrayOf(Pair(0,0),Pair(1,0),Pair(2,0),Pair(1,1)),
                arrayOf(Pair(0,1),Pair(1,0),Pair(1,1),Pair(2,1))
        )

        var answer = 0
        for (tetromino in tetrominos) {
            for (i in 0 until n) {
                out@for (j in 0 until m) {
                    var sum = 0
                    for (pos in tetromino) {
                        val nr = pos.first + i
                        val nc = pos.second + j
                        if(nr >= n || nc >= m) break
                        sum += board[nr][nc]
                    }
                    answer = Math.max(answer, sum)
                }
            }

        }
        println(answer)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek14500().main()
        }
    }
}
// 걸린 시간(분): 55 (로직 짜는데 39분)