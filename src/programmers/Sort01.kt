package programmers

import kotlin.system.measureNanoTime

class Sort01 {
    fun solution(array: IntArray, commands: Array<IntArray>): IntArray {
        var answer = IntArray(commands.size)

        commands.forEachIndexed { index, it ->
            val start = it[0]-1
            val end = it[1]
            val target = it[2]-1
            val list = IntArray(end - start)
            for ((index, i) in (start until  end).withIndex()) {
                list[index] = array[i]
            }

            answer[index] = list.sorted()[target]
        }


        return answer
    }

}


fun main() {
    println(Sort01()
        .solution(intArrayOf(1, 5, 2, 6, 3, 7, 4), arrayOf(intArrayOf(2,5,3), intArrayOf(4,4,1), intArrayOf(1,7,3))).contentToString())
}