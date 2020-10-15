package programmers

//https://programmers.co.kr/learn/courses/30/lessons/43238?language=kotlin
class BinarySearch01 {

    /**걸릴 수 있는 최대 시간과 최소 시간 사이에서 이분탐색을 통해 해당 시간안에 끝내는게 가능한지 판단하여 결과 찾는 방식*/
    fun solution(n: Int, times: IntArray): Long {
        var answer: Long

        var min = 1L
        var max = n * times.maxOrNull()!!.toLong()
        var mid : Long
        answer = max
        while (min < max) {
            mid = Math.floorDiv(min+max, 2)
            var sum = 0L
            times.forEach {t ->
                sum += mid / t
            }

            if(sum > n) {
                if(mid < answer)
                    answer = mid
                max = mid -1
            } else if (sum < n) {
                min = mid + 1
            } else {
                if(mid < answer)
                    answer = mid

                max = mid
            }
        }

        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = BinarySearch01()
            val r = s.solution(6, intArrayOf(7, 10))
//            val r = s.solution(2, intArrayOf(1,2,2,2,100))
            println(r)
//            println(r2.contentToString())
        }
    }
}


// 걸린 시간: 90분