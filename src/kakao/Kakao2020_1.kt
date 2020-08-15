package kakao

class Kakao2020_1 {
    private val numPos = hashMapOf<Int, IntArray>().apply {
        this[0] = intArrayOf(2, 1)
        this[1] = intArrayOf(1, 4)
        this[2] = intArrayOf(2, 4)
        this[3] = intArrayOf(3, 4)
        this[4] = intArrayOf(1, 3)
        this[5] = intArrayOf(2, 3)
        this[6] = intArrayOf(3, 3)
        this[7] = intArrayOf(1, 2)
        this[8] = intArrayOf(2, 2)
        this[9] = intArrayOf(3, 2)
        this[10] = intArrayOf(1, 1)
        this[12] = intArrayOf(3, 1)
    }

    fun solution(numbers: IntArray, hand: String): String {
        var answer : String = ""

        var left = 10
        var right = 12

        numbers.forEach {
            when (it) {
                1, 4, 7 -> {
                    answer += "L"
                    left = it
                }

                3, 6, 9 -> {
                    answer += "R"
                    right = it
                }

                2, 5, 8, 0 -> {
                    val ld = getDistance(left, it)
                    val rd = getDistance(right, it)
                    if(ld > rd){
                        answer += "R"
                        right = it
                    } else if (ld < rd){
                        answer += "L"
                        left = it
                    } else {
                        if(hand == "left"){
                            answer += "L"
                            left = it
                        } else {
                            answer += "R"
                            right = it
                        }
                    }
                }
            }
        }


        return answer
    }

    private fun getDistance(from: Int, to: Int): Int {
        return Math.abs(numPos[from]!![0] - numPos[to]!![0]) + Math.abs(numPos[from]!![1] - numPos[to]!![1])
    }

}

fun main() {
    val s = Kakao2020_1()
    var r = s.solution(intArrayOf(7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2), "left")
    println(r)
}