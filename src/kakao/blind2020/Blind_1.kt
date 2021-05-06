package kakao.blind2020

//https://programmers.co.kr/learn/courses/30/lessons/60057
class Blind_1 {
    fun solution(s: String): Int {
        var answer = s.length
        for (zipCount in 1 .. s.length/2) {
            var zipped = ""
            var prev =""
            var count = 1
            var i = 0
            while (i + zipCount <= s.length) {
                val sub = s.substring(i, i+zipCount)
                if(prev == sub) {
                    count++
                } else {
                    if(count > 1) zipped += "$count"
                    zipped += prev
                    prev = sub
                    count = 1
                }
                i += zipCount
            }
            if(count > 1) zipped += "$count"
            zipped += prev
            if(i < s.length) zipped += s.substring(i)

            if(zipped.length < answer) answer = zipped.length

        }
        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Blind_1()
            println(s.solution("a")) //10a2b3c
            println(s.solution("aabbaccc")) //10a2b3c
            println(s.solution("abcabcdede"))
            println(s.solution("abcabcabcabcdededededede"))
        }
    }

}
// 걸린 시간(분): 23