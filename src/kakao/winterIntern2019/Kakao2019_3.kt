package kakao.blind2021

//https://programmers.co.kr/learn/courses/30/lessons/64064
class Kakao2019_3 {
    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        val availables = Array(banned_id.size){ hashSetOf<Int>()}
        banned_id.forEachIndexed { index, banned ->
            out@for (i in user_id.indices) {
                val id = user_id[i]
                if(banned.length == id.length) {
                    for (i in id.indices) {
                        if(banned[i] == '*') continue
                        if(banned[i] != id[i]) continue@out
                    }
                    availables[index].add(i)
                }
            }
        }

        val bannedSet = hashSetOf<Int>()
        val res = hashSetOf<String>()
        fun dfs(n :Int) {
            if(n == banned_id.size) {
                res.add(bannedSet.joinToString(" "))
                return
            }
            for (i in availables[n]) {
                if(bannedSet.contains(i)) continue
                bannedSet.add(i)
                dfs(n+1)
                bannedSet.remove(i)
            }
        }
        dfs(0)

        return res.size
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Kakao2019_3()
//            val r = s.solution(arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc"),
//                    arrayOf("fr*d*", "abc1**"))
            val r = s.solution(arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc"),
                    arrayOf("fr*d*", "*rodo", "******", "******"))
            println(r)

        }
    }
}
// 걸린 시간(분): 36