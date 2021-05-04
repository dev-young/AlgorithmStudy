package kakao.blind2019

//https://programmers.co.kr/learn/courses/30/lessons/42890?language=kotlin
class KakaoB19_3 {

    fun solution(relation: Array<Array<String>>): Int {
        var answer = 0
        val n = relation[0].size
        val res = arrayListOf<IntArray>()
        for (i in 0 until (1 shl n)) {
            val temp = arrayListOf<Int>()
            for (j in 0 until n) {
                if (i and (1 shl j) != 0) {
                    temp.add(j)
                }
            }
            res.add(temp.toIntArray())
        }
        res.sortBy { it.size }
        val answerKeySet = arrayListOf<Set<Int>>()
        out@for (keys in res) {
            val cur = keys.toSet()
            for (aSet in answerKeySet) {
                if(aSet.size >= cur.size) continue
                if(cur.containsAll(aSet)) continue@out
            }
            val rowSet = hashSetOf<String>()
            for (row in relation) {
                var s = ""
                keys.forEach { key->
                    s += row[key]
                }
                if (rowSet.contains(s)) continue@out
                rowSet.add(s)
            }
            answerKeySet.add(cur)
            answer++
        }

        return answer
    }



    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = KakaoB19_3()
            val r = s.solution(arrayOf(arrayOf("100", "ryan", "music", "2"),
                arrayOf("200", "apeach", "math", "2"),
                arrayOf("300", "tube", "computer", "3"),
                arrayOf("400", "con", "computer", "4"),
                arrayOf("500", "muzi", "music", "3"),
                arrayOf("600", "apeach", "music", "2")))
            println(r)
        }
    }
}
// 걸린 시간: 30분