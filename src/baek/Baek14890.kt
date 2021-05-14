package baek

import java.io.BufferedReader
import java.io.InputStreamReader

//경사로
class Baek14890 {

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val temp = readLine().split(" ")
        val n = temp[0].toInt()
        val l = temp[1].toInt()
        val board = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }
        close()

        fun checkHorizon(i : Int) : Boolean{
            var prevTop = board[i][0]
            var prev = board[i][0]
            var sameCount = 1
            var completeCount = 1
            for (j in 1 until n) {
                val curr = board[i][j]
                if(prev == curr)
                    sameCount++
                if(prevTop - curr == 1) {
                    if(curr != prev)
                        sameCount = 1
                    if(sameCount == l) {
                        prevTop = curr
                        completeCount += sameCount
                        sameCount = 0
                    }
                } else if (prevTop - curr == -1) {
                    completeCount++
                    if(sameCount >= l) {
                        prevTop = curr
                        sameCount = 1
                    } else {
                        return false
                    }
                } else if(prevTop - curr == 0) {
                    if(prev == curr)
                        completeCount++
                } else {
                    return false
                }

                prev = curr

            }
            return completeCount == n
        }

        fun checkVertical(j : Int) : Boolean{
            var prevTop = board[0][j]
            var prev = board[0][j]
            var sameCount = 1
            var completeCount = 1
            for (i in 1 until n) {
                val curr = board[i][j]
                if(prev == curr)
                    sameCount++
                if(prevTop - curr == 1) {
                    if(curr != prev)
                        sameCount = 1
                    if(sameCount == l) {
                        prevTop = curr
                        completeCount += sameCount
                        sameCount = 0
                    }
                } else if (prevTop - curr == -1) {
                    completeCount++
                    if(sameCount >= l) {
                        prevTop = curr
                        sameCount = 1
                    } else {
                        return false
                    }
                } else if(prevTop - curr == 0) {
                    if(prev == curr)
                        completeCount++
                } else {
                    return false
                }

                prev = curr
            }
            return completeCount == n
        }

        var answer = 0
        for (i in 0 until n) {
            if(checkHorizon(i)) answer++
            if(checkVertical(i)) answer++
        }
        println(answer)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek14890().main()
        }
    }
}
// 걸린 시간(분): 122
