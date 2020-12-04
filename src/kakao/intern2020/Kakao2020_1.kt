package kakao.intern2020

//https://programmers.co.kr/learn/courses/30/lessons/67256?language=kotlin
class Kakao2020_1 {
    val pos = hashMapOf<Char, IntArray>().apply {
        put('1', intArrayOf(1, 1))
        put('2', intArrayOf(1, 2))
        put('3', intArrayOf(1, 3))
        put('4', intArrayOf(2, 1))
        put('5', intArrayOf(2, 2))
        put('6', intArrayOf(2, 3))
        put('7', intArrayOf(3, 1))
        put('8', intArrayOf(3, 2))
        put('9', intArrayOf(3, 3))
        put('0', intArrayOf(4, 2))
        put('*', intArrayOf(4, 1))
        put('#', intArrayOf(4, 3))
    }

    fun solution(numbers: IntArray, hand: String): String {
        var answer = ""
        var left = '*'
        var right = '#'

        numbers.forEach {
            val c = it.toString()[0]
            when (it) {
                1, 4, 7 -> {
                    left = c
                }
                3, 6, 9 -> {
                    right = c
                }

                else -> {
                    val ld = getDistance(left, c)
                    val rd = getDistance(right, c)
                    if (ld > rd) {
                        right = c
                    } else if (ld < rd) {
                        left = c
                    } else {
                        if (hand == "left")
                            left = c
                        else
                            right = c
                    }
                }
            }
            if(c == left) answer += 'L'
            else answer += 'R'
        }

        return answer
    }

    fun getDistance(from: Char, to: Char): Int {
        return Math.abs(pos[from]!![0] - pos[to]!![0]) + Math.abs(pos[from]!![1] - pos[to]!![1])
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Kakao2020_1()
            var r = s.solution(intArrayOf(7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2), "left")
            println(r)
        }
    }
}
// 걸린 시간: 22분