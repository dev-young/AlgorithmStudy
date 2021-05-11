package baek

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

//시험 감독
class Baek13458 {

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        val people = readLine().split(" ").map { it.toInt() }
        val temp = readLine().split(" ")
        val b = temp[0].toInt()
        val c = temp[1].toInt()
        close()
        var answer = 0L

        for (p in people) {
            answer++
            val remain = p - b
            if (remain <= 0) continue
            answer += (remain / c)
            if(remain%c != 0) answer++
        }
        println(answer)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek13458().main()
        }
    }
}
// 걸린 시간(분): 11