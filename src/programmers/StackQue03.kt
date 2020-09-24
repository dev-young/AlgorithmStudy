package programmers

import java.util.*

class StackQue03 {
    fun solution(priorities: IntArray, location: Int): Int {
        var answer = 0

        val que : Queue<Int> = LinkedList(priorities.toList())

        var targetIdx = location
        while (true) {

            val pri = que.poll()
            var isPolled = true
            for (p in que) {
                if (pri < p) {
                    isPolled = false
                    que.add(pri)
                    break
                }
            }
            if(isPolled) {
                answer++
                if(targetIdx == 0)
                    break
            }
            targetIdx--
            if(targetIdx < 0)
                targetIdx = que.size - 1
        }

        return answer
    }







    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = StackQue03()
            val r = s.solution(intArrayOf(1, 1, 9, 1, 1, 1), 0)
            println(r)
        }
    }
}
// 걸린 시간: 34분