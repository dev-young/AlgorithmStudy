package programmers

//https://programmers.co.kr/learn/courses/30/lessons/43236?language=kotlin
class BinarySearch02 {

    //https://taesan94.tistory.com/154 참고
    fun solution(distance: Int, rocks: IntArray, n: Int): Int {
        var answer = 0
        rocks.sort()
        var min = 1
        var max = distance
        var mid = 0
        while (min <= max) {
            mid = (min + max) / 2
            var removeCount = 0
            var prev = 0
            for (rock in rocks) {
                val diff = rock - prev
                if (diff < mid) {
                    removeCount++
                    if (removeCount > n)
                        break
                } else prev = rock
            }

            if (distance - prev < mid)
                removeCount++

            if (removeCount <= n) {
                min = mid + 1
                if (answer < mid)
                    answer = mid
            } else if (removeCount > n)
                max = mid - 1

        }

        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = BinarySearch02()
            val r = s.solution(25, intArrayOf(2, 14, 11, 21, 17), 4)
            println(r)
//            println(r2.contentToString())
        }
    }
}
// 걸린 시간: 분