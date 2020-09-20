package kakao.blind2020

class Blind_2 {
    fun solution(p: String): String {
        var answer = p

        answer = checkBracket(p)

        return answer
    }
    fun checkBracket(str:String): String {
        if(str.isEmpty()) return str

        val uv = getBalanced(str)
        val u = uv.first
        val v = uv.second
//        print("$u / $v")
        if(isAllRight(u)){
            return u+checkBracket(v)
        } else {
            return "(" + checkBracket(v) + ")" + revers(removeFirstAndLast(u))
        }

    }

    private fun revers(str: String): String {
        var result = ""
        str.forEach {
            if(it == '(')
                result += ')'
            else
                result += '('
        }
        return result
    }

    private fun removeFirstAndLast(str: String): String {
        return str.substring(1, str.lastIndex)
    }

    fun getBalanced(str: String) : Pair<String, String>{
        var first = str
        var second = ""

        var countL = 0
        var countR = 0

        str.forEachIndexed { index, c ->
            if(c == '(') countL++
            else countR++

            if(countL == countR) {
                first = str.substring(0, index+1)
                second = str.substring(index+1, str.length)
                return Pair(first, second)
            }


        }


        return Pair(first, second)
    }

    fun isAllRight(str: String): Boolean{
        var count = 0
        str.forEach {
            if(it == '(') count++
            else count--

            if(count < 0)
                return false
        }

        return count == 0
    }

}

fun main() {
    val s = Blind_2()
    println(s.solution("(()())()"))
    println(s.solution("()))((()"))
    println(s.solution(")("))
}