package programmers


//https://programmers.co.kr/learn/courses/30/lessons/42840?language=kotlin
class ExhaustiveSearch01 {
    fun solution(answers: IntArray): IntArray {
        val result = arrayListOf<Int>()

        val students = arrayOf(
            intArrayOf(1, 2, 3, 4, 5),
            intArrayOf(2, 1, 2, 3, 2, 4, 2, 5),
            intArrayOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5)
        )

        var bestScore = 0
        students.forEachIndexed { student, it ->
            val size = it.size
            var answerCount = 0
            answers.forEachIndexed { index, answer ->
                if( it[index%size] == answer ) {
                    answerCount++
                }
            }
            if(bestScore < answerCount) {
                bestScore = answerCount
                result.clear()
            }

            if(answerCount == bestScore) result.add(student+1)
        }


        return result.apply { sort() }.toIntArray()
    }
}


fun main() {
    println(ExhaustiveSearch01().solution(intArrayOf(1,3,2,4,2)).contentToString())
}