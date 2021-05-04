package kakao.blind2019

//https://programmers.co.kr/learn/courses/30/lessons/42888?language=kotlin
class KakaoB19_1 {

    fun solution(record: Array<String>): Array<String> {
        var answer = arrayOf<String>()
        val uidNameMap = hashMapOf<String, String>()
        val activeList = arrayListOf<Pair<Boolean, String>>()
        record.forEach {
            it.split(" ").let {
                when (it[0]){
                    "Enter" -> {
                        activeList.add(Pair(true, it[1]))
                        uidNameMap[it[1]] = it[2]
                    }

                    "Leave" -> activeList.add(Pair(false, it[1]))

                    else -> uidNameMap[it[1]] = it[2]
                }
            }
        }
        activeList.forEach {
            val name = uidNameMap[it.second]!!
            if(it.first) {
                answer += "${name}님이 들어왔습니다."
            } else answer += "${name}님이 나갔습니다."
        }
        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = KakaoB19_1()
            val r = s.solution(arrayOf("Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"))
            println(r.contentToString())
        }
    }
}
// 걸린 시간: 13분