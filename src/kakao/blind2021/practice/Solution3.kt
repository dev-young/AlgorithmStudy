package kakao.blind2021.practice

class Solution3 {
    fun solution(info: Array<String>, query: Array<String>): IntArray {
        val conditionScoreMap = hashMapOf<String, ArrayList<Int>>()
        val conditions = arrayOf(
            arrayOf("cpp", "java", "python", "-"),
            arrayOf("backend", "frontend", "-"),
            arrayOf("junior", "senior", "-"),
            arrayOf("chicken", "pizza", "-"),
        )



        for (i0 in conditions[0]) {
            for (i1 in conditions[1]) {
                for (i2 in conditions[2]) {
                    for (i3 in conditions[3]) {
                        conditionScoreMap["$i0 $i1 $i2 $i3"] = arrayListOf()
                    }
                }
            }
        }

        info.forEach {
            it.split(" ").let {
                val score = it[4].toInt()
                conditionScoreMap["${it[0]} ${it[1]} ${it[2]} ${it[3]}"]!!.add(score)
                conditionScoreMap["- ${it[1]} ${it[2]} ${it[3]}"]!!.add(score)
                conditionScoreMap["${it[0]} - ${it[2]} ${it[3]}"]!!.add(score)
                conditionScoreMap["${it[0]} ${it[1]} - ${it[3]}"]!!.add(score)
                conditionScoreMap["${it[0]} ${it[1]} ${it[2]} -"]!!.add(score)
                conditionScoreMap["- - ${it[2]} ${it[3]}"]!!.add(score)
                conditionScoreMap["${it[0]} - - ${it[3]}"]!!.add(score)
                conditionScoreMap["${it[0]} ${it[1]} - -"]!!.add(score)
                conditionScoreMap["- ${it[1]} - ${it[3]}"]!!.add(score)
                conditionScoreMap["${it[0]} - ${it[2]} -"]!!.add(score)
                conditionScoreMap["- ${it[1]} ${it[2]} -"]!!.add(score)
                conditionScoreMap["- - - ${it[3]}"]!!.add(score)
                conditionScoreMap["- - ${it[2]} -"]!!.add(score)
                conditionScoreMap["- ${it[1]} - -"]!!.add(score)
                conditionScoreMap["${it[0]} - - -"]!!.add(score)
                conditionScoreMap["- - - -"]!!.add(score)

            }

        }

        conditionScoreMap.values.forEach { it.sort() }

        var answer = IntArray(query.size)
        query.forEachIndexed { index, s ->
            s.split(" ").let {
                val k = "${it[0]} ${it[2]} ${it[4]} ${it[6]}"
                val s = it[7].toInt()
                val list = conditionScoreMap[k] ?: listOf()
                answer[index] = findFirstBig(listOf(), s)?.let { list.size - it } ?: 0
            }
        }



        return answer
    }

    fun findFirstBig(sortedList: List<Int>, search: Int): Int? {
        var low = 0
        var high = sortedList.size
        var mid = (low + high) / 2

        if (sortedList.isEmpty() || search < sortedList.first()) return 0

        while (low < high) {
            if (sortedList[mid] < search) {
                low = mid + 1
            } else {
                if (mid > 0 && sortedList[mid - 1] < search)
                    break
                high = mid - 1
            }
            mid = (low + high) / 2
        }
        if (mid == sortedList.size) return null
        return mid
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Solution3()
            val r = s.solution(
                arrayOf(
                    "java backend junior pizza 150",
                    "python frontend senior chicken 210",
                    "python frontend senior chicken 150",
                    "cpp backend senior pizza 260",
                    "java backend junior chicken 80",
                    "python backend senior chicken 50"
                ),
                arrayOf(
                    "java and backend and junior and pizza 100",
                    "python and frontend and senior and chicken 200",
                    "cpp and - and senior and pizza 250",
                    "- and backend and senior and - 150",
                    "- and - and - and chicken 100",
                    "- and - and - and - 150"
                )
            )
            println(r.contentToString())

        }
    }
}
// 걸린 시간(분): 82