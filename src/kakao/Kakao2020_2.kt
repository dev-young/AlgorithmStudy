package kakao

class Kakao2020_2 {
    val operaterPriority = arrayOf("+-*","+*-","-+*","-*+","*+-","*-+")

    fun solution(expression: String): Long {
        var answer : Long = 0

        expression.split("+", "-", "*")
        val expList = arrayListOf<String>()
        expList.add("")
        for (c in expression) {
            if(c == '+' || c == '-' || c == '*'){
                expList.add(c.toString())
                expList.add("")
            } else
                expList[expList.lastIndex] = expList[expList.lastIndex] + c
        }

        println(expList)

        operaterPriority.forEach {
            val temp:ArrayList<String> = expList.clone() as ArrayList<String>
            for (c in it) {
                val oper = c.toString()
                var i = 0
                while (i > -1 && i < temp.size){
                    val target = temp[i]
                    if (oper == target){
                        val v1 = temp[i-1].toLong()
                        val v2 = temp[i+1].toLong()
                        val result = calculate(v1, v2, oper).toString()
                        temp.removeAt(i+1)
                        temp.removeAt(i)
                        temp[i-1] = result
                        i--
                        continue
                    }

                    i++
                }
            }
            val result = Math.abs(temp[0].toLong())
            if(answer < result)
                answer = result
        }


        return answer
    }

    private fun calculate(v1: Long, v2: Long, operater: String): Long {
        when(operater){
            "+" -> return v1 + v2
            "-" -> return v1 - v2
            "*" -> return v1 * v2
        }
        return 0
    }

}

fun main() {
    val s = Kakao2020_2()
    var r = s.solution("100-200*300-500+20")
    println(r)
}