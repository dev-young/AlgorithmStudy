package kakao.blind2022

import java.math.BigInteger
import kotlin.math.ceil
import kotlin.math.sqrt

class Solution3 {
    fun solution(fees: IntArray, _records: Array<String>): IntArray {
        val records = hashMapOf<String, ArrayList<Int>>()
        val times = hashMapOf<String, Int>()    //차량번호, 총 시간

        _records.forEach {
            it.split(" ").let {
                val m = it[0].split(":").let { it[0].toInt() * 60 + it[1].toInt() }
                val num = it[1]
                records.computeIfAbsent(num) { arrayListOf() }.add(m)
            }
        }

        records.forEach { num, list ->
            var start = 0
            list.forEachIndexed { idx, time ->
                if (idx % 2 == 0) {
                    start = time
                } else {
                    times[num] = (times[num] ?: 0) + time - start
                }
            }
            if (list.size % 2 == 1) {
                times[num] = (times[num] ?: 0) + 1439 - list.last()
            }
        }

        return times.toList().sortedBy { it.first }.map {
            val time = it.second
            val p1 = if (time < fees[0]) return@map fees[1] else fees[1]
            val p2 = (time - fees[0]).let {
                ceil(it / fees[2].toDouble()).toInt() * fees[3]
            }
            p1 + p2
        }.toIntArray()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Solution3()
            val r = s.solution(intArrayOf(180, 5000, 10, 600),
            arrayOf("05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"))
            println(r.toList())
        }
    }
}
// 걸린 시간(분): 38