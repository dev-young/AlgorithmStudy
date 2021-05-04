package kakao.blind2019

import kotlin.Comparator
import kotlin.random.Random

//https://programmers.co.kr/learn/courses/30/lessons/42891?language=kotlin
class KakaoB19_4 {

    fun solution(food_times: IntArray, k: Long): Int {
        var arr = arrayListOf<Pair<Int,Int>>()
        food_times.forEachIndexed { index, i -> arr.add(Pair(i, index+1)) }
        var p = (k / food_times.size)
        var e = (k % food_times.size)
        var list = arrayListOf<Pair<Int, Int>>()

        while (true) {
            for (i in arr.indices) {
                val r = arr[i].first - p
                if(r> 0){
                    list.add(Pair(r.toInt(), arr[i].second))
                } else {
                    e -= r
                }
            }
            if(list.size > e)
                break
            if (list.isEmpty()) return -1

            arr = list
            list = arrayListOf()
            p = e / arr.size
            e %= arr.size
        }

        return list[((e.toInt() % list.size))].second
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = KakaoB19_4()
            val rand = Random(0)
//            val r = s.solution(intArrayOf(3, 1, 1, 1, 2, 4, 3), 12) // 6
//            val r = s.solution(intArrayOf(3,1,2), 5) // 1

            val a = IntArray(184942) {if(rand.nextBoolean()) 100000000 else 1}
            val r = s.solution(a, 10000000000001) // 1
//            val r = s.solution(intArrayOf(6,2,3,8), 14) // 4
            println(r)
        }
    }
}
// 걸린 시간: 80분