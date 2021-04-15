package programmers.line2021

class Line1 {

    fun solution(table: Array<String>, languages: Array<String>, preference: IntArray): String {
        var answer: String = ""

        val scoreMap = hashMapOf<String, HashMap<String,Int>>()
        table.forEach {
            it.split(" ").let {
                scoreMap[it[0]] = hashMapOf()
                scoreMap[it[0]]!![it[1]] = 5
                scoreMap[it[0]]!![it[2]] = 4
                scoreMap[it[0]]!![it[3]] = 3
                scoreMap[it[0]]!![it[4]] = 2
                scoreMap[it[0]]!![it[5]] = 1
            }
        }

        var max = 0
        scoreMap.forEach { t, u ->
            var score = 0
            languages.forEachIndexed { index, s ->
                val p = preference[index]
                u[s]?.let {
                    score += (p*it)
                }
            }
            if (score > max) {
                answer = t
                max = score
            } else if (score == max) {
                if(answer > t) answer = t
            }
        }

        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Line1()
            val r = s.solution(
                    arrayOf("PORTAC JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++",
                            "HARDWARE C C++ PYTHON JAVA JAVASCRIPT",
                            "PORTAB JAVA JAVASCRIPT PYTHON KOTLIN PHP",
                            "GAME C++ C# JAVASCRIPT C JAVA"),
                    arrayOf("JAVA", "JAVASCRIPT"),
                    intArrayOf(7, 5)
            )
            println(r)

        }
    }
}
