package algorithm.blimp

import toArrayOfIntArray
import kotlin.math.abs

class Solution1 {

    data class DataInfo(val dayOfWeek: Int, val sky: Int, val precipitation: Int, val temperature: Int) {
        companion object {
            const val SKY_SUNNY = 1
            const val SKY_CLOUD_LITTLE = 2
            const val SKY_CLOUD_MANY = 3
            const val SKY_CLOUDY = 4

            const val PRC_NONE = 0
            const val PRC_RAIN = 1
            const val PRC_SNOW = 2

            val dayOfWeekPriority = mapOf(
                    Pair(5, 700),
                    Pair(4, 600),
                    Pair(6, 500),
                    Pair(2, 400),
                    Pair(3, 300),
                    Pair(1, 200),
                    Pair(0, 100)
            )
        }

        val dayOfWeekScore = dayOfWeekPriority[dayOfWeek] ?: throw(Exception("WrongDayOfWeek"))

        val temperatureScore = 20 - abs(22 - temperature)

        val weatherScore = when (precipitation) {
            PRC_RAIN -> 5
            PRC_SNOW -> 14
            PRC_NONE -> when (sky) {
                SKY_SUNNY, SKY_CLOUD_LITTLE -> 20
                SKY_CLOUD_MANY -> 17
                SKY_CLOUDY -> 10
                else -> throw(Exception("UnKnownState"))
            }
            else -> throw(Exception("UnKnownState"))
        }

        val totalScore = temperatureScore + weatherScore

        fun isBadDay() = sky == SKY_CLOUDY || precipitation == PRC_RAIN || temperature >= 30 || temperature <= 0
    }

    fun solution(data: Array<IntArray>): IntArray {
        val sortedData = data.mapIndexed { dayOfWeek, it -> DataInfo(dayOfWeek, it[0], it[1], it[2]) }
                .sortedWith { o1, o2 ->
                    val b = o1.totalScore.compareTo(o2.totalScore)
                    if (b == 0) {
                        -o1.dayOfWeekScore.compareTo(o2.dayOfWeekScore)
                    } else -b
                }

        val good = sortedData.first().dayOfWeek
        val bad = sortedData.last().let { if (it.isBadDay()) it.dayOfWeek else -1 }
        return intArrayOf(good, bad)
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Solution1()
            val r = s.solution("""[[1,0,11],[3,1,15],[2,0,16],[4,0,17],[2,0,15],[2,1,14],[2,0,12]]""".toArrayOfIntArray())
//            val r = s.solution("""[[4,0,12],[1,0,16],[3,0,18],[3,0,17],[2,0,15],[3,2,22],[2,1,17]]""".toArrayOfIntArray())
            println(r.contentToString())
        }
    }
}
// 걸린 시간(분):