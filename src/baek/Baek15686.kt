package baek

import java.io.BufferedReader
import java.io.InputStreamReader

//치킨 배달
class Baek15686 {

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val board = Array(n) { readLine().split(" ").map { it.toInt() } }
        close()
        val stores = arrayListOf<Pair<Int, Int>>()
        val houses = arrayListOf<Pair<Int, Int>>()
        for (i in board.indices) {
            for (j in board.indices) {
                if (board[i][j] == 2)
                    stores.add(Pair(i, j))
                else if (board[i][j] == 1)
                    houses.add(Pair(i, j))
            }
        }

        fun check(storeIdx: IntArray): Int {
            var res = 0
            houses.forEach { house ->
                var min = Int.MAX_VALUE
                storeIdx.forEach { idx ->
                    val d = Math.abs(stores[idx].first - house.first) + Math.abs(stores[idx].second - house.second)
                    min = Math.min(min, d)
                }
                res += min
            }
            return res
        }

        var answer = Int.MAX_VALUE
        val visited = hashSetOf<Int>()
        fun comb(i:Int){
            if (i == stores.size) return
            if(visited.size == m) {
                val temp = visited.map { it }.toIntArray()
                answer = Math.min(answer, check(temp))
                return
            }
            visited.add(i+1)
            comb(i+1)
            visited.remove(i+1)
            comb(i+1)
        }
        comb(-1)

        println(answer)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek15686().main()
        }
    }
}
// 걸린 시간(분): 36 (바보같이 순열이랑 조합 착각해서 오지게 삽질했다...)
