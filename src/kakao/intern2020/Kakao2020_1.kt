package kakao.intern2020

//https://programmers.co.kr/learn/courses/30/lessons/67256?language=kotlin
class Kakao2020_1 {
    fun solution(numbers: IntArray, hand: String): String {
        var answer = ""

        var leftHand = 10
        var rightHand = 12

        fun getD(hand: Int, des: Int): Int {
            var hh = hand / 3
            if (hand % 3 != 0) hh++

            var dh = des / 3
            if (des % 3 != 0) dh++

            return Math.abs(dh - hh).let {
                return@let when (hand) {
                    2, 5, 8, 11 -> {
                        it
                    }
                    else -> {
                        it+1
                    }
                }
            }
        }

        numbers.forEach {
            val num = if (it == 0) 11 else it
            var h = ' '
            when (num) {
                1, 4, 7 -> {
                    h = 'L'
                }
                3, 6, 9 -> {
                    h = 'R'
                }
                else -> {
                    val ld = getD(leftHand, num)
                    val rd = getD(rightHand, num)
                    if (ld < rd) {
                        h = 'L'
                    } else if (ld > rd) {
                        h = 'R'
                    } else {
                        if (hand == "right") {
                            h = 'R'
                        } else {
                            h = 'L'
                        }
                    }
                }
            }
            if (h == 'L') leftHand = num
            else rightHand = num
            answer += h
        }

        return answer
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Kakao2020_1()
            println(s.solution(intArrayOf(1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5), "right"))
            println(s.solution(intArrayOf(7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2), "left"))
        }
    }
}
// 걸린 시간: 43분