package kakao.blind2020

class Blind_1 {
    fun solution(s: String): Int {
        var answer = s.length

        for (i in 1..s.length) {
            var target = s.substring(0, i)

            var result = ""
            var count = 0
            var startIndex = 0
            var lastIndex = i
            while (startIndex < s.length) {
                if (lastIndex > s.length) lastIndex = s.length

                val temp = s.substring(startIndex, lastIndex)

                if (target == temp) {
                    count++
                } else {
                    if(count > 1) {
                        result += "$count$target"
                    } else {
                        result += "$target"
                    }
                    target = temp
                    count = 1
                }

                startIndex = lastIndex
                lastIndex += i
            }

            if(count > 1) {
                result += "$count$target"
            } else {
                result += "$target"
            }

            if (result.length < answer)
                answer = result.length
        }

        return answer
    }


    fun solution2(s: String): Int {
        var answer = s.length

        for (i in 1..s.length) {
            var target = s.substring(0, i)

            var length = target.length
            var count = 0
            var startIndex = 0
            var lastIndex = i
            while (startIndex < s.length) {
                if (lastIndex > s.length) lastIndex = s.length

                val temp = s.substring(startIndex, lastIndex)

                if (target == temp) {
                    count++
                } else {
                    target = temp
                    count = 1
                    length += temp.length
                }

                if (count > 1) {
                    if (count == 2 || count == 10 || count == 100 || count == 1000) {
                        length++
                    }
                    count++
                }

//                print("  $temp")

                startIndex = lastIndex
                lastIndex += i
            }

//            println("  $length")
            if (length < answer)
                answer = length
        }

        return answer
    }

}

fun main() {
    val s = Blind_1()
    println(s.solution("aabbaccc")) //10a2b3c
    println(s.solution("abcabcdede"))
    println(s.solution("abcabcabcabcdededededede"))

}