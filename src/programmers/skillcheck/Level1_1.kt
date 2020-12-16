package programmers.skillcheck

class Level1_1 {

    fun solution(s: String): String {
        val i = s.length/2
        return if(s.length % 2 == 0)
            "${s[i-1]}${s[i]}"
        else
            "${s[i]}"
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Level1_1()
            println(s.solution("abcde"))

        }
    }

}