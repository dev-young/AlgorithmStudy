package kakao.winterIntern2019

//https://programmers.co.kr/learn/courses/30/lessons/64065
class Kakao2019_2 {

    fun solution(s: String): IntArray {
        var answer = intArrayOf()
        val set = hashSetOf<String>()
        s.removePrefix("{{").removeSuffix("}}").split("},{").sortedBy { it.length }.forEach {
            for (s in it.split(",")) {
                if(!set.contains(s)){
                    answer += s.toInt()
                    set.add(s)
                    break
                }
            }
        }
        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Kakao2019_2()
            println(s.solution("{{2,1},{2},{2,1,3},{2,1,3,4}}").contentToString())
            println(s.solution("{{1,2,3},{2,1},{1,2,4,3},{2}}").contentToString())
            println(s.solution("{{20,111},{111}}").contentToString())
            println(s.solution("{{123}}").contentToString())

        }
    }
}
// 걸린 시간(분): 14