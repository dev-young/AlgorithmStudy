package baek

import java.io.BufferedReader
import java.io.InputStreamReader

//퇴사
class Baek14501 {

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        val day = IntArray(n)
        val price = IntArray(n)
        for (i in 0 until n) {
            readLine().split(" ").let {
                day[i] = it[0].toInt()
                price[i] = it[1].toInt()
            }
        }
        close()

        fun cal(list: List<Int>) : Int {
            var last = -1
            var res = 0
            for (idx in list) {
                if(idx <= last)
                    break
                last = idx + day[idx] - 1
                if(last >= n)
                    break
                res += price[idx]
            }
            return res
        }
        var answer = 0
        for (i in 0 until (1 shl n)) {
            val temp = arrayListOf<Int>()
            for (j in 0 until n) {
                if (i and (1 shl j) != 0) {
                    temp.add(j)
                }
            }
            answer = Math.max(answer, cal(temp))
        }
        println(answer)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek14501().main()
        }
    }
}
// 걸린 시간(분): 33