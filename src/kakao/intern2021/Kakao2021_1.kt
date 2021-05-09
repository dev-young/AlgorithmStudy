package kakao.intern2021

class Kakao2021_1 {
    fun solution(s: String): Int {
        var answer: String = ""
        var str = s
        while (str.isNotEmpty()) {
            if (str.startsWith("one")) {
                answer += "1"
                str = str.substring(3)
            } else if (str.startsWith("two")) {
                answer += "2"
                str = str.substring(3)
            } else if (str.startsWith("three")) {
                answer += "3"
                str = str.substring(5)
            } else if (str.startsWith("four")) {
                answer += "4"
                str = str.substring(4)
            } else if (str.startsWith("five")) {
                answer += "5"
                str = str.substring(4)
            } else if (str.startsWith("six")) {
                answer += "6"
                str = str.substring(3)
            } else if (str.startsWith("seven")) {
                answer += "7"
                str = str.substring(5)
            } else if (str.startsWith("eight")) {
                answer += "8"
                str = str.substring(5)
            } else if (str.startsWith("nine")) {
                answer += "9"
                str = str.substring(4)
            } else if (str.startsWith("zero")) {
                answer += "0"
                str = str.substring(4)
            } else {
                answer += str[0]
                str = str.substring(1)
            }
        }

        return answer.toInt()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Kakao2021_1()
            println(s.solution("one4seveneight"))
            println(s.solution("23four5six7"))
            println(s.solution("2three45sixseven"))

            println(s.solution("123"))
        }
    }
}
// 걸린 시간(분):11