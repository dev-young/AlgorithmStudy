package kakao.blind2019

//https://programmers.co.kr/learn/courses/30/lessons/42888?language=kotlin
class KakaoB19_1 {

    /**일단 답을 uid가 들어왔다 나갔다로 저장
     * 각 uid의 최종 닉네임을 저장
     * 결과 리턴시 uid를 각자의 닉네임에 맞게 수정*/
    fun solution(record: Array<String>): Array<String> {
        var answer = arrayListOf<String>()
        val log = arrayListOf<Pair<Boolean, String>>()
        val uidNickMap = hashMapOf<String, String>()

        record.forEach {
            it.split(' ').let {
                if(it.size > 2) uidNickMap[it[1]] = it[2]
                if(it[0] == "Enter") log.add(Pair(true, it[1]))
                else if(it[0] == "Leave") log.add(Pair(false, it[1]))
            }
        }

        log.forEach {
            if(it.first){
                answer.add(uidNickMap[it.second]+"님이 들어왔습니다.")
            } else
                answer.add(uidNickMap[it.second]+"님이 나갔습니다.")
        }

        return answer.toTypedArray()
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
// 걸린 시간: 22분