package baek

import java.io.BufferedReader
import java.io.InputStreamReader

//사다리 조작
class Baek15684 {

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val (n, m, h) = readLine().split(" ").map { it.toInt() }
        val ladder = Array(h) { IntArray(n) }
        repeat(m) {
            readLine().split(" ").map { it.toInt() }.let {
                val height = it[0] - 1
                val line = it[1] -1
                ladder[height][line] = 1
                ladder[height][line+1] = 2
            }
        }
        close()

        fun check(): Boolean {
            for (j in 0 until n) {
                var currentJ = j
                for (i in 0 until h) {
                    if (ladder[i][currentJ] == 1) currentJ ++
                    else if (ladder[i][currentJ] == 2) currentJ --
                }
                if(currentJ != j)
                    return false
            }
            return true
        }

        val maxPos = n*h
        fun dfs(pos:Int, cnt:Int, maxCnt:Int): Boolean {
            if(cnt == maxCnt) return check()
            if (pos >= maxPos) return false

            val (i,j) = Pair(pos/n, pos%n)
            if(j+1 < n && ladder[i][j] == 0 && ladder[i][j+1] == 0) {
                ladder[i][j] = 1
                ladder[i][j+1] = 2
                if(dfs(pos+2, cnt+1, maxCnt)) return true
                ladder[i][j] = 0
                ladder[i][j+1] = 0
            }
            return dfs(pos+1, cnt, maxCnt)
        }

        for (i in 0..3) {
            if(dfs(0, 0, i)){
                println(i)
                return
            }
        }
        println(-1)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek15684().main()
        }
    }
}
// 걸린 시간(분): 112 (풀다 포기하고 접근방법 보고 다시 짬)
