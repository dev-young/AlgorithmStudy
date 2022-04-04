package kakao.blind2022

class Solution1 {
    fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
        val answer = IntArray(id_list.size)
        val idIdx = hashMapOf<String, Int>()
        val map = hashMapOf<String, HashSet<String>>()  // <id, id를 신고한 id>
        id_list.forEachIndexed { index, s ->
            idIdx[s] = index
            map[s] = hashSetOf()
        }
        val splitList = report.map { it.split(" ") }
        splitList.forEach {
            map[it[1]]!!.add(it[0])
        }

        map.values.forEach { set ->
            if(set.size >= k) {
                set.forEach { id ->
                    answer[idIdx[id]!!] += 1
                }
            }
        }


        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Solution1()
//            val r = s.solution()
//            println(r.contentToString())
        }
    }
}
// 걸린 시간(분): 21