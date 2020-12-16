package programmers.skillcheck

class Level2_1 {

    fun solution(n: Int, a: Int, b: Int): Int {
        var answer = 0

        fun getNextN(n: Int): Int {
            return if (n % 2 == 0) n / 2
            else (n + 1) / 2
        }

        var n1 = a
        var n2 = b
        while (n1 != n2) {
            n1 = getNextN(n1)
            n2 = getNextN(n2)
            answer++
        }

        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Level2_1()
            println(s.solution(8, 4, 7))

        }
    }

}