package kakao.blind2021

class Kakao2019_3 {
    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        val bannedAvailableMap = banned_id.map { banned->
            user_id.filter { check(it, banned) }
        }
        val answerSet = hashSetOf<String>()
        val  visited = hashSetOf<String>()
        fun loop(startIndex:Int = 0){
            if(startIndex == banned_id.size) {
                if(visited.size == banned_id.size) answerSet.add(visited.sorted().toString())
                return
            }

            for (id in bannedAvailableMap[startIndex]) {
                if(!visited.contains(id)){
                    visited.add(id)
                    loop(startIndex+1)
                    visited.remove(id)
                }
            }
        }
        loop()
        return answerSet.size
    }

    fun check(userId: String, bannedId: String): Boolean {
        if (userId.length != bannedId.length) return false
        for (i in userId.indices) {
            if (userId[i] != bannedId[i] && bannedId[i] != '*')
                return false
        }
        return true
    }

}

fun main() {
    val s = Kakao2019_3()
    val r = s.solution(arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc"),
        arrayOf("fr*d*", "*rodo", "******", "******"))
    println(r)
}