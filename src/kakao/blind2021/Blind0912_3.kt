package kakao.blind2021

class Blind0912_3 {
    fun solution(info: Array<String>, query: Array<String>): IntArray {
        var answer = arrayListOf<Int>()
        val map = hashMapOf<String, ArrayList<Int>>()
        info.forEach {
            it.split(" ").let {
                val score = it[4].toInt()
                map.computeIfAbsent(it[0] + it[1] + it[2] + it[3]) { arrayListOf() }.add(score)
                map.computeIfAbsent(it[1] + it[2] + it[3]) { arrayListOf() }.add(score)
                map.computeIfAbsent(it[0] + it[2] + it[3]) { arrayListOf() }.add(score)
                map.computeIfAbsent(it[0] + it[1] + it[3]) { arrayListOf() }.add(score)
                map.computeIfAbsent(it[0] + it[1] + it[2]) { arrayListOf() }.add(score)
                map.computeIfAbsent(it[2] + it[3]) { arrayListOf() }.add(score)
                map.computeIfAbsent(it[1] + it[3]) { arrayListOf() }.add(score)
                map.computeIfAbsent(it[1] + it[2]) { arrayListOf() }.add(score)
                map.computeIfAbsent(it[0] + it[3]) { arrayListOf() }.add(score)
                map.computeIfAbsent(it[0] + it[2]) { arrayListOf() }.add(score)
                map.computeIfAbsent(it[0] + it[1]) { arrayListOf() }.add(score)
                map.computeIfAbsent(it[0]) { arrayListOf() }.add(score)
                map.computeIfAbsent(it[1]) { arrayListOf() }.add(score)
                map.computeIfAbsent(it[2]) { arrayListOf() }.add(score)
                map.computeIfAbsent(it[3]) { arrayListOf() }.add(score)
                map.computeIfAbsent("") { arrayListOf() }.add(score)
            }
        }
        map.keys.forEach {
            map[it]!!.sort()
        }

        fun findFirst(sortedList: List<Int>, search: Int): Int {
            var low = 0
            var high = sortedList.size
            var mid = (low + high) / 2

            if (search < sortedList.first()) return 0

            while (low < high) {
                if (sortedList[mid] < search) {
                    low = mid + 1
                } else {
                    if(mid > 0 && sortedList[mid-1] < search)
                        break
                    high = mid - 1
                }
                mid = (low + high) / 2
            }
            return mid
        }

        query.forEach {
            it.split(" ").let {
                val q = (it[0] + it[2] + it[4] + it[6]).replace("-", "")
                val s = it[7].toInt()
                if(map[q] == null) answer.add(0)
                else {
                    val c = map[q]!!.size - findFirst(map[q]!!, s)
                    answer.add(c)
                }
            }
        }

        return answer.toIntArray()
    }

}

fun main() {
    val s = Blind0912_3()
//    val r = s.solution(
//            arrayOf(
//                    "java backend junior pizza 150",
//                    "python frontend senior chicken 210",
//                    "python frontend senior chicken 150",
//                    "cpp backend senior pizza 260",
//                    "java backend junior chicken 80",
//                    "python backend senior chicken 50"
//            ),
//            arrayOf(
//                    "- and backend and senior and - 150",
//                    "- and - and - and chicken 100",
//                    "- and - and - and - 150"
//            )
//    )
    val r = s.solution(
            arrayOf(
                    "java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"
            ),
            arrayOf(
                    "java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"
            )
    )
    println(r.contentToString())
}
// 소요시간(분): 115