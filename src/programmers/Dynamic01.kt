package programmers

//https://programmers.co.kr/learn/courses/30/lessons/42895
class Dynamic01 {

    fun solution(N: Int, number: Int): Int {
        if(N == number) return 1
        val numberMap = hashMapOf<Int, HashSet<Int>>()

        numberMap[1] = hashSetOf(N)

        for (i in 2..8) {
            for (left in 1..i) {
                val right = i-left
                if(left > right) break
                val newSet = hashSetOf("$N".repeat(i).toInt())
                numberMap[left]?.forEach { leftNum ->
                    numberMap[right]?.forEach {rightNum ->
                        newSet.add(leftNum+rightNum)
                        newSet.add(leftNum*rightNum)
                        newSet.add(leftNum-rightNum)
                        newSet.add(rightNum-leftNum)
                        if(rightNum != 0) newSet.add(leftNum/rightNum)
                        if(leftNum != 0) newSet.add(rightNum/leftNum)

                        if(newSet.contains(number)) return i
                    }

                }

                numberMap[i] = newSet
            }
        }

        return -1
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Dynamic01()
            val r = s.solution(5, 5)
            println(r)
        }
    }
}
// 걸린 시간: 40분