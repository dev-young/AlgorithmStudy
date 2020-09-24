package programmers

import kotlin.math.ceil

//https://programmers.co.kr/learn/courses/30/lessons/42586?language=kotlin
class StackQue01 {
    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        var answer = arrayListOf<Int>()

        fun getDay(progress: Int, speed: Int):Int{
            return Math.ceil((100 - progress).toDouble().div(speed)).toInt()
        }

        var maxDay = 0
        var count = 0
        progresses.forEachIndexed { index: Int, progress: Int ->
            val speed = speeds[index]
            val remainDay = getDay(progress, speed)
            println(remainDay)

            if(maxDay == 0) maxDay = remainDay

            if(maxDay < remainDay) {
                maxDay = remainDay
                answer.add(count)
                count = 1
            } else {
                count++
            }
        }
        answer.add(count)

        return answer.toIntArray()
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = StackQue01()
            println(s.solution(intArrayOf(93, 30, 55), intArrayOf(1, 30, 5)).contentToString())
        }
    }
}
// 걸린 시간: 30분
