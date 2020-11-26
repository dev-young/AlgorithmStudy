package kakao.blind2019

import java.util.*
import kotlin.Comparator

//https://programmers.co.kr/learn/courses/30/lessons/42891
class KakaoB19_6 {

    fun solution(food_times: IntArray, k_: Long): Int {
        //리스트를 소팅하는것보다 우선순위 큐를 만들어서 소팅하는것이 더 빠르다.
        val que = PriorityQueue(Comparator<Pair<Int, Int>> { o1, o2 ->
            o1.second.compareTo(o2.second)
        })
        food_times.forEachIndexed { index, i ->
            que.add(Pair(index+1, i))
        }

        var k = k_
        var deleted = 0
        while (que.isNotEmpty() && que.size <= k) {
            val min = que.peek().second
            val maxReduced = que.size * (min - deleted).toLong()
            if(maxReduced <= k)
                k -= maxReduced
            else {
                k %= que.size
                break
            }

            while (que.isNotEmpty() && que.peek().second == min) {
                que.poll()
            }
            deleted = min
        }

        return if (que.isNotEmpty())
            que.sortedBy { it.first }[k.toInt()].first
        else
            -1
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = KakaoB19_6()
            val r = s.solution(intArrayOf(3, 1, 1, 1, 2, 4, 3), 12)
            println(r)
        }
    }
}
// 걸린 시간: 분