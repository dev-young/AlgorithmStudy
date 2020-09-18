package kakao.blind2020

class Blind0912_1 {
    fun solution(new_id: String): String {
        var answer: String = ""

        answer = new_id.toLowerCase()

        answer = step234(answer)

        if(answer.isEmpty())
            answer = "a"

        answer = step6(answer)

        while (answer.length < 3) {
            answer += answer[answer.lastIndex]
        }

        return answer
    }

    private fun step234(str: String): String {
        var r = ""
        str.forEach {
            when(it){
                'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                '1','2','3','4','5','6','7','8','9','0','-','_','.'-> {
                    if (it != '.' || r.isEmpty() || r[r.lastIndex] != '.') {
                        r += it
                    }
                }
            }
        }

        r = r.removePrefix(".").removeSuffix(".")

        return r
    }

    private fun step6(str: String): String {
        var r = str

        if(str.length >= 16) {
            r = str.removeRange(15, str.length).removeSuffix(".")
        }

        return r
    }
}

fun main() {
    val s = Blind0912_1()
//    val r = s.solution("...!@BaT#*..y.abcdefghijklm")
//    val r = s.solution("..1234...ASdf-!@_.")
    val r = s.solution("11")
    println(r)
}