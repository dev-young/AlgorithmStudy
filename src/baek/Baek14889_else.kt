package baek

import kotlin.math.abs

//스타트와 링크 (백트래킹) _가장 빠른 처리시간 코드
class Baek14889_else {
    lateinit var arr: Array<IntArray>
    var minsum = -1

    fun main() {
        val n = readLine()!!.toInt()
        arr = Array(n) { _ -> IntArray(n) }

        for(i in 0 until n) {
            arr[i] = readLine()!!.split(" ").map(String::toInt).toIntArray()
        }

        val teamA = ArrayList<Int>()
        val teamB = ArrayList<Int>()
        divide(1, n, teamA, teamB)

        print(minsum)
    }

    fun divide(i: Int, n: Int, teamA: ArrayList<Int>, teamB: ArrayList<Int>) {
        if(teamA.count() == n/2 && teamB.count() == n/2) {
            // 팀별 합 계산
            // 4개 중 2개 골라서 표에서 수치를 가져와서 계속 더해나감
            var sumA = 0
            var sumB = 0

            for(a in 0 until teamA.size-1) {
                for(b in a+1 until teamA.size) {
                    val v1 = teamA[a]
                    val v2 = teamA[b]
                    sumA += arr[v1-1][v2-1] + arr[v2-1][v1-1]
                }
            }

            for(a in 0 until teamB.size-1) {
                for(b in a+1 until teamB.size) {
                    val v1 = teamB[a]
                    val v2 = teamB[b]
                    sumB += arr[v1-1][v2-1] + arr[v2-1][v1-1]
                }
            }

            val diff = abs(sumA - sumB)
            if(minsum == -1 || diff < minsum) minsum = diff
            if(minsum == 0) {
                print(minsum)
                return
            }
        }
        else {
            if(teamA.count() < n/2) {
                teamA.add(i)
                divide(i+1, n, teamA, teamB)
                teamA.remove(i)
            }
            if(teamB.count() < n/2) {
                teamB.add(i)
                divide(i+1, n, teamA, teamB)
                teamB.remove(i)
            }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek14889_else().main()
        }
    }
}
// 걸린 시간(분):55