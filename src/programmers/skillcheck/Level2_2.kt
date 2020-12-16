package programmers.skillcheck

class Level2_2 {

    fun solution(numbers: String): Int {
        var answer = 0

        permutationAll(numbers).toSet().forEach {
            if(isPrime(it))
                answer++
        }

        return answer
    }

    fun permutationAll(numbers: String): ArrayList<Int> {
        val combNumbers = arrayListOf<Int>()
        fun temp(numbers: String, result: String) {
            if (!result.isNullOrEmpty()) {
                combNumbers.add(result.toInt())
            }

            if (numbers.isEmpty()) {
                return
            }
            numbers.forEachIndexed { index, c ->
                temp((numbers.removeRange(index..index)), result + c)
            }

        }

        temp(numbers, "")
        return combNumbers
    }

    fun isPrime(num: Int): Boolean {
        if (num == 1 || num == 0) {
            return false
        }
        for (i in 2..num / 2) {
            if (num % i == 0) {
                return false
            }
        }
        return true
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Level2_2()
            println(s.solution("011"))

        }
    }

}