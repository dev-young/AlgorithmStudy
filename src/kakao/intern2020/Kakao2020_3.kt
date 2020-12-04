package kakao.intern2020

//https://programmers.co.kr/learn/courses/30/lessons/67258?language=kotlin
class Kakao2020_3 {
    /**투 포인터 알고리즘을 사용해서 풀어야한다!*/
    fun solution(gems: Array<String>): IntArray {
        var answer = intArrayOf(0,gems.size-1)
        val gemTypeCount = gems.toHashSet().size
        val gemMap = linkedMapOf<String ,Int>()
        var answerLength = gems.size
        gems.forEachIndexed { idx, gem ->
            if(gemMap.contains(gem)) {
                gemMap.remove(gem)
                gemMap[gem] = idx
            } else gemMap[gem] = idx

            if(gemMap.size == gemTypeCount) {
                val l = gemMap.values.first()
                val r = gemMap.values.last()
                val length = r - l
                if(length < answerLength) {
                    answer[0] = l
                    answer[1] = r
                    answerLength = length
                }
            }
        }

        return intArrayOf(answer[0]+1, answer[1]+1)
    }


    fun solution0(gems: Array<String>): IntArray {
        var answer = intArrayOf(1,gems.size)
        val gemTypeCount = gems.toHashSet().size
        val gemSet = hashSetOf<String>()
        var maxDistance = gems.size - 1
        if(gemTypeCount == gems.size) return intArrayOf(1, gemTypeCount)
        gems.forEachIndexed { start, s ->
            gemSet.clear()
            var end = start + maxDistance
            if(end >= gems.size) end = gems.size -1
            for (i in start..end) {
                val g = gems[i]
                gemSet.add(g)
                if(gemSet.size == gemTypeCount) {
                    val d = i - start
                    if(d < maxDistance) {
                        maxDistance = d
                        answer[0] = start+1
                        answer[1] = i+1
                        break
                    }
                }
            }
        }

        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Kakao2020_3()
            println(s.solution(arrayOf("DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA")).contentToString())
            println(s.solution(arrayOf("AA", "AB", "AC", "AA", "AC")).contentToString())
            println(s.solution(arrayOf("XYZ", "XYZ", "XYZ")).contentToString())
            println(s.solution(arrayOf("ZZZ", "YYY", "NNNN", "YYY", "BBB")).contentToString())
        }
    }
}
// 걸린 시간: 27분 (투포인터 알고리즘 공부 후)