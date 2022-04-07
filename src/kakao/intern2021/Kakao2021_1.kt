package kakao.intern2021

class Kakao2021_1 {
    fun solution(s: String): Int {
        val map = hashMapOf<String ,Int>()
        map["zero"] = 0
        map["one"] = 1
        map["two"] = 2
        map["three"] = 3
        map["four"] = 4
        map["five"] = 5
        map["six"] = 6
        map["seven"] = 7
        map["eight"] = 8
        map["nine"] = 9
        var str = s
        map.forEach { t, u ->
            str = str.replace(t, u.toString())
        }

        return str.toInt()
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
// 걸린 시간(분): 5.5