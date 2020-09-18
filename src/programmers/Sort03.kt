package programmers


//https://programmers.co.kr/learn/courses/30/lessons/42747?language=kotlin
class Sort03 {
    fun solution(citations: IntArray): Int {
        var answer = 0

        while (true) {

            var n = 0
            citations.forEach {
                if(it >= answer)
                    n++
            }
            if(n < answer) {
                answer--
                break
            }
            answer++
        }

        return answer
    }

    fun solution2(citations: IntArray): Int {
        citations.sortDescending()
        for((i, v) in citations.withIndex()) {
            if(v < i+1)
                return i
        }


        return citations.size
    }
}


fun main() {
    println(Sort03().solution2(intArrayOf(3, 0, 6, 1, 5)))
    println(Sort03().solution2(intArrayOf(4, 4, 4, 3, 2, 0, 0)))
}