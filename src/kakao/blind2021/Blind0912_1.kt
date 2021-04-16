package kakao.blind2021

class Blind0912_1 {
    fun solution(new_id: String): String {
        var answer = new_id.toLowerCase()
        answer = """[^\w-.]""".toRegex().replace(answer, "")
        answer = """\.{2,}""".toRegex().replace(answer, ".")
        answer = answer.removePrefix(".").removeSuffix(".")
        if (answer.isEmpty())
            answer = "a"

        if (answer.length > 15) {
            answer = answer.substring(0, 15)
            answer = answer.removeSuffix(".")
        }

        while (answer.length < 3)
            answer += answer.last()

        return answer
    }

    /**더 깔끔한 코드 (다른사람꺼)*/
    fun solution2(newId: String) = newId.toLowerCase()
            .filter { it.isLowerCase() || it.isDigit() || it == '-' || it == '_' || it == '.' }
            .replace("[.]*[.]".toRegex(), ".")
            .removePrefix(".").removeSuffix(".")
            .let { if (it.isEmpty()) "a" else it }
            .let { if (it.length > 15) it.substring(0 until 15) else it }.removeSuffix(".")
            .let {
                if (it.length <= 2)
                    StringBuilder(it).run {
                        while (length < 3) append(it.last())
                        toString()
                    }
                else it
            }
}

fun main() {
    val s = Blind0912_1()
    val r = s.solution("...!@BaT#*..y.abcdefghijklm")
//    val r = s.solution("..1234...ASdf-!@_.")
//    val r = s.solution("11")
    println(r)
}
// 소요시간(분): 60 (정규표현식 공부 포함)