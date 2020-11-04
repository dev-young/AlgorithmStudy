package programmers

//https://programmers.co.kr/learn/courses/30/lessons/42883?language=kotlin
class Greedy02 {

    fun solution(number: String, k: Int): String {
        var answer = ""

        //반환해야하는 수의 가장 큰 자릿수부터 올수 있는 수 중 가장 큰 수를 찾음으로서 정답을 찾을 수 있다.
        //ex) 1231234, 3 -> 천의 자리 수는 1231 중 하나만 가능하다. 그중 가장 큰 수를 찾아서 천의 자리로 사용한다.
        // 백의 자리 수는 12 중 하나만 가능하다.
        val resultLength = number.length - k
        var startIdx = 0
        var endIdx = k
        while (answer.length < resultLength) {
            if(startIdx == endIdx) {
                answer += number.substring(startIdx)
                break
            }
            var maxIdx = startIdx
            var max = number[maxIdx].toInt()
            for (i in startIdx..endIdx) {
                number[i].toInt().let {
                    if(max < it) {
                        maxIdx = i
                        max = it
                    }
                }
            }
            answer += number[maxIdx]
            startIdx = maxIdx+1
            endIdx++
        }

        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Greedy02()
            val r = s.solution("61111352", 6)
            println(r)
//            println(r2.contentToString())
        }
    }
}
// 걸린 시간: 16분