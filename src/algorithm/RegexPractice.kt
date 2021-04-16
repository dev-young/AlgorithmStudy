package algorithm

/**정규표현식 연습
 * (표현식 참고) https://hamait.tistory.com/342
 * */
class RegexPractice {

    //특정 문자를 제외한 나머지 문자 제거
    fun p1(string: String){
        val regex = """[\w-.]""".toRegex()
        val result = regex.findAll(string).joinToString("") { it.value }
        println(result)
    }
    fun p2(string :String){
        val regex = """[^\w-.]""".toRegex() //^를 사용해
        val result = regex.replace(string, "")
        println(result)
    }

    //테스트
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val practice = RegexPractice()
            val s = "..1234...ASdf-!@_."
            practice.p1(s)
            practice.p2(s)
        }
    }
}