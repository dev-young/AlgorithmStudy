package programmers.challenge


//https://programmers.co.kr/learn/courses/30/lessons/77884
//level1
class c77884 {

    fun divisorCount(n: Int): Int {
        var cnt = 0
        for (i in 1..n) {
            val ii = i * i
            if (ii > n) break
            if (ii == n) cnt++
            else if (n % i == 0) cnt += 2
        }
        return cnt
    }

    fun solution(left: Int, right: Int): Int {
        var answer = 0
        for (i in left..right) {
            if (divisorCount(i) % 2 == 0) answer += i
            else answer -= i
        }
        return answer
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = c77884()
            println(s.solution(13, 17))
        }
    }
}
// 걸린 시간(분): 8
